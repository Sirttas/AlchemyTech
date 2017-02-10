package sirttas.alchemytech.block.jar;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import sirttas.alchemytech.block.tile.TileAT;
import sirttas.alchemytech.block.tile.api.IIngredientContainer;
import sirttas.alchemytech.block.tile.api.IIngredientSender;

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
}
