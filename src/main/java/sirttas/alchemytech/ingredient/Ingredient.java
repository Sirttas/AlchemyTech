package sirttas.alchemytech.ingredient;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.text.translation.I18n;

public class Ingredient extends net.minecraftforge.fml.common.registry.IForgeRegistryEntry.Impl<Ingredient> {
	public static final RegistryNamespaced<ResourceLocation, Ingredient> REGISTRY = IngredientRegistry
			.getIngredienRegistry();

	public static final int NO_FREEZING = -101;
	public static final int NO_BOILING = 101;

	private int freezingPoint;
	private int boilingPoint;

	private String unlocalizedName;

	private int color;

	public Ingredient() {

	}

	public Ingredient(String name) {
		if (name != null) {
			setRegistryName(name);
			setUnlocalizedName(name);
		}
	}

	public int getFreezingPoint() {
		return freezingPoint;
	}

	public Ingredient setFreezingPoint(int freezingPoint) {
		this.freezingPoint = freezingPoint;
		return this;
	}

	public int getBoilingPoint() {
		return boilingPoint;
	}

	public Ingredient setBoilingPoint(int boilingPoint) {
		this.boilingPoint = boilingPoint;
		return this;
	}

	public String getUnlocalizedName() {
		return "ingredient." + unlocalizedName;
	}

	public Ingredient setUnlocalizedName(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
		return this;
	}

	public String getDisplayName() {
		return ("" + I18n.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
	}

	public int getColor() {
		return color;
	}

	public Ingredient setColor(int color) {
		this.color = color;
		return this;
	}

}
