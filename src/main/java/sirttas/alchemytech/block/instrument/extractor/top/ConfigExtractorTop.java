package sirttas.alchemytech.block.instrument.extractor.top;

import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigExtractorTop extends ConfigInstrument {

	public static final String NAME = "extractorTop";
	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	public class NBT extends ConfigInstrument.NBT {

		public static final String INVENTORY = "inventory";
	}
}
