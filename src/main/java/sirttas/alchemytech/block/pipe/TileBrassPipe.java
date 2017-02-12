package sirttas.alchemytech.block.pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import sirttas.alchemytech.block.pipe.BrassPipeConnection.Type;
import sirttas.alchemytech.block.tile.TileAT;
import sirttas.alchemytech.block.tile.api.IIngredientReceiver;
import sirttas.alchemytech.block.tile.api.IIngredientSender;

public class TileBrassPipe extends TileAT {

	HashMap<EnumFacing, BrassPipeConnection> connections;

	private TileEntity getAdjacentTile(EnumFacing face) {
		return this.getWorld().getTileEntity(this.getPos().offset(face));
	}

	public boolean isConnectedTo(EnumFacing face) {
		if (connections != null) {
			return this.connections.get(face).getType() != Type.NONE;
		}
		return false;
	}

	private IIngredientReceiver searchReceiver(List<TileBrassPipe> pipes) {
		pipes.add(this);
		for (BrassPipeConnection connection : connections.values()) {
			if (connection.getType() == Type.CONNECT) {
				TileBrassPipe other = (TileBrassPipe) getAdjacentTile(connection.getFacing());

				if (!pipes.contains(other)) {
					IIngredientReceiver ret = other.searchReceiver(pipes);

					if (ret != null) {
						return ret;
					}
				}
			} else if (connection.getType() == Type.INSERT) {
				return (IIngredientReceiver) getWorld().getTileEntity(getPos().offset(connection.getFacing()));
			}
		}
		return null;
	}

	public void init() {
		this.connections = new HashMap<EnumFacing, BrassPipeConnection>();

		for (EnumFacing face : EnumFacing.VALUES) {
			BrassPipeConnection connection = new BrassPipeConnection();
			TileEntity other = getAdjacentTile(face);

			connection.setFacing(face);
			connection.setType(Type.NONE);
			if (other instanceof TileBrassPipe) {
				connection.setType(Type.CONNECT);
			} else if (other instanceof IIngredientReceiver) {
				connection.setType(Type.INSERT);
			} else if (other instanceof IIngredientSender) {
				connection.setType(Type.EXTRACT);
			}
			connections.put(face, connection);

		}
	}

	public void refresh() {
		for (EnumFacing face : EnumFacing.VALUES) {
			BrassPipeConnection connection = connections.get(face);
			TileEntity other = getAdjacentTile(face);

			if (connection.getType() == Type.NONE) {
				if (other instanceof TileBrassPipe) {
					connection.setType(Type.CONNECT);
				} else if (other instanceof IIngredientReceiver) {
					connection.setType(Type.INSERT);
				} else if (other instanceof IIngredientSender) {
					connection.setType(Type.EXTRACT);
				}
			} else {
				if (other == null) {
					connection.setType(Type.NONE);
				}
			}
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void serverUpdate() {
		if (connections == null) {
			this.init();
		}
		for (EnumFacing face : EnumFacing.VALUES) {
			BrassPipeConnection connection = connections.get(face);

			if (connection.getType() == Type.EXTRACT) {
				IIngredientSender sender = (IIngredientSender) getWorld()
						.getTileEntity(getPos().offset(connection.getFacing()));

				if (sender != null) {
					IIngredientReceiver receiver = this.searchReceiver(new ArrayList<TileBrassPipe>());

					if (receiver != null) {
						receiver.addIngredient(sender.removeIngredient(0));
					}
				}
			}
		}
	}

	public boolean activatePipe(EnumFacing face) {
		if (connections == null) {
			this.init();
		}
		if (connections.get(face).getType() == Type.INSERT && getAdjacentTile(face) instanceof IIngredientSender) {
			connections.get(face).setType(Type.EXTRACT);
			return true;
		} else if (connections.get(face).getType() == Type.EXTRACT
				&& getAdjacentTile(face) instanceof IIngredientReceiver) {
			connections.get(face).setType(Type.INSERT);
			return true;
		}
		return false;
	}

}
