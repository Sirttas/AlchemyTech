package sirttas.alchemytech.block.jar;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sirttas.alchemytech.block.BlockAT;

public class BlockIngredientJar extends BlockAT implements ITileEntityProvider {

	public BlockIngredientJar(Material material) {
		super("ingredientJar", material);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileIngredientJar();
	}

}
