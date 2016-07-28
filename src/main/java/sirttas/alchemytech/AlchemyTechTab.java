package sirttas.alchemytech;

import javax.annotation.Nonnull;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.ATBlocks;

public class AlchemyTechTab extends CreativeTabs {

	public static final @Nonnull CreativeTabs tabAlchemyTech = new AlchemyTechTab();

	public AlchemyTechTab() {
		super(AlchemyTech.MODID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public @Nonnull Item getTabIconItem() {
		return Item.getItemFromBlock(ATBlocks.instrumentCore);
	}

}
