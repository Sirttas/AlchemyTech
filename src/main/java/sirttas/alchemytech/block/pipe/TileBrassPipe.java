package sirttas.alchemytech.block.pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import sirttas.alchemytech.block.ATBlocks;
import sirttas.alchemytech.block.pipe.BrassPipeConnection.Type;
import sirttas.alchemytech.block.tile.TileAT;
import sirttas.alchemytech.block.tile.api.IIngredientReceiver;

public class TileBrassPipe extends TileAT {

	HashMap<EnumFacing, BrassPipeConnection> connections;

	public TileBrassPipe() {

		connections = new HashMap<EnumFacing, BrassPipeConnection>();
		for (EnumFacing face : EnumFacing.VALUES) {
			BrassPipeConnection connection = new BrassPipeConnection();

			connections.put(face, connection);
			connection.setFacing(face);
			if (canConnectTo(face)) {
				connection.setType(Type.CONNECTION);
				TileBrassPipe other = getAdjacentPipe(face);

				other.connections.get(face.getOpposite()).setType(Type.CONNECTION);
			}
		}
	}

	private TileBrassPipe getAdjacentPipe(EnumFacing face) {
		return (TileBrassPipe) this.getWorld().getTileEntity(this.getPos().offset(face));
	}

	public boolean isConnectedTo(EnumFacing face) {
		return this.connections.get(face).getType() != Type.NONE;
	}

	private boolean canConnectTo(EnumFacing face) {
		return this.worldObj.getBlockState(this.getPos().offset(face)).getBlock() == ATBlocks.brassPipe;
	}

	public IIngredientReceiver moveIngredient() {
		return this.checkPipe(this.getWorld(), new ArrayList<TileBrassPipe>());
	}

	private IIngredientReceiver checkPipe(World world, List<TileBrassPipe> pipes) {
		pipes.add(this);
		for (BrassPipeConnection connection : connections.values()) {
			if (connection.getType() == Type.CONNECTION) {
				TileBrassPipe other = getAdjacentPipe(connection.getFacing());

				if (!pipes.contains(other)) {

				}
			}
		}
		return null;
	}
}
