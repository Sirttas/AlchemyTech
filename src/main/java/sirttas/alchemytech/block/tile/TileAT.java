package sirttas.alchemytech.block.tile;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.tile.api.IForcableSync;
import sirttas.alchemytech.network.NetworkUtil;

public abstract class TileAT extends TileEntity implements ITickable, IForcableSync {

	private boolean toSync = true;
	private int tick = 0;

	@Override
	public void forceSync() {
		toSync = true;
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	@Nonnull
	@Override
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	private void sync() {
		tick++;
		if (toSync || tick >= 10) {
			IBlockState bs = worldObj.getBlockState(pos);

			worldObj.notifyBlockUpdate(pos, bs, bs, 3);
			markDirty();
			worldObj.notifyBlockOfStateChange(pos, getBlockType());
			NetworkUtil.dispatchTEToNearbyPlayers(this);
			toSync = false;
			tick = 0;
		}
	}

	@Override
	public final void update() {
		if (worldObj.isRemote) {
			clientUpdate();
		} else {
			serverUpdate();
			sync();
		}
	}

	protected void serverUpdate() {

	}

	@SideOnly(Side.CLIENT)
	protected void clientUpdate() {

	}

}
