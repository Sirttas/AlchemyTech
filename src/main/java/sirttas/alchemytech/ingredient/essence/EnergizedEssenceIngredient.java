package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnergizedEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "energized";
	private PotionEffect speed;
	private PotionEffect strength;

	public EnergizedEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strength) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) entity;

			if (speed == null || this.strength == null) {
				speed = new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:speed"), 10, 1);
				this.strength = new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:strength"), 10, 1);
			}

			livingEntity.addPotionEffect(speed);
			livingEntity.addPotionEffect(this.strength);
		}
	}
}
