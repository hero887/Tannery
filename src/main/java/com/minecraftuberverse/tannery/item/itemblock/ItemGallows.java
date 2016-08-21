package com.minecraftuberverse.tannery.item.itemblock;

import com.minecraftuberverse.tannery.block.processing.BlockGallows;
import com.minecraftuberverse.tannery.init.TanneryBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemGallows extends TanneryItemBlock
{

	public ItemGallows(Block block)
	{
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			BlockPos bottomPos = pos;

			EnumFacing direction = player.getHorizontalFacing().getOpposite();

			if (!world.getBlockState(bottomPos).getBlock().isReplaceable(world,
					bottomPos)) bottomPos = bottomPos.offset(side);

			BlockPos topPos = bottomPos.up();
			BlockPos topleftPos = topPos.offset(direction);

			boolean bottomFlag = world.getBlockState(bottomPos).getBlock().isReplaceable(world,
					bottomPos);
			boolean topFlag = world.getBlockState(topPos).getBlock().isReplaceable(world, topPos);
			boolean topleftFlag = world.getBlockState(topleftPos).getBlock().isReplaceable(world,
					topleftPos);

			if (bottomFlag && topFlag && topleftFlag && player.canPlayerEdit(bottomPos, side,
					stack) && player.canPlayerEdit(topPos, side, stack) && player.canPlayerEdit(
							topleftPos, side,
							stack) && World.doesBlockHaveSolidTopSurface(world, bottomPos.down()))
			{
				BlockGallows base = (BlockGallows) TanneryBlocks.gallows;
				IBlockState bottomBlock = base.getDefaultState()
						.withProperty(BlockDirectional.FACING, direction)
						.withProperty(BlockGallows.PART, BlockGallows.EnumPart.BOTTOM);
				IBlockState topBlock = base.getDefaultState()
						.withProperty(BlockDirectional.FACING, direction)
						.withProperty(BlockGallows.PART, BlockGallows.EnumPart.TOP);
				IBlockState topleftBlock = base.getDefaultState()
						.withProperty(BlockDirectional.FACING, direction)
						.withProperty(BlockGallows.PART, BlockGallows.EnumPart.TOPLEFT);

				world.setBlockState(bottomPos, bottomBlock, 3);
				world.setBlockState(topPos, topBlock, 3);
				world.setBlockState(topleftPos, topleftBlock, 3);
				stack.stackSize--;
				return true;
			}
			return false;
		}
	}
}
