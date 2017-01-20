package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DiamondEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "diamond";

	public DiamondEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strength) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) entity;

			livingEntity.addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:resistance"), 1200, 0));
		}
	}
}
