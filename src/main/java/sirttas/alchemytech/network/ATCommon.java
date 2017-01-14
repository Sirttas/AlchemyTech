package sirttas.alchemytech.network;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sirttas.alchemytech.block.ATBlocks;
import sirttas.alchemytech.ingredient.Ingredients;
import sirttas.alchemytech.item.ATItems;
import sirttas.alchemytech.world.ATWorldGenerator;

public class ATCommon implements IProxy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rip.shendai.alchemytech.network.IATProxy#preInit(net.minecraftforge.fml.
	 * common.event.FMLPreInitializationEvent)
	 */
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ATItems.preInit();
		ATBlocks.preInit();
		Ingredients.preInit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rip.shendai.alchemytech.network.IATProxy#init(net.minecraftforge.fml.
	 * common.event.FMLInitializationEvent)
	 */
	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new ATWorldGenerator(), 5);
		ATItems.init();
		ATBlocks.init();
		Ingredients.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rip.shendai.alchemytech.network.IATProxy#postInit(net.minecraftforge.fml.
	 * common.event.FMLPostInitializationEvent)
	 */
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ATItems.postInit();
		ATBlocks.postInit();
		Ingredients.postInit();
	}
}
