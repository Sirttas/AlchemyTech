package sirttas.alchemytech.ingredient;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.RegistryBuilder;
import sirttas.alchemytech.AlchemyTech;

public class IngredientRegistry {

	private FMLControlledNamespacedRegistry<Ingredient> registry;

	public static final ResourceLocation INGREDIENTS = new ResourceLocation("alchemytech:ingredients");

	private static final int MIN_INGREDIENT_ID = 0;
	private static final int MAX_INGREDIENT_ID = Short.MAX_VALUE - 1;

	private static IngredientRegistry insntace = new IngredientRegistry();

	protected static IngredientRegistry getInstance() {
		return insntace;
	}

	private IngredientRegistry() {
		RegistryBuilder<Ingredient> builder = new RegistryBuilder<Ingredient>();
		builder.setName(INGREDIENTS);
		builder.setIDRange(MIN_INGREDIENT_ID, MAX_INGREDIENT_ID);
		builder.setType(Ingredient.class);
		registry = (FMLControlledNamespacedRegistry<Ingredient>) builder.create();
		AlchemyTech.T.info("Ingredients registry loaded.");
	}

	static final FMLControlledNamespacedRegistry<Ingredient> getIngredienRegistry() {
		return getInstance().registry;
	}
}
