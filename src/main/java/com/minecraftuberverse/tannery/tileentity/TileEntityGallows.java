package com.minecraftuberverse.tannery.tileentity;

import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.util.CarcassType;
import com.minecraftuberverse.ubercore.tileentity.TileEntityMachine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TileEntityGallows extends TileEntityMachine
{
	public static final String NBTKEY_PROGRESSCOUNTER = "progressCounter";
	public static final String NBTKEY_CONTENT = "carcass";

	private ItemStack content = null;

	@Override
	public ItemStack[] getInput()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getRecipeHandlerKey()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void insertItemStackIntoInventory(ItemStack stack)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack[] removeInput()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutput(ItemStack[] arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
