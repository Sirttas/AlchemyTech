package sirttas.alchemytech.block.instrument.extractor.top;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sirttas.alchemytech.block.BlockATContainer;

public class BlockExtractorTop extends BlockATContainer {

	public BlockExtractorTop() {
		super(ConfigExtractorTop.NAME, TileExtractorTop.class);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileExtractorTop();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileExtractorTop top = (TileExtractorTop) world.getTileEntity(pos);
		boolean returnValue = false;

		if (heldItem == null) {
			for (int index = 0; index < top.getSizeInventory(); index++) {
				ItemStack stack = top.getStackInSlot(index);

				if (stack != null) {
					if (!world.isRemote) {
						EntityItem invItem = new EntityItem(world, player.posX, player.posY + 0.25, player.posZ, stack);

						world.spawnEntityInWorld(invItem);
					}
					top.setInventorySlotContents(index, null);
					returnValue = true;
				}
			}
		}
		return returnValue;

	}
}
