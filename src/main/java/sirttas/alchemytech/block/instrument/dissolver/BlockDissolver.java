package sirttas.alchemytech.block.instrument.dissolver;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.instrument.BlockInstrument;
import sirttas.alchemytech.block.tile.instrument.IInstrument;
import sirttas.alchemytech.item.ItemPreparation;
import sirttas.alchemytech.particle.ParticleMixerBubble;

public class BlockDissolver extends BlockInstrument {

	public BlockDissolver() {
		super(ConfigDissolver.NAME, TileDissolver.class);
		addBoundingBox(FULL_BLOCK_AABB);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDissolver();
	}

	@Override
	protected boolean onBoundingBoxActivated(AxisAlignedBB boundingBox, IInstrument instrument, EntityPlayer player,
			ItemStack heldItem) {
		if (heldItem == null || heldItem.getItem() instanceof ItemPreparation) {
			return onSlotActivated(instrument, player, heldItem, 0);
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double x = pos.getX() + (5 + rand.nextDouble() * 6) * BIT_SIZE;
		double y = pos.getY() + 10 * BIT_SIZE;
		double z = pos.getZ() + (5 + rand.nextDouble() * 6) * BIT_SIZE;
		TileDissolver dissolver = (TileDissolver) worldIn.getTileEntity(pos);

		if (dissolver != null && dissolver.getProgress() > 0) {
			Minecraft.getMinecraft().effectRenderer
					.addEffect(new ParticleMixerBubble(worldIn, x, y, z, 0.0D, 1.0D, 0.0D));
		}
	}

}
