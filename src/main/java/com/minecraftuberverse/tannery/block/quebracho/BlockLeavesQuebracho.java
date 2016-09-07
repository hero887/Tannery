package com.minecraftuberverse.tannery.block.quebracho;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.init.TanneryBlocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author Lewis_McReu
 */
public class BlockLeavesQuebracho extends BlockLeaves
{
	public BlockLeavesQuebracho()
	{
		super();
		setCreativeTab(Tannery.tabTannery);
		setDefaultState(getDefaultState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile.leavesQuebracho.name";
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { CHECK_DECAY, DECAYABLE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(CHECK_DECAY, (meta & 1) == 1).withProperty(DECAYABLE, (meta & 2 >> 1) == 1);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return (((boolean) state.getValue(CHECK_DECAY) == true ? 1 : 0) << 1) + ((boolean) state.getValue(DECAYABLE) == true ? 1 : 0);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		ArrayList<ItemStack> list = new ArrayList<>();
		list.add(new ItemStack(Item.getItemFromBlock(this)));
		return list;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(TanneryBlocks.quebrachoSapling);
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		return null;
	}

	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}
}
