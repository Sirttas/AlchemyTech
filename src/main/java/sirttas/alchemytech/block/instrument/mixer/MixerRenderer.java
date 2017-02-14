package sirttas.alchemytech.block.instrument.mixer;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import sirttas.alchemytech.block.tile.renderer.ATRenderer;

public class MixerRenderer extends ATRenderer<TileMixer> {
	@Override
	public void renderTileEntityAt(TileMixer instrument, double x, double y, double z, float partialTicks,
			int destroyStage) {
		if (instrument.getStackInSlot(0) != null) {
			renderItem(instrument.getStackInSlot(0), x + 2 * BIT_SIZE, y + 4 * BIT_SIZE, z + 0.475, 0.5);
		}
		if (instrument.getStackInSlot(1) != null) {
			renderItem(instrument.getStackInSlot(1), x + 0.475, y + 4 * BIT_SIZE, z + 2 * BIT_SIZE, 0.5);
		}
		if (instrument.getStackInSlot(2) != null) {
			renderItem(instrument.getStackInSlot(2), x + 14 * BIT_SIZE, y + 4 * BIT_SIZE, z + 0.475, 0.5);
		}
		if (instrument.getStackInSlot(3) != null) {
			renderItem(instrument.getStackInSlot(3), x + 0.475, y + 4 * BIT_SIZE, z + 14 * BIT_SIZE, 0.5);
		}
		if (instrument.getStackInSlot(4) != null) {
			renderItem(instrument.getStackInSlot(4), x + 0.475, y + 6 * BIT_SIZE, z + 0.475, 0.5);
		}
	}
}
