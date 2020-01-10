package fr.upem.dut.info.autoConfigurator.components.cards;

import java.util.Objects;

import fr.upem.dut.info.autoConfigurator.components.Slots;

public class SoundCard extends AbstractCard {
	
	private final String technology;

	public SoundCard(String name, Slots slots,String technology) {
		super(name, slots, CardsType.SOUND,true);
		this.technology = Objects.requireNonNull(technology);
	}

	@Override
	public void launchComponent() {
		System.out.print("Inititialisation de la carte son");
		for(int i =0;i<3;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.print(".");
		}
		System.out.print(" Finished !\n");
		System.out.println(this.toString()+" Initialized !");
	}
	
	@Override
	public String toString() {
		return getName()+" - SOUND CARD ("+technology+")";
	}

}
