package sirttas.alchemytech.block.instrument.dissolver;

import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigDissolver extends ConfigInstrument {

	public static final String NAME = "dissolver";

	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	public class NBT extends ConfigInstrument.NBT {
		public static final String INGREDIENT = "ingredient";
	}
}
