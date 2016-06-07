package com.minecraftuberverse.tannery.block.processing;

import java.util.Random;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.block.TanneryBlockDirectional;
import com.minecraftuberverse.tannery.init.TanneryItems;
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
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGallows extends TanneryBlockDirectional implements ITileEntityProvider
{
	public static final PropertyEnum CARCASS = PropertyEnum.create("carcass", CarcassType.class);
	public static final PropertyBool BLOODY = PropertyBool.create("bloody");

	public BlockGallows()
	{
		super(Material.wood, "gallows");
		this.setHardness(1.0f);
		setDefaultState(getDefaultState().withProperty(CARCASS, CarcassType.NONE)
				.withProperty(BLOODY, true));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntityGallows tile = getTileEntity(worldIn, pos);
		boolean boolOut = false;

		if (tile.isDraining() && tile.isReady())
		{
			ItemStack out = tile.removeOutput()[0];
			if (!playerIn.inventory.addItemStackToInventory(out)) spawnAsEntity(worldIn, pos, out);
			boolOut = true;
		}
		else
		{
			ItemStack stack = playerIn.getCurrentEquippedItem();
			if (stack != null && stack.getItem() == TanneryItems.boneKnife && !tile.isDraining())
			{
				ItemStack[] out = tile.removeOutput();

				for (ItemStack i : out)
				{
					if (i != null) spawnAsEntity(worldIn, pos, i);
				}
				boolOut = true;
			}
			else
			{
				ItemStack[] in = tile.getInput();
				if (in != null && in[0] != null)
				{
					ItemStack content = in[0];
					ItemStack out = tile.removeInput()[0];
					if (!playerIn.inventory.addItemStackToInventory(out)) spawnAsEntity(worldIn,
							pos, out);
					boolOut = true;
				}
				else
				{
					if (stack != null)
					{
						ItemStack stackIn = new ItemStack(stack.getItem(), 1);
						if (!tile.getRecipeHandler(TileEntityGallows.RECIPE_HANDLER_KEY)
								.isValidInput(stackIn)) return boolOut;
						if (!worldIn.isRemote)
						{
							if (!playerIn.capabilities.isCreativeMode)
							{
								if (stack.stackSize > 1) stackIn = stack.splitStack(1);
								else playerIn.inventory.setInventorySlotContents(
										playerIn.inventory.currentItem, null);
							}
						}
						tile.addInput(stackIn);
						boolOut = true;
					}
				}
			}
		}
		if (boolOut) tile.markDirty();
		return false;
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

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		// TODO implement blood particles if the gallows is currently draining
		super.updateTick(worldIn, pos, state, rand);
		if (worldIn.isRemote && getTileEntity(worldIn, pos) != null && getTileEntity(worldIn, pos)
				.isDraining()) worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, pos.getX(),
						pos.getY(), pos.getZ(), ((double) (rand.nextInt(6) + 2)) / 10d, 1.2,
						((double) (rand.nextInt(6) + 2)) / 10d, 20);
	}
}
