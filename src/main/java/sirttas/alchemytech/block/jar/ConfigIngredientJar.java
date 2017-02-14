package sirttas.alchemytech.block.jar;

import net.minecraft.util.math.AxisAlignedBB;
import sirttas.alchemytech.block.instrument.ConfigInstrument;

public class ConfigIngredientJar extends ConfigInstrument {

	public static final String NAME = "ingredientJar";

	public static final String UNLOCALIZED_NAME = "tile." + NAME + ".name";

	// TODO: add an option for max ingredients
	public static final int MAX_INGREDIENTS = 100;

	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.3, 0, 0.3, 0.7, 0.5, 0.7);

	public class NBT extends ConfigInstrument.NBT {
		public static final String INGREDIENT = "ingredient";
		public static final String INGREDIENT_COUNT = "ingredientCount";
	}
}
