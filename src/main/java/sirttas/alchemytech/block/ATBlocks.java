package sirttas.alchemytech.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import sirttas.alchemytech.block.instrument.boiler.BlockBoiler;
import sirttas.alchemytech.block.instrument.centrifuge.BlockCentrifuge;
import sirttas.alchemytech.block.instrument.core.BlockInstrumentCore;
import sirttas.alchemytech.block.instrument.dissolver.BlockDissolver;
import sirttas.alchemytech.block.instrument.extractor.BlockExtractor;
import sirttas.alchemytech.block.instrument.mixer.BlockMixer;
import sirttas.alchemytech.block.instrument.shaker.BlockShaker;
import sirttas.alchemytech.block.jar.BlockIngredientJar;
import sirttas.alchemytech.block.pipe.BlockBrassPipe;
import sirttas.alchemytech.item.ATItems;

public class ATBlocks {
	public static BlockInstrumentCore instrumentCore;
	public static BlockMixer mixer;
	public static BlockShaker shaker;
	public static BlockExtractor extractor;
	public static BlockBoiler boiler;
	public static BlockCentrifuge centrifuge;
	public static BlockDissolver dissolver;
	public static BlockAT copperOre;
	public static BlockAT zincOre;
	public static BlockAT copperBlock;
	public static BlockAT zincBlock;
	public static BlockAT brassBlock;
	public static BlockBrassPipe brassPipe;
	public static BlockGlowingEssence glowingEssence;
	public static BlockIngredientJar ingredientJar;

	public static List<IBlockAT> blocks = new ArrayList<IBlockAT>();

	public static void preInit() {
		instrumentCore = new BlockInstrumentCore();
		mixer = new BlockMixer();
		shaker = new BlockShaker();
		extractor = new BlockExtractor();
		boiler = new BlockBoiler();
		centrifuge = new BlockCentrifuge();
		dissolver = new BlockDissolver();
		copperOre = new BlockAT("copperOre", Material.IRON);
		zincOre = new BlockAT("zincOre", Material.IRON);
		copperBlock = new BlockAT("copperBlock", Material.IRON);
		zincBlock = new BlockAT("zincBlock", Material.IRON);
		brassBlock = new BlockAT("brassBlock", Material.IRON);
		brassPipe = new BlockBrassPipe();
		glowingEssence = new BlockGlowingEssence();
		ingredientJar = new BlockIngredientJar();

		for (IBlockAT block : blocks) {
			block.register();
		}

		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("oreZinc", zincOre);
		OreDictionary.registerOre("blockCopper", copperBlock);
		OreDictionary.registerOre("blockZinc", zincBlock);
		OreDictionary.registerOre("blockBrass", brassBlock);
	}

	public static void init() {
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for (IBlockAT block : blocks) {
			block.initModel();
		}
	}

	public static void postInit() {

		GameRegistry.addRecipe(new ShapedOreRecipe(instrumentCore, new Object[] { "III", "I I", "ADA", 'A',
				new ItemStack(Blocks.STONE, 1, 5), 'D', new ItemStack(Blocks.STONE, 1, 3), 'I', Items.IRON_INGOT }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ATBlocks.brassPipe, 2),
				new Object[] { "B", "B", "B", 'B', "ingotBrass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ATBlocks.ingredientJar, 2),
				new Object[] { "GWG", "G G", "GBG", 'B', ATItems.bowl, 'G', Blocks.GLASS_PANE, 'W', Blocks.PLANKS }));

		GameRegistry.addRecipe(new ShapedOreRecipe(mixer, new Object[] { "PbP", "UIU", "IBI", 'I', Items.IRON_INGOT,
				'B', Blocks.IRON_BLOCK, 'b', "ingotBrass", 'U', ATItems.bowl, 'P', brassPipe }));
		GameRegistry.addRecipe(new ShapedOreRecipe(extractor, new Object[] { " P ", "bUb", "IBI", 'I', Items.IRON_INGOT,
				'B', Blocks.IRON_BLOCK, 'b', "ingotBrass", 'U', ATItems.bowl, 'P', brassPipe }));
		GameRegistry.addRecipe(new ShapedOreRecipe(boiler, new Object[] { "IUI", "bUb", "IBI", 'I', Items.IRON_INGOT,
				'B', Blocks.IRON_BLOCK, 'b', "ingotBrass", 'U', ATItems.bowl }));
		GameRegistry.addRecipe(new ShapedOreRecipe(centrifuge,
				new Object[] { "pOp", "bUb", "IBI", 'I', Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'b', "ingotBrass",
						'O', ATItems.centrifugeBarrel, 'U', ATItems.bowl, 'p', Blocks.PISTON }));
		GameRegistry.addRecipe(new ShapedOreRecipe(shaker, new Object[] { "pUp", "IBI", 'B', Blocks.IRON_BLOCK, 'I',
				"ingotBrass", 'U', ATItems.bowl, 'p', Blocks.PISTON }));

		GameRegistry
				.addRecipe(new ShapedOreRecipe(brassBlock, new Object[] { "III", "III", "III", 'I', "ingotBrass" }));
		GameRegistry
				.addRecipe(new ShapedOreRecipe(copperBlock, new Object[] { "III", "III", "III", 'I', "ingotCopper" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(zincBlock, new Object[] { "III", "III", "III", 'I', "ingotZinc" }));
	}
}
