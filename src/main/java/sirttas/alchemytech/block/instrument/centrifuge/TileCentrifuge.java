package sirttas.alchemytech.block.instrument.centrifuge;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.tile.instrument.TileCraftingInstrument;

public class TileCentrifuge extends TileCraftingInstrument {

	@Override
	public String getName() {
		return ConfigCentrifuge.UNLOCALIZED_NAME;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void clientUpdate() {
		if (progress > 0) {
			progress++;
		}
	}
}
