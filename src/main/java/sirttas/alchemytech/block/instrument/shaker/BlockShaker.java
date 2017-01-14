package sirttas.alchemytech.block.instrument.shaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.instrument.BlockInstrument;
import sirttas.alchemytech.block.tile.instrument.IInstrument;
import sirttas.alchemytech.item.ItemPreparation;

public class BlockShaker extends BlockInstrument {

	public BlockShaker() {
		super(ConfigShaker.NAME, TileShaker.class);
		addBoundingBox(FULL_BLOCK_AABB);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShaker();
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
	public void initModel() {
		super.initModel();
		ClientRegistry.bindTileEntitySpecialRenderer(TileShaker.class, new ShakerRenderer());
	}

}
