package com.minecraftuberverse.tannery.block.quebracho;

import java.util.List;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.init.TanneryItems;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author Lewis_McReu
 */
public class BlockLogQuebracho extends BlockNewLog
{
	public BlockLogQuebracho()
	{
		super();
		setUnlocalizedName("logQuebracho");
		setCreativeTab(Tannery.tabTannery);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STATE, 0));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY,
			float hitZ)
	{
		ItemStack stack = player.getHeldItem();
		if (stack != null && stack.getItem() == TanneryItems.barkSpud)
		{
			world.setBlockState(pos, state.withProperty(STATE, Math.min((int) state.getValue(STATE) + 1, 3)));
			return true;
		}
		return false;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{}

	public static final PropertyInteger STATE = PropertyInteger.create("state", 0, 3);

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		int b = meta >> 2;
		int x = meta & 3;
		EnumAxis axis = x == 2 ? EnumAxis.Z : x == 1 ? EnumAxis.X : EnumAxis.Y;
		return getDefaultState().withProperty(LOG_AXIS, axis).withProperty(STATE, b);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumAxis axis = (EnumAxis) state.getValue(LOG_AXIS);
		int meta = axis == EnumAxis.X ? 1 : axis == EnumAxis.Z ? 2 : 0;
		meta += (Integer) state.getValue(STATE) << 2;
		System.out.println(meta);
		return meta;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
	{
		double xO = 0;
		double zO = 0;
		if ((int) state.getValue(STATE) == 3)
		{
			xO = 1 / 16;
			zO = 1 / 16;
		}
		return AxisAlignedBB.fromBounds(pos.getX() + xO, pos.getY(), pos.getZ() + zO, pos.getX() + 1 - xO, pos.getY() + 1, pos.getZ() + 1 - zO);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
	{
		return getCollisionBoundingBox(worldIn, pos, worldIn.getBlockState(pos));
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT, LOG_AXIS, STATE });
	}
}
