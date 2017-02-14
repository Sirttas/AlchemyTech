package sirttas.alchemytech.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.AlchemyTechTab;

public class BlockAT extends Block implements IBlockAT {

	public static final float BIT_SIZE = 0.0625f;

	private AxisAlignedBB bounds;

	public BlockAT(String name, Material material) {
		super(material);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(AlchemyTechTab.tabAlchemyTech);
		ATBlocks.blocks.add(this);
	}

	@Override
	public void register() {
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	public BlockAT setBlockBounds(AxisAlignedBB bounds) {
		this.bounds = bounds;
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (bounds == null) {
			return super.getBoundingBox(state, source, pos);
		}
		return bounds;
	}
}
