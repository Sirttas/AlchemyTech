package sirttas.alchemytech.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import sirttas.alchemytech.helpers.NBTHelper;

public class ATOutput extends ATInventory {

	private static final String TAG_OVERFLOW = "overflow";

	private ItemStack overflow;

	public ATOutput() {
		this(DEFAULT_SIZE);
	}

	public ATOutput(int size) {
		super(size);
	}

	public void addItem(ItemStack stack) {
		if (overflow == null) {
			overflow = insertItem(stack);
		}
	}

	private void insertOverflow() {
		overflow = insertItem(overflow);
	}

	private ItemStack insertItem(ItemStack stackIn) {
		if (stackIn == null) {
			return null;
		}
		ItemStack stack = stackIn.copy();

		for (int i = 0; i < size; i++) {
			if (slots[i] == null) {
				slots[i] = stack;
				return null;
			} else if (ItemStack.areItemStackTagsEqual(slots[i], stack)) {
				if (slots[i].stackSize + stack.stackSize <= 64) {
					slots[i].stackSize += stack.stackSize;
					return null;
				}
				int remaining = stack.stackSize + slots[i].stackSize - 64;

				slots[i].stackSize = 64;
				stack.stackSize -= remaining;
			}
		}
		return stack;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack stack = decrStackSize(slot, count);
		insertOverflow();
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = slots[index];
		insertOverflow();
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		super.setInventorySlotContents(index, stack);
		if (stack == null) {
			insertOverflow();
		}
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return overflow == null;
	}

	public boolean hasOverflow() {
		return overflow != null;
	}

	@Override
	public void clear() {
		super.clear();
		this.overflow = null;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		overflow = NBTHelper.readItemStack(compound, TAG_OVERFLOW);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTHelper.writeItemStack(compound, TAG_OVERFLOW, overflow);
		return compound;
	}
}
