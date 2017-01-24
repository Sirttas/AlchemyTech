package sirttas.alchemytech.block.instrument.boiler;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import sirttas.alchemytech.block.tile.renderer.InstrumentRenderer;

public class BoilerRenderer extends InstrumentRenderer<TileBoiler> {

	@Override
	public void renderTileEntityAt(TileBoiler te, double x, double y, double z, float partialTicks, int destroyStage) {
		if (te.getStackInSlot(0) != null) {
			renderItem(te.getStackInSlot(0), x + 0.5, y + 8.5 * BIT_SIZE, z + 0.5, 0.5);
		}
	}

}
