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

	private final List<AxisAlignedBB> boxes;

	private static boolean doesVectorColide(AxisAlignedBB bb, Vec3d vec) {
		return vec.xCoord >= bb.minX && vec.yCoord >= bb.minY && vec.zCoord >= bb.minZ && vec.xCoord <= bb.maxX
				&& vec.yCoord <= bb.maxY && vec.zCoord <= bb.maxZ;
	}

	public BlockBrassPipe() {
		super(ConfigBrassPipe.NAME, Material.IRON);
		boxes = new ArrayList<AxisAlignedBB>();
		boxes.add(ConfigBrassPipe.BASE_AABB);
		boxes.add(ConfigBrassPipe.EAST_AABB);
		boxes.add(ConfigBrassPipe.DOWN_AABB);
		boxes.add(ConfigBrassPipe.NORTH_AABB);
		boxes.add(ConfigBrassPipe.WEST_AABB);
		boxes.add(ConfigBrassPipe.UP_AABB);
		boxes.add(ConfigBrassPipe.SOUTH_AABB);
		this.setTickRandomly(true);
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
		TileBrassPipe te = (TileBrassPipe) worldIn.getTileEntity(pos);

		if (te == null) {
			return state.withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE)
					.withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE)
					.withProperty(UP, Boolean.FALSE).withProperty(DOWN, Boolean.FALSE);
		}

		return state.withProperty(NORTH, Boolean.valueOf(te.isConnectedTo(EnumFacing.NORTH)))
				.withProperty(EAST, Boolean.valueOf(te.isConnectedTo(EnumFacing.EAST)))
				.withProperty(SOUTH, Boolean.valueOf(te.isConnectedTo(EnumFacing.SOUTH)))
				.withProperty(WEST, Boolean.valueOf(te.isConnectedTo(EnumFacing.WEST)))
				.withProperty(UP, Boolean.valueOf(te.isConnectedTo(EnumFacing.UP)))
				.withProperty(DOWN, Boolean.valueOf(te.isConnectedTo(EnumFacing.DOWN)));
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
			if (isRendered(box, collisionBlockState, worldIn, pos)) {
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
		if (box.equals(ConfigBrassPipe.DOWN_AABB)) {
			return EnumFacing.DOWN;
		} else if (box.equals(ConfigBrassPipe.UP_AABB)) {
			return EnumFacing.UP;
		} else if (box.equals(ConfigBrassPipe.NORTH_AABB)) {
			return EnumFacing.NORTH;
		} else if (box.equals(ConfigBrassPipe.SOUTH_AABB)) {
			return EnumFacing.SOUTH;
		} else if (box.equals(ConfigBrassPipe.WEST_AABB)) {
			return EnumFacing.WEST;
		} else if (box.equals(ConfigBrassPipe.EAST_AABB)) {
			return EnumFacing.EAST;
		}
		return null;
	}

	private boolean isRendered(AxisAlignedBB box, IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IBlockState actualState = this.getActualState(state, worldIn, pos);

		if (actualState.getBlock() == this) {
			if (box.equals(ConfigBrassPipe.BASE_AABB)) {
				return true;
			} else if (box.equals(ConfigBrassPipe.DOWN_AABB) && actualState.getValue(DOWN).booleanValue()) {
				return true;
			} else if (box.equals(ConfigBrassPipe.UP_AABB) && actualState.getValue(UP).booleanValue()) {
				return true;
			} else if (box.equals(ConfigBrassPipe.NORTH_AABB) && actualState.getValue(NORTH).booleanValue()) {
				return true;
			} else if (box.equals(ConfigBrassPipe.SOUTH_AABB) && actualState.getValue(SOUTH).booleanValue()) {
				return true;
			} else if (box.equals(ConfigBrassPipe.WEST_AABB) && actualState.getValue(WEST).booleanValue()) {
				return true;
			} else if (box.equals(ConfigBrassPipe.EAST_AABB) && actualState.getValue(EAST).booleanValue()) {
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
				if (doesVectorColide(box.offset(pos), hit) && isRendered(box, state, worldIn, pos)) {
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

		if (face != null) {
			return pipe.activatePipe(face);
		}
		return false;
	}

}
