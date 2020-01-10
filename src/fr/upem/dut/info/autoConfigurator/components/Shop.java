package fr.upem.dut.info.autoConfigurator.components;

import java.util.ArrayList;
import java.util.List;

import fr.upem.dut.info.autoConfigurator.Computer;
import fr.upem.dut.info.autoConfigurator.components.cards.Card;
import fr.upem.dut.info.autoConfigurator.components.cards.GPU;
import fr.upem.dut.info.autoConfigurator.components.cards.GPUBrand;
import fr.upem.dut.info.autoConfigurator.components.cards.NetworkCard;
import fr.upem.dut.info.autoConfigurator.components.cards.SoundCard;

public class Shop {
	
	private final static List<Card> availableItems = new ArrayList<>();
	
	public Shop() {
		//on rajoute les cartes en statique
		
		//GPU
		availableItems.add(new GPU("RX 5700XT", Slots.PCIE16, GPUBrand.AMD));
		availableItems.add(new GPU("RX 590", Slots.PCIE16, GPUBrand.AMD));
		availableItems.add(new GPU("RX 580", Slots.PCIE16, GPUBrand.AMD));
		
		availableItems.add(new GPU("GTX 1060", Slots.PCIE16, GPUBrand.NVIDIA));
		availableItems.add(new GPU("GTX 1070", Slots.PCIE16, GPUBrand.NVIDIA));
		availableItems.add(new GPU("RTX 2080", Slots.PCIE16, GPUBrand.NVIDIA));
		
		//Sounds Cards
		availableItems.add(new SoundCard("STRIX Soar", Slots.PCIE1, "7.1"));
		availableItems.add(new SoundCard("ASUS Xonar", Slots.PCIE1, "7.1"));
		availableItems.add(new SoundCard("SBX ProStudio", Slots.PCIE1, "5.1"));
		
		//Network Cards
		availableItems.add(new NetworkCard("TP-LINK Archer t4e", Slots.PCIE1, 1200));
		availableItems.add(new NetworkCard("ASUS PCE-N15", Slots.PCIE1, 300));
		availableItems.add(new NetworkCard("TP-LINK Archer t6e", Slots.PCIE1, 1300));
	}
	
	public static void showCatalog() {
		System.out.println(Computer.ANSI_YELLOW+"Bienvenue voyageur ! Voici mon catalogue ..."+Computer.ANSI_RESET);
		for(int i = 0; i< availableItems.size();i++) {
			System.out.println(i+1+" - "+availableItems.get(i));
		}
	}
	
	public static Card getCard(int i) {
		return availableItems.get(i-1);
	}

}
