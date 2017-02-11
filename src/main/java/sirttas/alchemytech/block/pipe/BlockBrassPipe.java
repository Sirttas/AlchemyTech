package sirttas.alchemytech.block.pipe;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sirttas.alchemytech.block.ATBlocks;
import sirttas.alchemytech.block.BlockAT;
import sirttas.alchemytech.block.tile.api.IIngredientReceiver;
import sirttas.alchemytech.block.tile.api.IIngredientSender;

public class BlockBrassPipe extends BlockAT implements ITileEntityProvider {

	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");

	private static final String NAME = "brassPipe";

	public BlockBrassPipe() {
		super(NAME, Material.IRON);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		TileEntity entity = worldIn.getTileEntity(pos);

		return block == ATBlocks.brassPipe || entity instanceof IIngredientSender
				|| entity instanceof IIngredientReceiver;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, WEST, SOUTH, UP, DOWN });
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	/**
	 * Get the actual Block state of this Block at the given position. This
	 * applies properties not visible in the metadata, such as fence
	 * connections.
	 */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north())))
				.withProperty(EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east())))
				.withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south())))
				.withProperty(WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west())))
				.withProperty(UP, Boolean.valueOf(this.canConnectTo(worldIn, pos.up())))
				.withProperty(DOWN, Boolean.valueOf(this.canConnectTo(worldIn, pos.down())));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBrassPipe();
	}

	@Override
	public void register() {
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		GameRegistry.registerTileEntity(TileBrassPipe.class, this.getRegistryName() + "TileEntity");
	}

}
