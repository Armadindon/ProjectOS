package fr.upem.dut.info.autoConfigurator.components.cards;

import java.util.Objects;

import fr.upem.dut.info.autoConfigurator.components.Slots;

public abstract class AbstractCard implements Card{
	
	private final String name;
	private final Slots slots;
	private final CardsType type;
	private final boolean DMA;
	
	
	
	public AbstractCard(String name, Slots slots, CardsType type,boolean DMA) {
		this.name = Objects.requireNonNull(name);
		this.slots = Objects.requireNonNull(slots);
		this.type = Objects.requireNonNull(type);
		this.DMA = DMA;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Slots getSlot() {
		return slots;
	}
	
	@Override
	public CardsType getType() {
		return type;
	}
	
	@Override
	abstract public void launchComponent();
	
	@Override
	public boolean needDMA() {
		return DMA;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name,slots,type,DMA);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCard other = (AbstractCard) obj;
		if (DMA != other.DMA)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (slots != other.slots)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	

}
