package sirttas.alchemytech.block.jar;

import javax.annotation.Nullable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.BlockAT;
import sirttas.alchemytech.block.instrument.ConfigInstrument;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.item.ItemPreparation;

public class BlockIngredientJar extends BlockAT implements ITileEntityProvider {

	public BlockIngredientJar() {
		super(ConfigIngredientJar.NAME, ConfigInstrument.DEFAULT_MATERIAL);
		this.setHarvestLevel("Pickaxe", 1);
		this.setHardness(2F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return ConfigIngredientJar.BOUNDING_BOX;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileIngredientJar();
	}

	public int getIngredientsColor(BlockPos pos) {
		// TODO Auto-generated method stub
		return 0xFF0000;
	}

	@Override
	public void register() {
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		GameRegistry.registerTileEntity(TileIngredientJar.class, this.getRegistryName() + "TileEntity");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileIngredientJar jar = (TileIngredientJar) world.getTileEntity(pos);
		Ingredient ingredient = jar.getIngredient(0);

		if (heldItem.getItem() instanceof ItemPreparation) {
			ItemPreparation preparation = (ItemPreparation) heldItem.getItem();

			if (ingredient == null) {
				jar.addIngredient(preparation.removeIngredientAt(heldItem, 0));
				return true;
			} else if (preparation.removeIngredient(heldItem, ingredient) != null) {
				jar.addIngredient(ingredient);
				if (preparation.getIngredientCount(heldItem) <= 0) {
					player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
				}
				return true;
			}
		}
		return false;

	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		TileIngredientJar jar = (TileIngredientJar) worldIn.getTileEntity(pos);
		ItemStack heldItem = playerIn.getHeldItemMainhand();

		if (heldItem.getItem() instanceof ItemPreparation) {
			ItemPreparation preparation = (ItemPreparation) heldItem.getItem();

			if (preparation.getIngredientCount(heldItem) < ItemPreparation.MAX_INGREDIENTS) {
				preparation.addIngredient(heldItem, jar.removeIngredient(0));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		super.initModel();
		ClientRegistry.bindTileEntitySpecialRenderer(TileIngredientJar.class, new IngredientJarRenderer());
	}

}
