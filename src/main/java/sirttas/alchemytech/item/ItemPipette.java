package sirttas.alchemytech.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IEssenceIngredient;
import sirttas.alchemytech.ingredient.api.IItemIngredient;

public class ItemPipette extends ItemAT {

	private static final String NAME = "pipette";
	private static final String INGREDIENT_TAG = "ingredient";

	public ItemPipette() {
		super(NAME);
	}

	public Ingredient getIngredient(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		if (!nbt.hasKey(INGREDIENT_TAG)) {
			return null;
		}
		return Ingredient.REGISTRY.getObject(new ResourceLocation(nbt.getString(INGREDIENT_TAG)));
	}

	public void setIngredient(ItemStack stack, Ingredient ingredient) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		if (ingredient == null) {
			stack.getTagCompound().removeTag(INGREDIENT_TAG);
		} else {
			stack.getTagCompound().setString(INGREDIENT_TAG, ingredient.getRegistryName().toString());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		Ingredient ingredient = this.getIngredient(stack);

		if (ingredient != null) {
			if (ingredient instanceof IEssenceIngredient) {
				tooltip.add("§b" + ingredient.getDisplayName());
			} else if (ingredient instanceof IItemIngredient) {
				tooltip.add("§f" + ingredient.getDisplayName());
			} else {
				tooltip.add(ingredient.getDisplayName());
			}
		}
	}

	private ItemStack findPreparation(EntityPlayer player) {
		if (this.isPreparation(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isPreparation(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isPreparation(itemstack)) {
					return itemstack;
				}
			}
			return null;
		}
	}

	protected boolean isPreparation(@Nullable ItemStack stack) {
		return stack != null && stack.getItem() instanceof ItemPreparation && !(this.getIngredient(stack) != null
				&& ((ItemPreparation) stack.getItem()).getIngredientCount(stack) == 10);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		ItemStack stack = findPreparation(playerIn);

		if (stack != null) {
			ItemPreparation preparation = (ItemPreparation) stack.getItem();
			Ingredient ingredient = this.getIngredient(itemStackIn);

			if (ingredient == null) {
				this.setIngredient(itemStackIn, preparation.removeIngredientAt(stack, 0));
			} else {
				this.setIngredient(itemStackIn, null);
				preparation.addIngredient(stack, ingredient);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
	}

}
