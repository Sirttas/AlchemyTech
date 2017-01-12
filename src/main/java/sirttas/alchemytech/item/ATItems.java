package sirttas.alchemytech.item;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import sirttas.alchemytech.block.ATBlocks;

public class ATItems {
	public static ItemPreparation preparation;
	public static ItemAT brassIngot;
	public static ItemAT copperIngot;
	public static ItemAT zincIngot;
	public static ItemAT pipe;
	public static ItemAT bowl;
	public static ItemAT centrifugeBarrel;

	public static void preInit() {
		preparation = GameRegistry.register(new ItemPreparation());
		brassIngot = GameRegistry.register(new ItemAT("brassIngot"));
		copperIngot = GameRegistry.register(new ItemAT("copperIngot"));
		zincIngot = GameRegistry.register(new ItemAT("zincIngot"));
		pipe = GameRegistry.register(new ItemAT("pipe"));
		bowl = GameRegistry.register(new ItemAT("bowl"));
		centrifugeBarrel = GameRegistry.register(new ItemAT("centrifugeBarrel"));

		OreDictionary.registerOre("ingotBrass", brassIngot);
		OreDictionary.registerOre("ingotCopper", copperIngot);
		OreDictionary.registerOre("ingotZinc", zincIngot);
	}

	public static void init() {
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		preparation.initModel();
		brassIngot.initModel();
		copperIngot.initModel();
		zincIngot.initModel();
		pipe.initModel();
		bowl.initModel();
		centrifugeBarrel.initModel();
	}

	public static void postInit() {
		preparation.initColors();

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(brassIngot, 4),
				new Object[] { "ingotCopper", "ingotCopper", "ingotCopper", zincIngot }));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(pipe, 2), new Object[] { "B", "B", "B", 'B', "ingotBrass" }));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(bowl, new Object[] { "I I", " B ", 'B', "ingotBrass", 'I', Items.IRON_INGOT }));
		GameRegistry.addRecipe(new ShapedOreRecipe(centrifugeBarrel,
				new Object[] { "IBI", "I I", "IBI", 'B', bowl, 'I', Items.IRON_INGOT }));

		FurnaceRecipes.instance().addSmeltingRecipeForBlock(ATBlocks.copperOre, new ItemStack(copperIngot), 1.0F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(ATBlocks.zincOre, new ItemStack(zincIngot), 1.0F);
	}
}
