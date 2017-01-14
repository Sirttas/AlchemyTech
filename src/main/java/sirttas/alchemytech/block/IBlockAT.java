package sirttas.alchemytech.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockAT {

	public void register();

	@SideOnly(Side.CLIENT)
	public void initModel();

}
