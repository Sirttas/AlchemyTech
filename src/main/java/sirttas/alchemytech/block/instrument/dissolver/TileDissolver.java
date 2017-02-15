package sirttas.alchemytech.block.instrument.dissolver;

import sirttas.alchemytech.block.tile.api.IIngredientContainer;
import sirttas.alchemytech.block.tile.api.IIngredientSender;
import sirttas.alchemytech.block.tile.instrument.TileSingleSlotInstrument;
import sirttas.alchemytech.ingredient.Ingredient;

public class TileDissolver extends TileSingleSlotInstrument implements IIngredientContainer, IIngredientSender {

	@Override
	public void addIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canReceive(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ingredient getIngredient(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingredient removeIngredient(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canExtract(int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
