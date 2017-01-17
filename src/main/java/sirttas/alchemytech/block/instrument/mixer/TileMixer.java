package sirttas.alchemytech.block.instrument.mixer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import sirttas.alchemytech.block.instrument.mixer.ConfigMixer.NBT;
import sirttas.alchemytech.block.tile.instrument.TileInstrument;
import sirttas.alchemytech.helpers.NBTHelper;
import sirttas.alchemytech.inventory.ATInventory;

public class TileMixer extends TileInstrument {

	private final ATInventory input;
	private ItemStack output;

	public TileMixer() {
		input = new ATInventory(4);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 4, 0, 1, 2, 3 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return index < 4;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 4) {
			return input.getStackInSlot(index);
		} else if (index == 4) {
			return output;
		}
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index < 4) {
			return input.removeStackFromSlot(index);
		} else if (index == 4) {
			ItemStack ret = output;

			output = null;
			return ret;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 4) {
			input.setInventorySlotContents(index, stack);
		} else if (index == 4) {
			output = stack;
		}

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public void clear() {
		super.clear();
		input.clear();
		output = null;
	}

	@Override
	public boolean isReciptAvalable() {
		if (output == null) {
			return false;
		}
		return super.isReciptAvalable();
	}

	@Override
	public String getName() {
		return ConfigMixer.UNLOCALIZED_NAME;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTHelper.readInventory(compound, NBT.INPUT, input);
		output = NBTHelper.readItemStack(compound, NBT.OUTPUT);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTHelper.writeInventory(compound, NBT.INPUT, input);
		NBTHelper.writeItemStack(compound, NBT.OUTPUT, output);
		return compound;
	}
}
