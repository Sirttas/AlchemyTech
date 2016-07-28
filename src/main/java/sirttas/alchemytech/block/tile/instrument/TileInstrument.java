package sirttas.alchemytech.block.tile.instrument;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import sirttas.alchemytech.block.instrument.ConfigInstrument.NBT;
import sirttas.alchemytech.block.tile.TileATContainer;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;
import sirttas.alchemytech.ingredient.recipe.IngredientRecipeRegistry;

public abstract class TileInstrument extends TileATContainer implements IInstrument {

	protected float progress = 0;

	private IIngredientRecipe<TileInstrument> recipe;

	@Override
	public boolean isReciptAvalable() {
		if (recipe != null && recipe.isAvalable(this)) {
			return true;
		}
		recipe = IngredientRecipeRegistry.lookupRecipe(this);
		return recipe != null;
	}

	@Override
	public void process() {
		recipe.process(this);
		recipe = null;
		this.forceSync();
	}

	@Override
	public void serverUpdate() {
		makeProgress();
	}

	protected void makeProgress() {
		IInstrumentCore core = getCore();

		if (!this.isReciptAvalable() || core == null) {
			progress = 0;
		} else if (progress >= 100) {
			process();
			progress = 0;
		} else if (canProgress() && core.isFueled()) {
			core.consumeFuel(1);
			progress++;
		}
	}

	@Override
	public boolean canProgress() {
		return true;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound cmp) {
		super.writeToNBT(cmp);
		cmp.setFloat(NBT.PROGRESS, progress);
		return cmp;
	}

	@Override
	public void readFromNBT(NBTTagCompound cmp) {
		super.readFromNBT(cmp);
		progress = cmp.getFloat(NBT.PROGRESS);
	}

	@Override
	public float getProgress() {
		return progress;
	}

	protected IInstrumentCore getCore() {
		TileEntity entity = this.getWorld().getTileEntity(getPos().add(0, -1, 0));

		if (entity != null && entity instanceof IInstrumentCore) {
			return (IInstrumentCore) entity;
		}
		return null;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void clear() {
		recipe = null;
	}

}
