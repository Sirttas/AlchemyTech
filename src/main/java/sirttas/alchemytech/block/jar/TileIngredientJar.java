package sirttas.alchemytech.block.jar;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import sirttas.alchemytech.block.tile.TileAT;
import sirttas.alchemytech.block.tile.api.IIngredientContainer;
import sirttas.alchemytech.block.tile.api.IIngredientSender;
import sirttas.alchemytech.ingredient.Ingredient;

public class TileIngredientJar extends TileAT implements IIngredientContainer, IIngredientSender {

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.clear();
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ingredient removeIngredient(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingredient getIngredient(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
