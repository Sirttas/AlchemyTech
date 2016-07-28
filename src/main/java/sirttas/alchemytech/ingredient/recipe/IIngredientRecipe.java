package sirttas.alchemytech.ingredient.recipe;

import net.minecraft.tileentity.TileEntity;

public interface IIngredientRecipe<T extends TileEntity> {

	void process(T instrument);

	boolean isAvalable(T instrument);

}
