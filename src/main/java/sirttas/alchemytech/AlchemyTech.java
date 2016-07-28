package sirttas.alchemytech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import sirttas.alchemytech.network.IProxy;

@Mod(modid = AlchemyTech.MODID, name = AlchemyTech.NAME, version = AlchemyTech.VERSION)
public class AlchemyTech {
	public static final String MODID = "alchemytech";
	public static final String VERSION = "1.0";
	public static final String NAME = "Alchemy Tech";

	public static final Logger T = LogManager.getLogger(MODID);

	@SidedProxy(serverSide = "sirttas.alchemytech.network.ATCommon", clientSide = "sirttas.alchemytech.network.ATClient")
	public static IProxy proxy;

	@Instance(MODID)
	public static AlchemyTech instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
