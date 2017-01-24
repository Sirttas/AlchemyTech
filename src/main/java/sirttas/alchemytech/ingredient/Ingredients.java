package sirttas.alchemytech.ingredient;

import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sirttas.alchemytech.ingredient.api.IInitIngredient;
import sirttas.alchemytech.ingredient.api.IPostInitIngredient;
import sirttas.alchemytech.ingredient.essence.DiamondEssenceIngredient;
import sirttas.alchemytech.ingredient.essence.EnergizedEssenceIngredient;
import sirttas.alchemytech.ingredient.essence.EssenceIngredient;
import sirttas.alchemytech.ingredient.essence.FireEssenceIngredient;
import sirttas.alchemytech.ingredient.essence.FortuneEssenceIngredient;
import sirttas.alchemytech.ingredient.essence.GlowingEssenceIngredient;
import sirttas.alchemytech.ingredient.item.ItemIngredient;
import sirttas.alchemytech.ingredient.item.MultipleItemIngredient;
import sirttas.alchemytech.ingredient.item.OreDictionaryItemIngredient;
import sirttas.alchemytech.ingredient.recipe.IngredientRecipeRegistry;
import sirttas.alchemytech.ingredient.recipe.instrument.CentrifugeRecipe;
import sirttas.alchemytech.ingredient.recipe.instrument.ExtractorRecipe;
import sirttas.alchemytech.ingredient.recipe.instrument.MixerRecipe;
import sirttas.alchemytech.ingredient.recipe.instrument.ShakerRecipe;

public class Ingredients {

