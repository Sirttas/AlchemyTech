package sirttas.alchemytech.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
	public static ItemPipette pipette;
	public static ItemAT brassIngot;
	public static ItemAT copperIngot;
	public static ItemAT zincIngot;
	public static ItemAT bowl;
	public static ItemAT centrifugeBarrel;

	public static void preInit() {
		preparation = GameRegistry.register(new ItemPreparation());
		pipette = GameRegistry.register(new ItemPipette());
		brassIngot = GameRegistry.register(new ItemAT("brassIngot"));
		copperIngot = GameRegistry.register(new ItemAT("copperIngot"));
		zincIngot = GameRegistry.register(new ItemAT("zincIngot"));
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
		pipette.initModel();
		brassIngot.initModel();
		copperIngot.initModel();
		zincIngot.initModel();
		bowl.initModel();
		centrifugeBarrel.initModel();
	}

	@SideOnly(Side.CLIENT)
	public static void initColors() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex > 0 ? -1 : preparation.getIngredientsColor(stack);
			}
		}, new Item[] { preparation });

	}

	public static void postInit() {
		initColors();

		// TODO: add option to disable this craft
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(brassIngot, 4),
				new Object[] { "ingotCopper", "ingotCopper", "ingotCopper", "ingotZinc" }));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(bowl, new Object[] { "I I", " B ", 'B', "ingotBrass", 'I', Items.IRON_INGOT }));
		GameRegistry.addRecipe(new ShapedOreRecipe(centrifugeBarrel,
				new Object[] { "IBI", "I I", "IBI", 'B', bowl, 'I', Items.IRON_INGOT }));
		// TODO: remplace leather with rubber when it is avalable
		GameRegistry.addRecipe(new ShapedOreRecipe(pipette,
				new Object[] { "L  ", " G ", "  G", 'L', Items.LEATHER, 'G', Blocks.GLASS }));

		FurnaceRecipes.instance().addSmeltingRecipeForBlock(ATBlocks.copperOre, new ItemStack(copperIngot), 1.0F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(ATBlocks.zincOre, new ItemStack(zincIngot), 1.0F);
	}
}
