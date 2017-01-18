package sirttas.alchemytech.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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

public class ItemPreparation extends ItemAT {

	private static final String NAME = "preparation";
	private static final String INGREDIENTS_TAG = "ingredients";

	// TODO: add an option for max ingredients
	public static final int MAX_INGREDIENTS = 10;

	public ItemPreparation() {
		super(NAME);
	}

	protected NBTTagList getIngredientsTagList(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		NBTTagList list = nbt.getTagList(INGREDIENTS_TAG, 8);

		return list;
	}

	public int addIngredient(ItemStack stack, Ingredient ingredient) {
		NBTTagList list = getIngredientsTagList(stack);

		if (list.tagCount() < MAX_INGREDIENTS) {
			list.appendTag(new NBTTagString(ingredient.getRegistryName().toString()));
		} else {
			return -1;
		}
		stack.getTagCompound().setTag(INGREDIENTS_TAG, list);
		return list.tagCount();
	}

	public Ingredient[] getIngredients(ItemStack stack) {
		NBTTagList list = getIngredientsTagList(stack);
		int size = list.tagCount();
		Ingredient[] ingredients = new Ingredient[size];

		for (int i = 0; i < size; i++) {
			ingredients[i] = Ingredient.REGISTRY.getObject(new ResourceLocation(list.getStringTagAt(i)));
		}
		return ingredients;
	}

	public int getIngredientCount(ItemStack stack) {
		return getIngredientsTagList(stack).tagCount();
	}

	public Ingredient removeIngredient(ItemStack stack, int index) {
		NBTTagList list = getIngredientsTagList(stack);
		int size = list.tagCount();

		if (index < size) {
			return Ingredient.REGISTRY
					.getObject(new ResourceLocation(((NBTTagString) list.removeTag(index)).getString()));
		}
		return null;
	}

	public Ingredient removeIngredient(ItemStack stack, Ingredient ingredient) {
		NBTTagList list = getIngredientsTagList(stack);
		int size = list.tagCount();

		for (int i = 0; i < size; i++) {
			if (Ingredient.REGISTRY.getObject(new ResourceLocation(list.getStringTagAt(i))) == ingredient) {
				return Ingredient.REGISTRY
						.getObject(new ResourceLocation(((NBTTagString) list.removeTag(i)).getString()));
			}

		}
		return null;
	}

	private boolean hasIngredient(Ingredient[] ingredients, Ingredient toCheck) {
		for (Ingredient ingredient : ingredients) {
			if (ingredient == toCheck) {
				return true;
			}
		}

		return false;
	}

	public boolean hasIngredient(ItemStack stack, Ingredient toCheck) {
		return hasIngredient(getIngredients(stack), toCheck);
	}

	public boolean hasAllIngredient(ItemStack stack, Ingredient[] toCheck) {
		Ingredient[] ingredients = getIngredients(stack);

		for (Ingredient singleCheck : toCheck) {
			if (!hasIngredient(ingredients, singleCheck)) {
				return false;
			}
		}
		return true;
	}

	@Deprecated
	public void transferIngredient(ItemStack from, ItemStack to) {
		// FIXME: this method isn't deprecated but it doesn't work properly,
		// 1. it could be less heavy
		// 2. some check need to be done like if the destination can receive
		// more ingredients
		ItemPreparation fromPrep = (ItemPreparation) from.getItem();
		ItemPreparation toPrep = (ItemPreparation) to.getItem();
		Ingredient ingredient = fromPrep.removeIngredient(from, 0);

		if (ingredient != null) {
			toPrep.addIngredient(to, ingredient);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		// TODO: line per ingredient types instead of ingredients

		for (Ingredient ingredient : getIngredients(stack)) {
			if (ingredient instanceof IEssenceIngredient) {
				tooltip.add("§b" + ingredient.getDisplayName());
			} else if (ingredient instanceof IItemIngredient) {
				tooltip.add("§f" + ingredient.getDisplayName());
			} else {
				tooltip.add(ingredient.getDisplayName());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getIngredientsColor(ItemStack stack) {
		Ingredient[] ingredients = getIngredients(stack);
		float ratio = 1F / ingredients.length;
		int R = 0;
		int G = 0;
		int B = 0;

		for (Ingredient ingredient : ingredients) {
			int color = ingredient.getColor();

			R += ((color & 0xff0000) >> 16) * ratio;
			G += ((color & 0xff00) >> 8) * ratio;
			B += (color & 0xff) * ratio;
		}
		return R << 16 | G << 8 | B;
	}

	@SideOnly(Side.CLIENT)
	public void initColors() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex > 0 ? -1 : getIngredientsColor(stack);
			}
		}, new Item[] { this });

	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {

		if (itemStackIn.getItem() instanceof ItemPreparation) {
			for (Ingredient ingredient : this.getIngredients(itemStackIn)) {
				if (ingredient instanceof IEssenceIngredient) {
					playerIn.setActiveHand(hand);
					return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
				}
			}
		}
		return new ActionResult(EnumActionResult.FAIL, itemStackIn);
	}

	/**
	 * Called when the player finishes using this Item (E.g. finishes eating.).
	 * Not called when the player stops using the Item before the action is
	 * complete.
	 */
	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (stack.getItem() instanceof ItemPreparation) {
			for (Ingredient ingredient : this.getIngredients(stack)) {
				if (ingredient instanceof IEssenceIngredient) {
					((IEssenceIngredient) ingredient).applyOnEntity(entityLiving, 1);
				}
			}
		}
		return stack;
	}
}