	public static ItemIngredient redstone;
	public static ItemIngredient glowstone;
	public static ItemIngredient lapys;
	public static ItemIngredient gunpowder;
	public static ItemIngredient sugar;
	public static ItemIngredient netherWart;
	public static ItemIngredient chorus;
	public static ItemIngredient iron;
	public static ItemIngredient gold;
	public static ItemIngredient diamond;
	public static ItemIngredient blaze;
	public static ItemIngredient quartz;
	public static MultipleItemIngredient coal;
	public static MultipleItemIngredient organicMatter;
	public static EssenceIngredient alteration;
	public static EssenceIngredient brewing;
	public static DiamondEssenceIngredient diamondEssence;
	public static EnergizedEssenceIngredient energized;
	public static GlowingEssenceIngredient glowing;
	public static FireEssenceIngredient fire;
	public static FortuneEssenceIngredient fortune;
	public static OreDictionaryItemIngredient salt;
	public static OreDictionaryItemIngredient sulfur;
	public static OreDictionaryItemIngredient salpetre;
	public static OreDictionaryItemIngredient graphite;
	public static Ingredient oil;
	public static Ingredient diamondBit;
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
		iron = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("iron", new ItemStack(Items.IRON_INGOT)).setColor(0xBDC3CC));
		gold = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("gold", new ItemStack(Items.GOLD_INGOT)).setColor(0xD4E07D));
		diamond = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("diamond", new ItemStack(Items.DIAMOND)).setColor(0xBECDE5));
		blaze = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("blaze", new ItemStack(Items.BLAZE_POWDER)).setColor(0xAD8203));
		quartz = (ItemIngredient) GameRegistry
				.register(new ItemIngredient("quartz", new ItemStack(Items.QUARTZ)).setColor(0xFDFFEF));

		coal = ((MultipleItemIngredient) GameRegistry.register(new MultipleItemIngredient("coal",
				new ItemStack[] { new ItemStack(Items.COAL), new ItemStack(Items.COAL, 1, 1) }).setColor(0x1F2021)))
						.setReturnStackIndex(0);
		organicMatter = (MultipleItemIngredient) GameRegistry
				.register(
						new MultipleItemIngredient("organicMatter",
								new ItemStack[] { new ItemStack(Items.BEEF), new ItemStack(Items.PORKCHOP),
										new ItemStack(Items.WHEAT), new ItemStack(Items.ROTTEN_FLESH) })
												.setColor(0x66000A));

		alteration = (EssenceIngredient) GameRegistry.register(new EssenceIngredient("alteration").setColor(0x8F395F));
		brewing = (EssenceIngredient) GameRegistry.register(new EssenceIngredient("brewing").setColor(0x82624C));
		diamondEssence = (DiamondEssenceIngredient) GameRegistry
				.register(new DiamondEssenceIngredient().setColor(0xBECDE5));
		energized = (EnergizedEssenceIngredient) GameRegistry
				.register(new EnergizedEssenceIngredient().setColor(0xCF395F));
		glowing = (GlowingEssenceIngredient) GameRegistry.register(new GlowingEssenceIngredient().setColor(0xCF895F));
		fire = (FireEssenceIngredient) GameRegistry.register(new FireEssenceIngredient().setColor(0xDDB849));
		fortune = (FortuneEssenceIngredient) GameRegistry.register(new FortuneEssenceIngredient().setColor(0xBAD0f4));

		salt = (OreDictionaryItemIngredient) GameRegistry
				.register(new OreDictionaryItemIngredient("salt", "foodSalt").setColor(0xE0E0AA));
		graphite = (OreDictionaryItemIngredient) GameRegistry
				.register(new OreDictionaryItemIngredient("graphite", "ingotGraphite").setColor(0x4E5156));
		sulfur = (OreDictionaryItemIngredient) GameRegistry
				.register(new OreDictionaryItemIngredient("sulfur", "dustSulfur").setColor(0xE5F26F));
		salpetre = (OreDictionaryItemIngredient) GameRegistry
				.register(new OreDictionaryItemIngredient("salpetre", "dustSalpeter").setColor(0xFDFFEF));

		oil = GameRegistry.register(new Ingredient("oil").setColor(0xDBE2A3));
		diamondBit = GameRegistry.register(new Ingredient("diamondBit").setColor(0xBECDE5));
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

		initRecipes();

		while (iterator.hasNext()) {
			Ingredient ingredient = iterator.next();

			if (ingredient instanceof IPostInitIngredient) {
				((IPostInitIngredient) ingredient).postInit();
			}
		}
	}

	private static void initRecipes() {
		IngredientRecipeRegistry.register(new MixerRecipe());
		IngredientRecipeRegistry.register(new ExtractorRecipe());
		IngredientRecipeRegistry.register(new ShakerRecipe(alteration, new Ingredient[] { netherWart, chorus }));
		IngredientRecipeRegistry.register(new ShakerRecipe(energized, new Ingredient[] { alteration, redstone }));
		IngredientRecipeRegistry.register(new ShakerRecipe(glowing, new Ingredient[] { alteration, glowstone }));
		IngredientRecipeRegistry.register(new ShakerRecipe(salt, new Ingredient[] { alteration, sugar }));
		IngredientRecipeRegistry.register(new ShakerRecipe(sugar, new Ingredient[] { alteration, salt }));
		IngredientRecipeRegistry.register(new ShakerRecipe(iron, new Ingredient[] { graphite, sugar, oil }));
		IngredientRecipeRegistry.register(new ShakerRecipe(gold, new Ingredient[] { alteration, iron }));
		IngredientRecipeRegistry.register(new ShakerRecipe(iron, new Ingredient[] { alteration, gold }));
		IngredientRecipeRegistry.register(new ShakerRecipe(fire, new Ingredient[] { blaze, coal, oil, gunpowder }));
		IngredientRecipeRegistry
				.register(new ShakerRecipe(diamondBit, new Ingredient[] { fire, graphite, graphite, graphite }));
		IngredientRecipeRegistry.register(
				new ShakerRecipe(diamond, new Ingredient[] { diamondBit, diamondBit, diamondBit, diamondBit }));
		IngredientRecipeRegistry.register(new ShakerRecipe(new Ingredient[] { redstone, redstone, redstone },
				new Ingredient[] { energized, sugar, sugar, sugar }));
		IngredientRecipeRegistry.register(new ShakerRecipe(new Ingredient[] { glowstone, glowstone, glowstone },
				new Ingredient[] { glowing, sugar, sugar, sugar }));
		IngredientRecipeRegistry
				.register(new ShakerRecipe(diamondEssence, new Ingredient[] { alteration, diamond, fire }));
		IngredientRecipeRegistry
				.register(new ShakerRecipe(fortune, new Ingredient[] { alteration, diamond, quartz, lapys }));
		IngredientRecipeRegistry.register(new ShakerRecipe(gunpowder, new Ingredient[] { coal, sulfur, salpetre }));
		IngredientRecipeRegistry.register(new ShakerRecipe(quartz, new Ingredient[] { graphite, salpetre }));
		IngredientRecipeRegistry.register(new ShakerRecipe(quartz, new Ingredient[] { alteration, lapys }));
		IngredientRecipeRegistry.register(new ShakerRecipe(lapys, new Ingredient[] { alteration, quartz }));

		IngredientRecipeRegistry.register(new CentrifugeRecipe(new Ingredient[] { oil, waste }, organicMatter));
		IngredientRecipeRegistry.register(new CentrifugeRecipe(new Ingredient[] { graphite, waste }, coal));
		IngredientRecipeRegistry.register(new CentrifugeRecipe(new Ingredient[] { coal, sulfur, salpetre }, gunpowder));
	}
}
