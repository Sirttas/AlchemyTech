package sirttas.alchemytech.block.instrument.boiler;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.tile.instrument.TileSingleSlotInstrument;

public class TileBoiler extends TileSingleSlotInstrument {

	@Override
	public String getName() {
		return ConfigBoiler.UNLOCALIZED_NAME;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void clientUpdate() {
		if (progress > 0) {
			progress++;
		}
	}
}
