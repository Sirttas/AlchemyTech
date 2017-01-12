package sirttas.alchemytech.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import sirttas.alchemytech.block.ATBlocks;

public class ATWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		switch (world.provider.getDimension()) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}

	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {

	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ) {

	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		int firstBlockXCoord;
		int firstBlockYCoord;
		int firstBlockZCoord;

		for (int k = 0; k < 20; k++) {
			firstBlockXCoord = chunkX + rand.nextInt(16);
			firstBlockYCoord = rand.nextInt(64);
			firstBlockZCoord = chunkZ + rand.nextInt(16);

			new WorldGenMinable(ATBlocks.copperOre.getDefaultState(), rand.nextInt(10)).generate(world, rand,
					new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
		}

		for (int k = 0; k < 16; k++) {
			firstBlockXCoord = chunkX + rand.nextInt(16);
			firstBlockYCoord = rand.nextInt(64);
			firstBlockZCoord = chunkZ + rand.nextInt(16);

			new WorldGenMinable(ATBlocks.zincOre.getDefaultState(), rand.nextInt(10)).generate(world, rand,
					new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
		}

	}

}
