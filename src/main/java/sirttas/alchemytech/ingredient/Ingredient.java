package sirttas.alchemytech.ingredient;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.text.translation.I18n;

@SuppressWarnings("deprecation")
public class Ingredient extends net.minecraftforge.fml.common.registry.IForgeRegistryEntry.Impl<Ingredient> {
	public static final RegistryNamespaced<ResourceLocation, Ingredient> REGISTRY = IngredientRegistry
			.getIngredienRegistry();

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
