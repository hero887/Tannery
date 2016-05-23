package com.minecraftuberverse.tannery.block.processing;

import java.util.List;

import com.minecraftuberverse.tannery.block.TanneryBlockDirectional;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;
import com.minecraftuberverse.tannery.util.CarcassType;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGallows extends TanneryBlockDirectional implements ITileEntityProvider
{
	public static final PropertyEnum CARCASS = PropertyEnum.create("carcass", CarcassType.class);
	public static final PropertyBool BLOODY = PropertyBool.create("bloody");

	public BlockGallows()
	{
		super(Material.wood, "gallows");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		// TODO fix syncing

		TileEntityGallows tile = getTileEntity(worldIn, pos);

		ItemStack stackOut = tile.retrieveContent();

		if (stackOut != null)
		{
			if (!playerIn.inventory.addItemStackToInventory(stackOut))
			{
				spawnAsEntity(worldIn, pos, stackOut);
			}
			return true;
		}
		else
		{
			ItemStack stack = playerIn.getCurrentEquippedItem();
			if (stack != null && stack
					.getItem() instanceof ItemCarcass && ((ItemCarcass) stack.getItem()).isBloody())
			{
				if (!playerIn.capabilities.isCreativeMode)
				{
					playerIn.inventory.consumeInventoryItem(stack.getItem());
				}
				tile.putContent(new ItemStack(stack.getItem(), 1));
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> list = super.getDrops(world, pos, state, fortune);
		ItemStack content = getTileEntity(world, pos).retrieveContent();
		if (content != null) list.add(content);
		return list;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntityGallows tile = getTileEntity(worldIn, pos);
		return super.getActualState(state, worldIn, pos)
				.withProperty(CARCASS, tile.getCarcassType()).withProperty(BLOODY, tile.isBloody());
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { FACING, CARCASS, BLOODY });
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityGallows();
	}

	private static TileEntityGallows getTileEntity(IBlockAccess world, BlockPos pos)
	{
		return (TileEntityGallows) world.getTileEntity(pos);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return createTileEntity(null, null);
	}
}
