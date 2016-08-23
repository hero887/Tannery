package com.minecraftuberverse.tannery.block.processing;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.block.TanneryBlockDirectional;
import com.minecraftuberverse.tannery.tileentity.TileEntityButchersBench;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockButchersBench extends TanneryBlockDirectional implements ITileEntityProvider
{
	public BlockButchersBench()
	{
		super(Material.wood, "butcherbench");
		this.setHardness(1.0F);
		this.setCreativeTab(Tannery.tabTannery);
		setDefaultState(getDefaultState());
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntityButchersBench tile = getTileEntity(worldIn, pos);
		return tile.onActivate(worldIn, pos, playerIn, playerIn.getCurrentEquippedItem());
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		InventoryHelper.dropInventoryItems(worldIn, pos, getTileEntity(worldIn, pos));
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityButchersBench();
	}

	private static TileEntityButchersBench getTileEntity(IBlockAccess world, BlockPos pos)
	{
		return (TileEntityButchersBench) world.getTileEntity(pos);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return createTileEntity(null, null);
	}
}
