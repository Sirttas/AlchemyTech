package sirttas.alchemytech;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sirttas.alchemytech.block.ATBlocks;

public class EventManager {
	Random random = new Random();

	private static String GLOWING = "GLOWING";

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingTick(LivingUpdateEvent event) {
		NBTTagCompound data = event.getEntityLiving().getEntityData();
		EntityLivingBase entity = event.getEntityLiving();

		if (data.hasKey(GLOWING)) {
			data.setInteger(GLOWING, data.getInteger(GLOWING) - 1);
			if (data.getInteger(GLOWING) % 40 == 0 && entity.getEntityWorld().isAirBlock(entity.getPosition())
					&& entity.getEntityWorld().getBlockState(entity.getPosition())
							.getLightValue(entity.getEntityWorld(), entity.getPosition()) < 8) {
				entity.getEntityWorld().setBlockState(entity.getPosition(), ATBlocks.glowingEssence.getDefaultState());
			}
			if (data.getInteger(GLOWING) == 0) {
				data.removeTag(GLOWING);
			}
		}
	}

}
