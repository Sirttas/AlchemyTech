package sirttas.alchemytech;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager {
	private static String GLOWING = "GLOWING";

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingTick(LivingUpdateEvent event) {
		NBTTagCompound data = event.getEntityLiving().getEntityData();
		// EntityLivingBase entity = event.getEntityLiving();

		if (data.hasKey(GLOWING)) {
			data.setInteger(GLOWING, data.getInteger(GLOWING) - 1);
			// entity.getEntityWorld().setLightFor(EnumSkyBlock.BLOCK,
			// entity.getPosition(), 15);
			// entity.getEntityWorld().checkLightFor(EnumSkyBlock.BLOCK,
			// entity.getPosition());
			if (data.getInteger(GLOWING) == 0) {
				data.removeTag(GLOWING);
			}
		}
	}
}
