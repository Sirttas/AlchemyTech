package sirttas.alchemytech.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;

public class ATInventory implements IInventory {

	public static final int DEFAULT_SIZE = 10;

	private static final String TAG_ITEMS = "items";
	private static final String TAG_SLOT = "slot";

	protected ItemStack[] slots;

	protected int size;

	public ATInventory() {
		this(DEFAULT_SIZE);
	}

	public ATInventory(int size) {
		this.size = size;
		slots = new ItemStack[size];
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public int getSizeInventory() {
		return size;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return slots[index];
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
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = slots[index];
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		slots[index] = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

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
	public void clear() {
		for (int i = 0; i < size; i++) {
			slots[i] = null;
		}
	}

	public ItemStack[] getStacks() {
		return slots;
	}

	public void readFromNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = compound.getTagList(TAG_ITEMS, 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte(TAG_SLOT) & 255;

			if (j >= 0 && j < this.slots.length) {
				this.slots[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.slots.length; ++i) {
			if (this.slots[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte(TAG_SLOT, (byte) i);
				this.slots[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		compound.setTag(TAG_ITEMS, nbttaglist);
		return compound;
	}
}