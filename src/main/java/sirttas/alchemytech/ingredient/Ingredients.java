package sirttas.alchemytech.ingredient;

import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sirttas.alchemytech.ingredient.recipe.IngredientRecipeRegistry;
import sirttas.alchemytech.ingredient.recipe.instrument.MixerRecipe;
import sirttas.alchemytech.ingredient.recipe.instrument.ShakerRecipe;

public class Ingredients {

	public static IngredientItem redstone;
	public static IngredientItem glowstone;
	public static IngredientItem lapys;
	public static IngredientItem gunpowder;
	public static IngredientItem sugar;
	public static IngredientItem netherWart;
	public static IngredientItem chorus;
	public static Ingredient alteration;
	public static Ingredient energized;
	public static Ingredient glowing;
	public static Ingredient salt;

	public static void preInit() {
		redstone = (IngredientItem) GameRegistry
				.register(new IngredientItem("redstone", new ItemStack(Items.REDSTONE)).setColor(0x720000));
		glowstone = (IngredientItem) GameRegistry
				.register(new IngredientItem("glowstone", new ItemStack(Items.GLOWSTONE_DUST)).setColor(0xD2D200));
		lapys = (IngredientItem) GameRegistry
				.register(new IngredientItem("lapys", new ItemStack(Items.DYE, 1, 4)).setColor(0x345EC3));
		gunpowder = (IngredientItem) GameRegistry
				.register(new IngredientItem("gunpowder", new ItemStack(Items.GUNPOWDER)).setColor(0x727272));
		sugar = (IngredientItem) GameRegistry
				.register(new IngredientItem("sugar", new ItemStack(Items.SUGAR)).setColor(0xEAEAEA));
		netherWart = (IngredientItem) GameRegistry
				.register(new IngredientItem("netherWart", new ItemStack(Items.NETHER_WART)).setColor(0xA62530));
		chorus = (IngredientItem) GameRegistry
				.register(new IngredientItem("chorus", new ItemStack(Items.CHORUS_FRUIT)).setColor(0x5F395F));
		alteration = GameRegistry.register(new Ingredient("alteration").setColor(0x8F395F));
		energized = GameRegistry.register(new Ingredient("energized").setColor(0xCF395F));
		glowing = GameRegistry.register(new Ingredient("glowing").setColor(0xCF895F));
		salt = GameRegistry.register(new Ingredient("salt").setColor(0xE0E0AA));
	}

	public static void init() {
		Iterator<Ingredient> iterator = Ingredient.REGISTRY.iterator();

		while (iterator.hasNext()) {
			Ingredient ingredient = iterator.next();

			if (ingredient instanceof IInitIngredient) {
				((IInitIngredient) ingredient).init();
			}
		}
	}

	public static void postInit() {
		Iterator<Ingredient> iterator = Ingredient.REGISTRY.iterator();

		IngredientRecipeRegistry.register(new MixerRecipe());
		IngredientRecipeRegistry.register(new ShakerRecipe(alteration, new Ingredient[] { netherWart, chorus }));
		IngredientRecipeRegistry.register(new ShakerRecipe(energized, new Ingredient[] { alteration, redstone }));
		IngredientRecipeRegistry.register(new ShakerRecipe(glowing, new Ingredient[] { alteration, glowstone }));
		IngredientRecipeRegistry.register(new ShakerRecipe(salt, new Ingredient[] { alteration, sugar }));

		while (iterator.hasNext()) {
			Ingredient ingredient = iterator.next();

			if (ingredient instanceof IPostInitIngredient) {
				((IPostInitIngredient) ingredient).postInit();
			}
		}
	}
}
