package sirttas.alchemytech.block.instrument.dissolver;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sirttas.alchemytech.block.instrument.BlockInstrument;

public class BlockDissolver extends BlockInstrument {

	public BlockDissolver() {
		super("dissolver", TileDissolver.class);
		addBoundingBox(FULL_BLOCK_AABB);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDissolver();
	}

}
