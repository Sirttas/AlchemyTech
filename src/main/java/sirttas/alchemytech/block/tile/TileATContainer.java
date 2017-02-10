package sirttas.alchemytech.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class TileATContainer extends TileAT implements IInventory {
	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer palyer) {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack oldStack = getStackInSlot(slot);
		int newCount = (count > oldStack.stackSize) ? oldStack.stackSize : count;

		oldStack.stackSize -= newCount;
		ItemStack stack = oldStack.copy();
		stack.stackSize = newCount;
		if (stack.stackSize <= 0) {
			this.removeStackFromSlot(slot);
			return null;
		}
		return stack;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName(), new Object[0]);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.clear();
		readFromNBT(packet.getNbtCompound());
	}
}
