package sirttas.alchemytech.ingredient.item;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.api.IPostInitIngredient;

public class OreDictionaryItemIngredient extends Ingredient implements IPostInitIngredient, IItemIngredient {

	private List<ItemStack> stacks;
	private String item;

	public OreDictionaryItemIngredient() {
		this(null);
	}

	public OreDictionaryItemIngredient(String name) {
		super(name);
	}

	public OreDictionaryItemIngredient(String name, String item) {
		super(name);
		this.item = item;
	}

	private boolean containsStack(ItemStack stack) {
		for (ItemStack st : stacks) {
			if (st.isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isProducedFromItem(ItemStack stack) {
		return this.stacks != null ? containsStack(stack) : false;
	}

	@Override
	public ItemStack getStack() {
		return stacks != null ? stacks.get(0).copy() : null;
	}

	@Override
	public void postInit() {
		ItemIngredient.itemToIngredient.add(this);
		this.stacks = OreDictionary.getOres(item);
	}

}
