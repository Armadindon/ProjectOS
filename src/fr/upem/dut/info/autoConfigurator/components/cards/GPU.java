package fr.upem.dut.info.autoConfigurator.components.cards;

import java.util.Objects;

import fr.upem.dut.info.autoConfigurator.components.Slots;

public class GPU extends AbstractCard {
	
	private final GPUBrand brand;

	public GPU(String name, Slots slots,GPUBrand brand) {
		super(name, slots, CardsType.GPU,true);
		this.brand = Objects.requireNonNull(brand);
	}

	@Override
	public void launchComponent() {
		System.out.print("Inititialisation du GPU");
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
		return getName()+" - GPU "+brand.name();
	}

}
