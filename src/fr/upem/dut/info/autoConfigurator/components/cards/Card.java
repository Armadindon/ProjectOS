package fr.upem.dut.info.autoConfigurator.components.cards;
import fr.upem.dut.info.autoConfigurator.components.Slots;

public interface Card {
	
	public String getName();
	public Slots getSlot();
	public CardsType getType();
	public void launchComponent();
	public boolean needDMA();

}
