package sirttas.alchemytech.block;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlowingEssence extends Block {

	public static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0, 0, 0, 16 * BIT_SIZE, 4 * BIT_SIZE,
			16 * BIT_SIZE);

	protected BlockGlowingEssence() {
		super(Material.BARRIER);
		this.setRegistryName("glowingEssence");
		this.setUnlocalizedName("glowingEssence");
		this.setBlockUnbreakable();
		this.setResistance(6000001.0F);
		this.translucent = true;
		this.setLightLevel(0.8F);
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BASE_AABB;
	}

	/**
	 * The type of render function called. 3 for standard block models, 2 for
	 * TESR's, 1 for liquids, -1 is no render
	 */
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}
