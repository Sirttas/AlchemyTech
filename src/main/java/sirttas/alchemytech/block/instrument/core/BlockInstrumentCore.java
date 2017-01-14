package sirttas.alchemytech.block.instrument.core;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.BlockATContainer;

public class BlockInstrumentCore extends BlockATContainer {

	public BlockInstrumentCore() {
		super(ConfigInstrumentCore.NAME, TileInstrumentCore.class);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileInstrumentCore instrumentCore = (TileInstrumentCore) world.getTileEntity(pos);

		if (instrumentCore != null && (heldItem == null || TileEntityFurnace.isItemFuel(heldItem))) {
			return this.onSlotActivated(instrumentCore, player, heldItem, 0);
		}
		return false;

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileInstrumentCore();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		super.initModel();
		ClientRegistry.bindTileEntitySpecialRenderer(TileInstrumentCore.class, new InstrumentCoreRenderer());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double x = pos.getX() + (5 + rand.nextDouble() * 6) * BIT_SIZE;
		double y = pos.getY() + 8 * BIT_SIZE;
		double z = pos.getZ() + (5 + rand.nextDouble() * 6) * BIT_SIZE;
		TileInstrumentCore instrumentCore = (TileInstrumentCore) worldIn.getTileEntity(pos);

		if (instrumentCore != null && instrumentCore.isBurning()) {
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

}
