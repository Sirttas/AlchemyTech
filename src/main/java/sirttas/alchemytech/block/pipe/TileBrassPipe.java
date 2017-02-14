package sirttas.alchemytech.block.pipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import sirttas.alchemytech.AlchemyTech;
import sirttas.alchemytech.block.pipe.BrassPipeConnection.Type;
import sirttas.alchemytech.block.tile.TileAT;
import sirttas.alchemytech.block.tile.api.IIngredientReceiver;
import sirttas.alchemytech.block.tile.api.IIngredientSender;
import sirttas.alchemytech.ingredient.Ingredient;

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

	private IIngredientReceiver searchReceiver(List<TileBrassPipe> pipes, Ingredient ingredient) {
		pipes.add(this);
		for (BrassPipeConnection connection : connections.values()) {
			TileEntity entity = getAdjacentTile(connection.getFacing());

			if (entity != null && entity instanceof TileBrassPipe && connection.getType() == Type.CONNECT) {
				TileBrassPipe other = (TileBrassPipe) entity;

				if (other != null && !pipes.contains(other)) {
					IIngredientReceiver ret = other.searchReceiver(pipes, ingredient);

					if (ret != null) {
						return ret;
					}
				}
			} else if (entity != null && entity instanceof IIngredientReceiver && connection.getType() == Type.INSERT) {
				IIngredientReceiver receiver = (IIngredientReceiver) entity;

				if (receiver.canReceive(ingredient)) {
					return receiver;
				}
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
				((TileBrassPipe) other).connections.get(face.getOpposite()).setType(Type.CONNECT);
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
					AlchemyTech.T.info("updating pipe: " + face.toString() + " connected");
				} else if (other instanceof IIngredientReceiver) {
					connection.setType(Type.INSERT);
					AlchemyTech.T.info("updating pipe: " + face.toString() + " connected (insert)");
				} else if (other instanceof IIngredientSender) {
					connection.setType(Type.EXTRACT);
					AlchemyTech.T.info("updating pipe: " + face.toString() + " connected (extract)");
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
		if (connections == null || connections.size() < 6) {
			this.init();
		}
		for (EnumFacing face : EnumFacing.VALUES) {
			BrassPipeConnection connection = connections.get(face);

			if (connection != null && connection.getType() == Type.EXTRACT) {
				TileEntity entity = getWorld().getTileEntity(getPos().offset(connection.getFacing()));

				if (entity != null && entity instanceof IIngredientSender) {
					IIngredientSender sender = (IIngredientSender) entity;

					if (sender != null && sender.canExtract(0)) {
						IIngredientReceiver receiver = this.searchReceiver(new ArrayList<TileBrassPipe>(),
								sender.getIngredient(0));

						if (receiver != null) {
							receiver.addIngredient(sender.removeIngredient(0));
						}
					}
				}
			}
		}
	}

	public boolean activatePipe(EnumFacing face) {
		if (connections == null || connections.size() < 6) {
			this.init();
		}
		BrassPipeConnection connection = connections.get(face);

		if (connection != null) {
			if (connection.getType() == Type.INSERT && getAdjacentTile(face) instanceof IIngredientSender) {
				connection.setType(Type.EXTRACT);
				return true;
			} else if (connection.getType() == Type.EXTRACT && getAdjacentTile(face) instanceof IIngredientReceiver) {
				connections.get(face).setType(Type.INSERT);
				return true;
			}
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.connections = new HashMap<EnumFacing, BrassPipeConnection>();

		for (EnumFacing face : EnumFacing.VALUES) {
			if (compound.hasKey(face.getName())) {
				BrassPipeConnection connection = new BrassPipeConnection();

				connection.setFacing(face);
				connection.setType(Type.fromInteger(compound.getInteger(face.getName())));
				connections.put(face, connection);
			} else {
				init();
				return;
			}
		}

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if (connections != null) {
			for (BrassPipeConnection connection : connections.values()) {
				compound.setInteger(connection.getFacing().getName(), connection.getType().getValue());
			}
		}
		return compound;
	}

}
