package sirttas.alchemytech.block.instrument.shaker;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.tile.instrument.TileSingleSlotInstrument;

public class TileShaker extends TileSingleSlotInstrument {

	@Override
	public String getName() {
		return ConfigShaker.UNLOCALIZED_NAME;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void clientUpdate() {
		if (progress > 0) {
			progress++;
		}
	}

}
