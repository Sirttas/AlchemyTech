package sirttas.alchemytech.ingredient;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.AlchemyTech;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.api.IPostInitIngredient;

public class ItemIngredient extends Ingredient implements IPostInitIngredient, IItemIngredient {

	static List<IItemIngredient> itemToIngredient = new ArrayList<IItemIngredient>();

	private ItemStack stack;

	public ItemIngredient() {
		this(null);
	}

	public ItemIngredient(String name) {
		super(name);
	}

	public ItemIngredient(String name, ItemStack stack) {
		super(name);
		setStack(stack);
	}

	@Override
	public void postInit() {
		itemToIngredient.add(this);
		if (stack == null) {
			AlchemyTech.T.warn("ItemStack for IngredientItem: '" + this.getRegistryName()
					+ "' not set. It may cause a potential game crash.");
		}
	}

	public static Ingredient getIngredientFromStack(ItemStack stack) {
		for (IItemIngredient ingredient : itemToIngredient) {
			if (ingredient instanceof Ingredient && ingredient.producedFromItem(stack)) {
				return (Ingredient)ingredient;
			}
		}
		return null;
	}

	public static boolean isIngredient(ItemStack stack) {
		return getIngredientFromStack(stack) != null;
	}

	public ItemStack getStack() {
		return stack.copy();
	}

	public ItemIngredient setStack(ItemStack stack) {
		this.stack = stack.copy();
		return this;
	}

	@Override
	public boolean producedFromItem(ItemStack stack) {
		return this.stack.isItemEqual(stack);
	}

}
