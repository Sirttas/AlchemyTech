package sirttas.alchemytech.block.tile.instrument;

public interface IInstrumentCore {

	boolean isFueled();

	boolean isInstrumentAutomated();

	void consumeFuel(int amount);

	boolean isBurning();
}
