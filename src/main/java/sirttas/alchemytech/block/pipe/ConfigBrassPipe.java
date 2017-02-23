package sirttas.alchemytech.block.pipe;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import net.minecraft.util.math.AxisAlignedBB;
import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigBrassPipe extends ConfigInstrument {

	public static final String NAME = "brassPipe";
	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	public static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(6.5 * BIT_SIZE, 6.5 * BIT_SIZE, 6.5 * BIT_SIZE,
			9.5 * BIT_SIZE, 9.5 * BIT_SIZE, 9.5 * BIT_SIZE);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0, 7 * BIT_SIZE, 7 * BIT_SIZE, 6.5 * BIT_SIZE,
			9 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(7 * BIT_SIZE, 0, 7 * BIT_SIZE, 9 * BIT_SIZE,
			6.5 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(7 * BIT_SIZE, 7 * BIT_SIZE, 0, 9 * BIT_SIZE,
			9 * BIT_SIZE, 6.5 * BIT_SIZE);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(9.5 * BIT_SIZE, 7 * BIT_SIZE, 7 * BIT_SIZE,
			16 * BIT_SIZE, 9 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(7 * BIT_SIZE, 9.5 * BIT_SIZE, 7 * BIT_SIZE,
			9 * BIT_SIZE, 16 * BIT_SIZE, 9 * BIT_SIZE);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(7 * BIT_SIZE, 7 * BIT_SIZE, 9.5 * BIT_SIZE,
			9 * BIT_SIZE, 9 * BIT_SIZE, 16 * BIT_SIZE);

	public class NBT extends ConfigInstrument.NBT {

	}

}
