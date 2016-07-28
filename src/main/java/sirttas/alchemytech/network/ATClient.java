package sirttas.alchemytech.network;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import sirttas.alchemytech.block.ATBlocks;
import sirttas.alchemytech.item.ATItems;

public class ATClient extends ATCommon {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ATItems.initModels();
		ATBlocks.initModels();
	}
}
