package sirttas.alchemytech.block.tile.instrument;

import net.minecraft.inventory.ISidedInventory;

public interface IInstrument extends ISidedInventory {

	boolean canProgress();

	boolean isReciptAvalable();

	void process();

	float getProgress();

}