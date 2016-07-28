package sirttas.alchemytech.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.AlchemyTechTab;

public class ItemAT extends Item {

	public ItemAT(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		this.setCreativeTab(AlchemyTechTab.tabAlchemyTech);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
