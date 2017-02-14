package sirttas.alchemytech.block.instrument.mixer;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import net.minecraft.util.math.AxisAlignedBB;
import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigMixer extends ConfigInstrument {

	public static final String NAME = "mixer";

	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	public static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0, 0, 0, 16 * BIT_SIZE, 5 * BIT_SIZE,
			16 * BIT_SIZE);
	public static final AxisAlignedBB BOWL1_AABB = new AxisAlignedBB(0, 5 * BIT_SIZE, 6 * BIT_SIZE, 4 * BIT_SIZE,
			10 * BIT_SIZE, 10 * BIT_SIZE);
	public static final AxisAlignedBB BOWL2_AABB = new AxisAlignedBB(6 * BIT_SIZE, 5 * BIT_SIZE, 0, 10 * BIT_SIZE,
			10 * BIT_SIZE, 4 * BIT_SIZE);
	public static final AxisAlignedBB BOWL3_AABB = new AxisAlignedBB(12 * BIT_SIZE, 5 * BIT_SIZE, 6 * BIT_SIZE,
			16 * BIT_SIZE, 10 * BIT_SIZE, 10 * BIT_SIZE);
	public static final AxisAlignedBB BOWL4_AABB = new AxisAlignedBB(6 * BIT_SIZE, 5 * BIT_SIZE, 12 * BIT_SIZE,
			10 * BIT_SIZE, 10 * BIT_SIZE, 16 * BIT_SIZE);
	public static final AxisAlignedBB OUTPUT_AABB = new AxisAlignedBB(4 * BIT_SIZE, 5 * BIT_SIZE, 4 * BIT_SIZE,
			12 * BIT_SIZE, 16 * BIT_SIZE, 12 * BIT_SIZE);

	public class NBT extends ConfigInstrument.NBT {

		public static final String INPUT = "input";
		public static final String OUTPUT = "output";
		public static final String INGREDIENT = "ingredient";
	}
}
