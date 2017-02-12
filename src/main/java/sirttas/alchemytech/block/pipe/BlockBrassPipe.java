package sirttas.alchemytech.block.pipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
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

	public static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(6.5 * BIT_SIZE, 6.5 * BIT_SIZE, 6.5 * BIT_SIZE,
			9.5 * BIT_SIZE, 9.5 * BIT_SIZE, 9.5 * BIT_SIZE);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0, 7 * BIT_SIZE, 7 * BIT_SIZE, 6.5 * BIT_SIZE,
			9 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(7 * BIT_SIZE, 0, 7 * BIT_SIZE, 9 * BIT_SIZE,
			6.5 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(7 * BIT_SIZE, 7 * BIT_SIZE, 0, 9 * BIT_SIZE,
			9 * BIT_SIZE, 6.5 * BIT_SIZE);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(9.5 * BIT_SIZE, 7 * BIT_SIZE, 7 * BIT_SIZE,
			16 * BIT_SIZE, 9 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(7 * BIT_SIZE, 9.5 * BIT_SIZE, 7 * BIT_SIZE,
			9 * BIT_SIZE, 16 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(7 * BIT_SIZE, 7 * BIT_SIZE, 9.5 * BIT_SIZE,
			9 * BIT_SIZE, 9 * BIT_SIZE, 16 * BIT_SIZE);

	private final List<AxisAlignedBB> boxes;

	private static boolean doesVectorColide(AxisAlignedBB bb, Vec3d vec) {
		return vec.xCoord >= bb.minX && vec.yCoord >= bb.minY && vec.zCoord >= bb.minZ && vec.xCoord <= bb.maxX
				&& vec.yCoord <= bb.maxY && vec.zCoord <= bb.maxZ;
	}

	public BlockBrassPipe() {
		super(NAME, Material.IRON);
		boxes = new ArrayList<AxisAlignedBB>();
		boxes.add(BASE_AABB);
		boxes.add(EAST_AABB);
		boxes.add(DOWN_AABB);
		boxes.add(NORTH_AABB);
		boxes.add(WEST_AABB);
		boxes.add(UP_AABB);
		boxes.add(SOUTH_AABB);
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

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		((TileBrassPipe) worldIn.getTileEntity(pos)).init();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		((TileBrassPipe) worldIn.getTileEntity(pos)).destroy();
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		((TileBrassPipe) worldIn.getTileEntity(pos)).refresh();
	}

	protected void addBoundingBox(AxisAlignedBB box) {
		boxes.add(box);
	}

	@Override
	@Nullable
	public RayTraceResult collisionRayTrace(IBlockState collisionBlockState, World worldIn, BlockPos pos, Vec3d start,
			Vec3d end) {
		RayTraceResult lastResult = null;
		double lastLength = 0;

		for (final AxisAlignedBB box : boxes) {
			if (isRendered(box, collisionBlockState)) {
				final RayTraceResult result = rayTrace(pos, start, end, box);

				if (result != null && result.typeOfHit == Type.BLOCK) {
					final double length = result.hitVec.subtract(start).lengthVector();

					if (lastLength == 0 || length < lastLength) {
						lastResult = result;
						lastLength = length;
					}
				}
			}
		}

		return lastResult;
	}

	private EnumFacing getFace(AxisAlignedBB box) {
		if (box.equals(DOWN_AABB)) {
			return EnumFacing.DOWN;
		} else if (box.equals(UP_AABB)) {
			return EnumFacing.UP;
		} else if (box.equals(NORTH_AABB)) {
			return EnumFacing.NORTH;
		} else if (box.equals(SOUTH_AABB)) {
			return EnumFacing.SOUTH;
		} else if (box.equals(WEST_AABB)) {
			return EnumFacing.WEST;
		} else if (box.equals(EAST_AABB)) {
			return EnumFacing.EAST;
		}
		return null;
	}

	private boolean isRendered(AxisAlignedBB box, IBlockState state) {
		if (state.getBlock() == this) {
			if (box.equals(BASE_AABB)) {
				return true;
			} else if (box.equals(DOWN_AABB) && state.getValue(DOWN).booleanValue()) {
				return true;
			} else if (box.equals(UP_AABB) && state.getValue(UP).booleanValue()) {
				return true;
			} else if (box.equals(NORTH_AABB) && state.getValue(NORTH).booleanValue()) {
				return true;
			} else if (box.equals(SOUTH_AABB) && state.getValue(SOUTH).booleanValue()) {
				return true;
			} else if (box.equals(WEST_AABB) && state.getValue(WEST).booleanValue()) {
				return true;
			} else if (box.equals(EAST_AABB) && state.getValue(EAST).booleanValue()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		final RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;

		if (result != null && result.typeOfHit == Type.BLOCK) {
			final Vec3d hit = result.hitVec;

			for (final AxisAlignedBB box : boxes) {
				if (doesVectorColide(box.offset(pos), hit) && isRendered(box, state)) {
					return box;
				}
			}
		}

		return super.getBoundingBox(state, worldIn, pos);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return getBoundingBox(state, worldIn, pos).offset(pos);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		final TileBrassPipe pipe = (TileBrassPipe) world.getTileEntity(pos);

		if (pipe != null) {
			final AxisAlignedBB boundingBox = getBoundingBox(state, world, pos);

			return onBoundingBoxActivated(boundingBox, pipe, player, heldItem);
		}
		return false;
	}

	protected boolean onBoundingBoxActivated(AxisAlignedBB boundingBox, TileBrassPipe pipe, EntityPlayer player,
			ItemStack heldItem) {
		EnumFacing face = getFace(boundingBox);

		return pipe.activatePipe(face);
	}

}
