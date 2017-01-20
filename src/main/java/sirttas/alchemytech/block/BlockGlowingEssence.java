package sirttas.alchemytech.block;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sirttas.alchemytech.AlchemyTech;

public class BlockGlowingEssence extends Block {

	public static final PropertyBool DECAY = PropertyBool.create("decay");

	public static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0, 0, 0, 16 * BIT_SIZE, 1 * BIT_SIZE,
			16 * BIT_SIZE);

	protected BlockGlowingEssence() {
		super(Material.BARRIER);
		this.setRegistryName("glowingEssence");
		this.setUnlocalizedName("glowingEssence");
		this.setBlockUnbreakable();
		this.setResistance(6000001.0F);
		this.translucent = true;
		this.setLightLevel(0.8F);
		this.setTickRandomly(true);
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

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (state.getValue(DECAY).booleanValue() == false) {
			worldIn.setBlockState(pos, state.withProperty(DECAY, Boolean.valueOf(true)));
		} else {
			worldIn.setBlockToAir(pos);
			AlchemyTech.T.info("tick");
		}
	}

	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World worldIn) {
		return 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		BlockStateContainer container = new BlockStateContainer(this, DECAY);

		container.getBaseState().withProperty(DECAY, Boolean.valueOf(false));
		return container;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) {

		return this.getDefaultState().withProperty(DECAY, meta == 1 ? true : false);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(DECAY).booleanValue() == true ? 1 : 0;
	}

}
