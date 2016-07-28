package sirttas.alchemytech.block.instrument.core;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.block.tile.renderer.InstrumentRenderer;

public class InstrumentCoreRenderer extends InstrumentRenderer<TileInstrumentCore> {

	@Override
	public void renderTileEntityAt(TileInstrumentCore instrument, double x, double y, double z, float partialTicks,
			int destroyStage) {

		ItemStack fuel = instrument.getFuel();

		if (fuel != null) {
			renderItem(fuel, x + 0.475, y + 3 * BIT_SIZE, z + 0.475, 1);
		}

	}

}
