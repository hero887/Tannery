package com.minecraftuberverse.tannery.tileentity;

import java.util.LinkedList;

import com.minecraftuberverse.tannery.init.TanneryItems;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.util.CarcassType;
import com.minecraftuberverse.tannery.util.recipe.IRecipe;
import com.minecraftuberverse.tannery.util.recipe.RecipeHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class TileEntityButchersBench extends TileEntity
		implements IInteractiveTileEntity, IInventory, IContainer
{
	public final static RecipeHandler recipeHandler = new RecipeHandler();
	public final static String nbtInventory = "inventory";
	public final static String nbt = "butchersbench";

	private ItemStack content;
	private LinkedList<ItemStack> futureOutput;
	private IRecipe current;

	public TileEntityButchersBench()
	{
		super();
		content = null;
		futureOutput = new LinkedList<ItemStack>();
	}

	@Override
	public boolean onActivate(World world, BlockPos pos, EntityPlayer player, ItemStack stack)
	{
		if (!world.isRemote)
		{
			if (content == null)
			{
				if (isItemValidForSlot(0, stack))
				{
					content = stack.splitStack(1);
					current = recipeHandler.find(this);
					for (ItemStack s : current.getOutput())
						futureOutput.add(s);
					markDirty();
					return true;
				}
			}
			else
			{
				if (stack != null && stack.getItem() == TanneryItems.boneKnife)
				{
					if (current == null) this.current = recipeHandler.find(this);
					if (current != null)
					{
						if (futureOutput.isEmpty()) for (ItemStack s : current.getOutput())
							futureOutput.add(s);

						Block.spawnAsEntity(world, pos, futureOutput.removeFirst());
						stack.damageItem(1, player);
						if (futureOutput.isEmpty()) current = null;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return (oldState.getBlock() != newState.getBlock());
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(getPos(), getBlockMetadata(), nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.getNbtCompound());
	}

	public CarcassType getCarcassType()
	{
		return content != null ? ((ItemCarcass) content.getItem())
				.getCarcassType() : CarcassType.NONE;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagCompound sub = new NBTTagCompound();
		sub.setTag(nbtInventory, content != null ? content.writeToNBT(new NBTTagCompound()) : null);
		compound.setTag(nbt, sub);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagCompound sub = compound.getCompoundTag(nbt);
		NBTTagCompound inv = sub.getCompoundTag(nbtInventory);
		if (inv != null) content = ItemStack.loadItemStackFromNBT(inv);
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return null;
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return content;
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return stack != null && stack.getItem() instanceof ItemCarcass;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		this.content = null;
	}

	@Override
	public ItemStack[] getContents()
	{
		return new ItemStack[] { content };
	}
}
