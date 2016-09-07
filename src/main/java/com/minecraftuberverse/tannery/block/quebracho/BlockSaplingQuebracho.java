/**
 * 
 */
package com.minecraftuberverse.tannery.block.quebracho;

import java.util.List;
import java.util.Random;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.worldgen.WorldGenQuebracho;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * @author Lewis_McReu
 */
public class BlockSaplingQuebracho extends BlockSapling
{
	public BlockSaplingQuebracho()
	{
		super();
		setUnlocalizedName("saplingQuebracho");
		setCreativeTab(Tannery.tabTannery);
		setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
	}

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand)
	{
		new WorldGenQuebracho(true).generate(world, rand, pos);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { STAGE, TYPE });
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{}
}
