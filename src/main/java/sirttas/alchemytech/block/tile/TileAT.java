package sirttas.alchemytech.block.tile;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.network.NetworkUtil;

public abstract class TileAT extends TileEntity implements ITickable, IInventory, IForcableSync {

	private boolean toSync = true;
	private int tick = 0;

	@Override
	public void forceSync() {
		toSync = true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer palyer) {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack oldStack = getStackInSlot(slot);
		int newCount = (count > oldStack.stackSize) ? oldStack.stackSize : count;

		oldStack.stackSize -= newCount;
		ItemStack stack = oldStack.copy();
		stack.stackSize = newCount;
		if (stack.stackSize <= 0) {
			this.removeStackFromSlot(slot);
			return null;
		}
		return stack;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName(), new Object[0]);
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.clear();
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
