package sirttas.alchemytech.block.jar;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;
import sirttas.alchemytech.block.jar.ConfigIngredientJar.NBT;
import sirttas.alchemytech.block.tile.TileAT;
import sirttas.alchemytech.block.tile.api.IIngredientContainer;
import sirttas.alchemytech.block.tile.api.IIngredientSender;
import sirttas.alchemytech.ingredient.Ingredient;

public class TileIngredientJar extends TileAT implements IIngredientContainer, IIngredientSender {

	private Ingredient ingredient = null;
	private int ingredientCount = 0;

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.clear();
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound cmp) {
		super.writeToNBT(cmp);
		if (ingredient != null) {
			cmp.setString(NBT.INGREDIENT, ingredient.getRegistryName().toString());
		}
		cmp.setInteger(NBT.INGREDIENT_COUNT, ingredientCount);
		return cmp;
	}

	@Override
	public void readFromNBT(NBTTagCompound cmp) {
		ingredient = Ingredient.REGISTRY.getObject(new ResourceLocation(cmp.getString(NBT.INGREDIENT)));
		ingredientCount = cmp.getInteger(NBT.INGREDIENT_COUNT);
		super.readFromNBT(cmp);
	}

	public int getIngredientCount() {
		return ingredientCount;
	}

	@Override
	public void clear() {
		ingredient = null;
		ingredientCount = 0;

	}

	@Override
	public void addIngredient(Ingredient ingredient) {
		if (this.ingredient == null) {
			this.ingredient = ingredient;
			this.ingredientCount = 1;
		} else if (this.ingredient == ingredient) {
			this.ingredientCount++;
		}

	}

	@Override
	public Ingredient removeIngredient(int index) {
		Ingredient ret = this.ingredient;

		this.ingredientCount--;
		if (ingredientCount <= 0) {
			this.ingredient = null;
			this.ingredientCount = 0;
		}
		return ret;
	}

	@Override
	public Ingredient getIngredient(int index) {
		return this.ingredient;
	}

	@Override
	public boolean canReceive(Ingredient ingredient) {
		return (this.ingredient == null || ingredient == this.ingredient)
				&& this.ingredientCount < ConfigIngredientJar.MAX_INGREDIENTS;
	}

	@Override
	public boolean canExtract(int index) {
		return this.ingredientCount > 0;
	}
}
