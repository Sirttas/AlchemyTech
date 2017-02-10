package sirttas.alchemytech.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.AlchemyTechTab;
import sirttas.alchemytech.block.instrument.ConfigInstrument;
import sirttas.alchemytech.block.tile.api.IForcableSync;

public abstract class BlockATContainer extends BlockContainer implements IBlockAT {

	private Class<? extends TileEntity> tileEntity = null;

	public BlockATContainer(String name, Class<? extends TileEntity> tileEntity) {
		super(ConfigInstrument.DEFAULT_MATERIAL);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		ATBlocks.blocks.add(this);
		this.setHarvestLevel("Pickaxe", 1);
		this.setHardness(2F);
		this.tileEntity = tileEntity;
		this.setCreativeTab(AlchemyTechTab.tabAlchemyTech);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);

		if (te instanceof IInventory) {
			IInventory inv = (IInventory) te;

			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stack = inv.getStackInSlot(i);

				if (stack != null) {
					EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							stack);

					world.spawnEntityInWorld(item);
				}
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}

	@Override
	@Deprecated
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void register() {
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		if (tileEntity != null) {
			GameRegistry.registerTileEntity(tileEntity, this.getRegistryName() + "TileEntity");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	private boolean onSlotActivatedUnsync(IInventory inventory, EntityPlayer player, ItemStack heldItem, int index) {
		ItemStack stack = inventory.getStackInSlot(index);
		TileEntity entity = (TileEntity) inventory;

		if (heldItem == null) {
			if (stack != null) {
				if (!entity.getWorld().isRemote) {
					EntityItem invItem = new EntityItem(entity.getWorld(), player.posX, player.posY + 0.25, player.posZ,
							inventory.getStackInSlot(index));
					entity.getWorld().spawnEntityInWorld(invItem);
				}
				inventory.setInventorySlotContents(index, null);
				return true;
			}
			return false;
		} else if (stack == null) {
			stack = heldItem.copy();
			stack.stackSize = 1;
			if (!player.isCreative()) {
				heldItem.stackSize--;
			}
			inventory.setInventorySlotContents(index, stack);
			return true;
		} else if (stack.isItemEqual(heldItem) && stack.stackSize < stack.getMaxStackSize()
				&& stack.stackSize < inventory.getInventoryStackLimit()) {
			if (!player.isCreative()) {
				heldItem.stackSize--;
			}
			stack.stackSize++;
			return true;
		}
		return false;
	}

	protected boolean onSlotActivated(IInventory inventory, EntityPlayer player, ItemStack heldItem, int index) {
		boolean ret = this.onSlotActivatedUnsync(inventory, player, heldItem, index);

		if (ret && inventory instanceof IForcableSync) {
			((IForcableSync) inventory).forceSync();
		}
		return ret;
	}

}