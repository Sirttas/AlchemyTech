package sirttas.alchemytech.block.instrument.extractor.top;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import sirttas.alchemytech.block.instrument.extractor.ConfigExtractor;
import sirttas.alchemytech.block.instrument.extractor.ConfigExtractor.NBT;
import sirttas.alchemytech.block.tile.TileATContainer;
import sirttas.alchemytech.helpers.NBTHelper;
import sirttas.alchemytech.inventory.ATInventory;

public class TileExtractorTop extends TileATContainer {

	ATInventory inventory = new ATInventory(20);

	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setInventorySlotContents(index, stack);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return inventory.isItemValidForSlot(index, stack);
	}

	@Override
	public void clear() {
		inventory.clear();

	}

	@Override
	public String getName() {
		return ConfigExtractor.TOP_UNLOCALIZED_NAME;
	}

	public ItemStack tryInsertItem(ItemStack stack) {
		return inventory.tryInsertItem(stack);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound cmp) {
		super.writeToNBT(cmp);
		NBTHelper.writeInventory(cmp, NBT.INVENTORY, inventory);
		return cmp;
	}

	@Override
	public void readFromNBT(NBTTagCompound cmp) {
		super.readFromNBT(cmp);
		NBTHelper.readInventory(cmp, NBT.INVENTORY, inventory);
	}
}
