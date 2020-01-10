package fr.upem.dut.info.autoConfigurator.components.cards;

import fr.upem.dut.info.autoConfigurator.components.Slots;

public class NetworkCard extends AbstractCard {
	
	private final int speed;
	
	public NetworkCard(String name, Slots slots,int speed) {
		super(name, slots, CardsType.NETWORK,false);
		this.speed = speed;
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
		return getName()+" - NETWORK CARD ("+speed+" Mbps)";
	}

}
