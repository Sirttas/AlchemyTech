package sirttas.alchemytech.block.instrument.dissolver;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import sirttas.alchemytech.block.instrument.dissolver.ConfigDissolver.NBT;
import sirttas.alchemytech.block.tile.api.IIngredientContainer;
import sirttas.alchemytech.block.tile.api.IIngredientSender;
import sirttas.alchemytech.block.tile.instrument.TileSingleSlotInstrument;
import sirttas.alchemytech.ingredient.Ingredient;

public class TileDissolver extends TileSingleSlotInstrument implements IIngredientContainer, IIngredientSender {

	private Ingredient ingredient;

	@Override
	public String getName() {
		return ConfigDissolver.UNLOCALIZED_NAME;
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

	@Override
	public void clear() {
		super.clear();
		ingredient = null;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		ingredient = Ingredient.REGISTRY.getObject(new ResourceLocation(compound.getString(NBT.INGREDIENT)));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if (ingredient != null) {
			compound.setString(NBT.INGREDIENT, ingredient.getRegistryName().toString());
		}
		return compound;
	}

}
