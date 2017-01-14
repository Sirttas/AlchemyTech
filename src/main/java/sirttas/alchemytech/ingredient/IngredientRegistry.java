package sirttas.alchemytech.ingredient;

import java.util.Map;

import com.google.common.collect.BiMap;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.PersistentRegistryManager;
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
		// TODO: use registry builder
		registry = PersistentRegistryManager.createRegistry(INGREDIENTS, Ingredient.class, null, MIN_INGREDIENT_ID,
				MAX_INGREDIENT_ID, true, IngredientsCallbacks.INSTANCE, IngredientsCallbacks.INSTANCE,
				IngredientsCallbacks.INSTANCE);
		AlchemyTech.T.info("Ingredients registry loaded.");
	}

	static final FMLControlledNamespacedRegistry<Ingredient> getIngredienRegistry() {
		return getInstance().registry;
	}

	private static class IngredientsCallbacks implements IForgeRegistry.AddCallback<Ingredient>,
			IForgeRegistry.ClearCallback<Ingredient>, IForgeRegistry.CreateCallback<Ingredient> {
		static final IngredientsCallbacks INSTANCE = new IngredientsCallbacks();

		@Override
		public void onAdd(Ingredient item, int blockId, Map<ResourceLocation, ?> slaves) {
		}

		public void onClear(Map<ResourceLocation, ?> slaveset) {
		}

		public void onCreate(Map<ResourceLocation, ?> slaveset) {
		}

		@Override
		public void onCreate(Map<ResourceLocation, ?> slaveset,
				BiMap<ResourceLocation, ? extends IForgeRegistry<?>> registries) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onClear(IForgeRegistry<Ingredient> is, Map<ResourceLocation, ?> slaveset) {
			// TODO Auto-generated method stub

		}
	}
}
