package sirttas.alchemytech.block.instrument.mixer;

import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import sirttas.alchemytech.block.instrument.mixer.ConfigMixer.NBT;
import sirttas.alchemytech.block.tile.api.IIngredientContainer;
import sirttas.alchemytech.block.tile.api.IIngredientSender;
import sirttas.alchemytech.block.tile.instrument.TileInstrument;
import sirttas.alchemytech.helpers.NBTHelper;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.inventory.ATInventory;
import sirttas.alchemytech.item.ItemPreparation;

public class TileMixer extends TileInstrument implements IIngredientContainer, IIngredientSender {

	private final ATInventory input;
	private ItemStack output;
	private Ingredient ingredient;

	public TileMixer() {
		input = new ATInventory(4);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 4, 0, 1, 2, 3 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (index == 4 && itemStackIn != null && itemStackIn.getItem() instanceof ItemPotion
				&& (PotionUtils.getPotionFromItem(itemStackIn).equals(PotionTypes.WATER)
						|| itemStackIn.getItem() instanceof ItemPreparation)) {
			return true;
		}
		return index < 4;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 4;
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
		ingredient = null;
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
		ingredient = Ingredient.REGISTRY.getObject(new ResourceLocation(compound.getString(NBT.INGREDIENT)));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTHelper.writeInventory(compound, NBT.INPUT, input);
		NBTHelper.writeItemStack(compound, NBT.OUTPUT, output);
		if (ingredient != null) {
			compound.setString(NBT.INGREDIENT, ingredient.getRegistryName().toString());
		}
		return compound;
	}

	@Override
	public void addIngredient(Ingredient ingredient) {
		if (this.ingredient == null) {
			this.ingredient = ingredient;
		}
	}

	@Override
	public boolean canReceive(Ingredient ingredient) {
		return this.ingredient == null;
	}

	@Override
	public Ingredient removeIngredient(int index) {
		Ingredient ingredient = this.ingredient;

		this.ingredient = null;
		return ingredient;
	}

	@Override
	public boolean canExtract(int index) {
		return this.ingredient != null;
	}

	@Override
	public Ingredient getIngredient(int index) {
		return this.ingredient;
	}
}
