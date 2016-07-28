package sirttas.alchemytech.ingredient;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.AlchemyTech;

public class IngredientItem extends Ingredient implements IPostInitIngredient {

	private static List<IngredientItem> itemToIngredient = new ArrayList<IngredientItem>();

	private ItemStack stack;

	public IngredientItem() {
		this(null);
	}

	public IngredientItem(String name) {
		super(name);
	}

	public IngredientItem(String name, ItemStack stack) {
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
		for (IngredientItem ingredient : itemToIngredient) {
			if (ingredient.stack.isItemEqual(stack)) {
				return ingredient;
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

	public IngredientItem setStack(ItemStack stack) {
		this.stack = stack.copy();
		return this;
	}

}
