package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnergizedEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "energized";
	private PotionEffect speed;
	private PotionEffect strenth;

	public EnergizedEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strenth) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) entity;

			if (speed == null || this.strenth == null) {
				speed = new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:speed"), 10, 1);
				this.strenth = new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:strenth"), 10, 1);
			}

			livingEntity.addPotionEffect(speed);
			livingEntity.addPotionEffect(this.strenth);
		}
	}
}
