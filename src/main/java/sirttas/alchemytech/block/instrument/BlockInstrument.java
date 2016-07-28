package sirttas.alchemytech.block.instrument;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
import sirttas.alchemytech.block.BlockATContainer;
import sirttas.alchemytech.block.tile.instrument.IInstrument;

public abstract class BlockInstrument extends BlockATContainer {

	private static boolean doesVectorColide(AxisAlignedBB bb, Vec3d vec) {
		return vec.xCoord >= bb.minX && vec.yCoord >= bb.minY && vec.zCoord >= bb.minZ && vec.xCoord <= bb.maxX
				&& vec.yCoord <= bb.maxY && vec.zCoord <= bb.maxZ;
	}

	private final List<AxisAlignedBB> boxes;

	public BlockInstrument(String name, Class<? extends TileEntity> tileEntity) {
		super(name, tileEntity);
		boxes = new ArrayList<AxisAlignedBB>();
	}

	protected void addBoundingBox(AxisAlignedBB box) {
		boxes.add(box);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
		for (final AxisAlignedBB box : boxes) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, box);
		}
	}

	@Override
	@Nullable
	public RayTraceResult collisionRayTrace(IBlockState collisionBlockState, World worldIn, BlockPos pos, Vec3d start,
			Vec3d end) {
		RayTraceResult lastResult = null;
		double lastLength = 0;

		for (final AxisAlignedBB box : boxes) {
			final RayTraceResult result = rayTrace(pos, start, end, box);

			if (result != null && result.typeOfHit == Type.BLOCK) {
				final double length = result.hitVec.subtract(start).lengthVector();

				if (lastLength == 0 || length < lastLength) {
					lastResult = result;
					lastLength = length;
				}
			}
		}

		return lastResult;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		final RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;

		if (result != null && result.typeOfHit == Type.BLOCK) {
			final Vec3d hit = result.hitVec;

			for (final AxisAlignedBB box : boxes) {
				if (doesVectorColide(box.offset(pos), hit)) {
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
		final IInstrument instrument = (IInstrument) world.getTileEntity(pos);

		if (instrument != null) {
			final AxisAlignedBB boundingBox = getBoundingBox(state, world, pos);

			return onBoundingBoxActivated(boundingBox, instrument, player, heldItem);
		}
		return false;
	}

	//@SuppressWarnings("unused")
	protected boolean onBoundingBoxActivated(AxisAlignedBB boundingBox, IInstrument instrument, EntityPlayer player,
			ItemStack heldItem) {
		return false;
	}

}
