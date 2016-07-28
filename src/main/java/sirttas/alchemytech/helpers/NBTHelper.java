package sirttas.alchemytech.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import sirttas.alchemytech.inventory.ATInventory;

public class NBTHelper {

	public static ItemStack readItemStack(NBTTagCompound cmp, String tag) {
		if (cmp.hasKey(tag)) {
			return ItemStack.loadItemStackFromNBT((NBTTagCompound) cmp.getTag(tag));
		}
		return null;
	}

	public static NBTTagCompound writeItemStack(NBTTagCompound cmp, String tag, ItemStack stack) {
		if (stack != null) {
			NBTTagCompound stackNbt = new NBTTagCompound();
			stack.writeToNBT(stackNbt);
			cmp.setTag(tag, stackNbt);
			return stackNbt;
		} else if (cmp.hasKey(tag)) {
			cmp.removeTag(tag);
		}
		return null;
	}

	public static void readInventory(NBTTagCompound cmp, String tag, ATInventory inventory) {
		if (cmp.hasKey(tag)) {
			inventory.readFromNBT((NBTTagCompound) cmp.getTag(tag));
		}
	}

	public static NBTTagCompound writeInventory(NBTTagCompound cmp, String tag, ATInventory inventory) {
		if (inventory != null) {
			NBTTagCompound inventoryNbt = new NBTTagCompound();
			inventory.writeToNBT(inventoryNbt);
			cmp.setTag(tag, inventoryNbt);
			return inventoryNbt;
		} else if (cmp.hasKey(tag)) {
			cmp.removeTag(tag);
		}
		return null;
	}
}
