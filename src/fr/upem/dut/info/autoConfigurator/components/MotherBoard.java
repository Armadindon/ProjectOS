package fr.upem.dut.info.autoConfigurator.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.upem.dut.info.autoConfigurator.components.cards.Card;

public class MotherBoard {
	private static List<Integer> availibleIrq = Arrays.asList(5,9,10,11,12);
	
	private Map<Slots, Integer> numbers = new HashMap<>();
	private List<Card> installedCards =  new ArrayList<>();
	private Map<Card, Integer> irqs = new HashMap<>();
	

	public MotherBoard(Map<Slots, Integer> numbers) {
		this.numbers = numbers;
	}
	
	
	public void addCard(Card c) {
		Objects.requireNonNull(c,"The card can't be null");
		if(numbers.get(c.getSlot())== null) throw new IllegalArgumentException("The motherboad don't have this port");
		int nbAvailable;
		if((nbAvailable = numbers.get(c.getSlot())) == 0) throw new IllegalArgumentException("There is no more of this slot available");
		installedCards.add(c);
		numbers.put(c.getSlot(),nbAvailable-1);
	}
	
	public void removeCard(Card c) {
		Objects.requireNonNull(c,"The card can't be null");
		if(!installedCards.contains(c)) throw new IllegalArgumentException("The motherboad don't have this card installed");
		installedCards.remove(c);
		numbers.put(c.getSlot(),numbers.get(c.getSlot())+1);
	}
	
	/*
	 * En temps normal, le BIOS va associer les Cartes a des IRQ bien précis (exemple : Les CG on une Irq dédiée)
	 * Ici, on va mettre des IRQ aléatoire
	 */
	public boolean setIrq(Card c) {
		for (Integer integer : availibleIrq) {
			if(!irqs.containsValue(integer)) {
				irqs.put(c, integer);
				return true;
			}
		}
		return false;
	}
	
	public Card getCard(int i) {
		return installedCards.get(i);
	}
	
	public boolean haveCards() {
		return installedCards.size() != 0;
	}
	
	public void printCards() {
		installedCards.forEach(c->System.out.println(installedCards.indexOf(c)+" - "+c+" (Irq: "+irqs.get(c)+")."));
	}
	
	public void configure() {
		for(Card c:installedCards) {
			if(!irqs.containsKey(c)) {
				if(setIrq(c)) System.out.println("L'irq de "+c+" a été réglé sur "+irqs.get(c));
				else throw new IllegalStateException("No port left for this card");
				
				//il faudrait mettre en place une vrai configuration d'entrée sortie et de DMA
				System.out.println("La plage d'input a été réglée pour "+c);
				if(c.needDMA()) System.out.println("Le DMA a été réglé pour "+c);
			}else System.out.println("La carte mère connait déjà "+c+" chargement des paramètre préenregistrés");
		}
	}

}
