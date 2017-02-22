package sirttas.alchemytech.block.instrument.extractor.top;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sirttas.alchemytech.block.BlockATContainer;
import sirttas.alchemytech.block.instrument.extractor.ConfigExtractor;

public class BlockExtractorTop extends BlockATContainer {

	public BlockExtractorTop() {
		super(ConfigExtractor.TOP_NAME, TileExtractorTop.class);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileExtractorTop();
	}

}
