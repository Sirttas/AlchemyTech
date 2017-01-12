package sirttas.alchemytech.ingredient;

import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sirttas.alchemytech.block.instrument.shaker.TileShaker;
import sirttas.alchemytech.ingredient.recipe.IngredientRecipeRegistry;
import sirttas.alchemytech.ingredient.recipe.SingleSlotIngredientRecipe;
import sirttas.alchemytech.ingredient.recipe.instrument.MixerRecipe;

public class Ingredients {

	public static ItemIngredient redstone;
	public static ItemIngredient glowstone;
	public static ItemIngredient lapys;
	public static ItemIngredient gunpowder;
	public static ItemIngredient sugar;
	public static ItemIngredient netherWart;
	public static ItemIngredient chorus;
	public static MultipleItemIngredient organicMatter;
	public static Ingredient alteration;
	public static Ingredient energized;
	public static Ingredient glowing;
	public static Ingredient salt;
	public static Ingredient oil;
	public static Ingredient waste;

	public static void preInit() {
		redstone = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("redstone", new ItemStack(Items.REDSTONE)).setColor(0x720000));
		glowstone = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("glowstone", new ItemStack(Items.GLOWSTONE_DUST)).setColor(0xD2D200));
		lapys = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("lapys", new ItemStack(Items.DYE, 1, 4)).setColor(0x345EC3));
		gunpowder = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("gunpowder", new ItemStack(Items.GUNPOWDER)).setColor(0x727272));
		sugar = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("sugar", new ItemStack(Items.SUGAR)).setColor(0xEAEAEA));
		netherWart = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("netherWart", new ItemStack(Items.NETHER_WART)).setColor(0xA62530));
		chorus = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("chorus", new ItemStack(Items.CHORUS_FRUIT)).setColor(0x5F395F));
		organicMatter = (MultipleItemIngredient) GameRegistry.register(new MultipleItemIngredient("organicMatter",
				new ItemStack[] { new ItemStack(Items.BEEF), new ItemStack(Items.PORKCHOP) }).setColor(0x66000A));
		alteration = GameRegistry.register(new Ingredient("alteration").setColor(0x8F395F));
		energized = GameRegistry.register(new Ingredient("energized").setColor(0xCF395F));
		glowing = GameRegistry.register(new Ingredient("glowing").setColor(0xCF895F));
		salt = GameRegistry.register(new Ingredient("salt").setColor(0xE0E0AA));
		oil = GameRegistry.register(new Ingredient("oil").setColor(0xDBE2A3));
		waste = GameRegistry.register(new Ingredient("waste").setColor(0x50585B));
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
		IngredientRecipeRegistry.register(
				new SingleSlotIngredientRecipe<TileShaker>(alteration, new Ingredient[] { netherWart, chorus }));
		IngredientRecipeRegistry.register(
				new SingleSlotIngredientRecipe<TileShaker>(energized, new Ingredient[] { alteration, redstone }));
		IngredientRecipeRegistry.register(
				new SingleSlotIngredientRecipe<TileShaker>(glowing, new Ingredient[] { alteration, glowstone }));
		IngredientRecipeRegistry
				.register(new SingleSlotIngredientRecipe<TileShaker>(salt, new Ingredient[] { alteration, sugar }));
		IngredientRecipeRegistry
				.register(new SingleSlotIngredientRecipe<TileShaker>(new Ingredient[] { redstone, redstone, redstone },
						new Ingredient[] { energized, sugar, sugar, sugar }));
		IngredientRecipeRegistry.register(
				new SingleSlotIngredientRecipe<TileShaker>(new Ingredient[] { glowstone, glowstone, glowstone },
						new Ingredient[] { glowing, sugar, sugar, sugar }));
		IngredientRecipeRegistry.register(new SingleSlotIngredientRecipe<TileShaker>(new Ingredient[] { oil, waste },
				new Ingredient[] { organicMatter }));

		while (iterator.hasNext()) {
			Ingredient ingredient = iterator.next();

			if (ingredient instanceof IPostInitIngredient) {
				((IPostInitIngredient) ingredient).postInit();
			}
		}
	}
}
