package sirttas.alchemytech.block.jar;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sirttas.alchemytech.block.BlockAT;
import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class BlockIngredientJar extends BlockAT implements ITileEntityProvider {

	public static final String NAME = "ingredientJar";
	private Class<? extends TileEntity> tileEntity = null;
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.3, 0, 0.3, 0.7, 0.5, 0.7);

	public BlockIngredientJar() {
		super(NAME, ConfigInstrument.DEFAULT_MATERIAL);
		this.tileEntity = TileIngredientJar.class;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
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
		if (tileEntity != null) {
			GameRegistry.registerTileEntity(tileEntity, this.getRegistryName() + "TileEntity");
		}
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

}
