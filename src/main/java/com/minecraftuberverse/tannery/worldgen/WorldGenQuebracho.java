package com.minecraftuberverse.tannery.worldgen;

import java.util.Random;

import com.minecraftuberverse.tannery.init.TanneryBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenQuebracho extends WorldGenAbstractTree implements IWorldGenerator
{

	public WorldGenQuebracho(boolean notify)
	{
		super(notify);
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos)
	{
		Block soil = world.getBlockState(pos.down()).getBlock();
		if (world.getTopSolidOrLiquidBlock(pos).equals(pos.down()) && (soil == Blocks.dirt || soil == Blocks.grass))
		{
			System.out.println("generate");
			Block leaf = TanneryBlocks.quebrachoLeaves;
			Block log = TanneryBlocks.quebrachoLog;

			int height = random.nextInt(4);
			height += 6;
			int leafL1 = height + 2;
			int leafL2 = height;
			int leafL3 = height - 2;

			BlockPos l = pos;
			for (int i = 0; i < height; i++)
			{
				world.setBlockState(l, log.getDefaultState());
				l = l.up();
			}
			return true;
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.getDimensionId())
		{
			case 0:
				for (int i = 0; i < 50; i++)
				{
					int x = chunkX * 16 + random.nextInt(16);
					int z = chunkZ * 16 + random.nextInt(16);
					BlockPos pos = new BlockPos(x, 0, z);
					pos = world.getTopSolidOrLiquidBlock(pos);
					BiomeGenBase biome = world.getBiomeGenForCoords(pos);
					if (biome.getTempCategory() == BiomeGenBase.TempCategory.WARM && biome.rainfall <= 0.3)
					{
						generate(world, random, pos.up());
					}
				}
				break;
		}
	}
}
