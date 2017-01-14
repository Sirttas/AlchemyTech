package sirttas.alchemytech.block.instrument.core;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import sirttas.alchemytech.block.instrument.core.ConfigInstrumentCore.NBT;
import sirttas.alchemytech.block.tile.TileATContainer;
import sirttas.alchemytech.block.tile.instrument.IInstrumentCore;
import sirttas.alchemytech.helpers.NBTHelper;

public class TileInstrumentCore extends TileATContainer implements IInstrumentCore {

	private ItemStack fuel;

	private int fuelCount;

	public TileInstrumentCore() {
	}

	public ItemStack getFuel() {
		return fuel;
	}

	public void setFuel(ItemStack fuel) {
		this.fuel = fuel;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound cmp) {
		super.writeToNBT(cmp);
		NBTHelper.writeItemStack(cmp, NBT.FUEL, fuel);
		cmp.setInteger(NBT.FUEL_COUNT, fuelCount);
		return cmp;
	}

	@Override
	public void readFromNBT(NBTTagCompound cmp) {
		super.readFromNBT(cmp);
		fuel = NBTHelper.readItemStack(cmp, NBT.FUEL);
		fuelCount = cmp.getInteger(NBT.FUEL_COUNT);
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index == 0) {
			return fuel;
		}
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index == 0) {
			ItemStack stack = fuel;

			fuel = null;
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index == 0) {
			fuel = stack;
		}

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 0 && TileEntityFurnace.isItemFuel(stack);
	}

	@Override
	public void clear() {
		fuel = null;
	}

	@Override
	public String getName() {
		return ConfigInstrumentCore.UNLOCALIZED_NAME;
	}

	@Override
	public boolean isFueled() {
		return fuelCount > 0 || this.fuel != null;
	}

	@Override
	public boolean isInstrumentAutomated() {
		return false;
	}

	@Override
	public void consumeFuel(int amount) {
		if (fuelCount <= amount - 1 || fuelCount <= 1) {
			fuelCount += TileEntityFurnace.getItemBurnTime(fuel);
			fuel.stackSize--;
			if (fuel.stackSize == 0) {
				fuel = null;
			}
			this.forceSync();
		}
		fuelCount -= amount - 1;
	}

	@Override
	public void serverUpdate() {
		if (fuelCount > 0) {
			fuelCount--;
		}
	}

	@Override
	public boolean isBurning() {
		return fuelCount > 0;
	}

}
