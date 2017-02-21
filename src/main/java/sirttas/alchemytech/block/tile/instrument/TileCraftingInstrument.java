package sirttas.alchemytech.block.tile.instrument;

import sirttas.alchemytech.block.tile.api.IInstrumentTile;

public abstract class TileCraftingInstrument extends TileSingleSlotInstrument {

	@Override
	public boolean isReciptAvalable() {
		if (slot == null && !(this instanceof IInstrumentTile)) {
			return false;
		}
		return super.isReciptAvalable();
	}

}
