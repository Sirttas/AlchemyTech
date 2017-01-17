package sirttas.alchemytech;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager {
	Random random = new Random();

	private static String GLOWING = "GLOWING";

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingTick(LivingUpdateEvent event) {
		NBTTagCompound data = event.getEntityLiving().getEntityData();

		if (data.hasKey(GLOWING)) {
			data.setInteger(GLOWING, data.getInteger(GLOWING) - 1);
		}
	}

}
