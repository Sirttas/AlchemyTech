package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnergizedEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "energized";

	public EnergizedEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strength) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) entity;

			livingEntity.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:speed"), 1200, 0));
			livingEntity.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:strength"), 1200, 0));
		}
	}
}
