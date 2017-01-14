package sirttas.alchemytech.block.instrument.core;

import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigInstrumentCore extends ConfigInstrument {

	public static final String NAME = "instrumentCore";

	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	public class NBT extends ConfigInstrument.NBT {
		public static final String FUEL = "fuel";
		public static final String FUEL_COUNT = "fuelCount";
	}
}
