package sirttas.alchemytech.block.instrument.extractor;

import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigExtractor extends ConfigInstrument {

	public static final String NAME = "extractor";
	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	public static final String TOP_NAME = "extractorTop";
	public static final String TOP_UNLOCALIZED_NAME = "tile." + TOP_NAME + ".name";

	public class NBT extends ConfigInstrument.NBT {
		public static final String INGREDIENT = "ingredient";
		public static final String INVENTORY = "inventory";
	}
}
