package com.minecraftuberverse.tannery.tileentity;

import java.util.LinkedList;

import com.minecraftuberverse.tannery.init.TanneryItems;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.util.CarcassType;
import com.minecraftuberverse.tannery.util.recipe.IDurationRecipe;
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

public class TileEntityGallows extends TileEntity
		implements IMachine, IInteractiveTileEntity, IInventory
{
	public final static RecipeHandler recipeHandler = new RecipeHandler();
	public final static String nbtProgress = "progress";
	public final static String nbtInventory = "inventory";
	public final static String nbt = "gallows";

	private ItemStack content;
	private LinkedList<ItemStack> futureOutput;
	private int progress;
	private IRecipe current;

	public TileEntityGallows()
	{
		super();
		content = null;
		progress = 0;
		futureOutput = new LinkedList<ItemStack>();
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagCompound sub = new NBTTagCompound();
		sub.setInteger(nbtProgress, progress);
		if (content != null)
		{
			NBTTagCompound n = new NBTTagCompound();
			content.writeToNBT(n);
			sub.setTag(nbtInventory, n);
		}
		compound.setTag(nbt, sub);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagCompound sub = compound.getCompoundTag(nbt);
		progress = sub.getInteger(nbtProgress);
		NBTTagCompound inv = sub.getCompoundTag(nbtInventory);
		if (inv != null) content = ItemStack.loadItemStackFromNBT(inv);
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

	public boolean isDrained()
	{
		return content != null ? ((ItemCarcass) content.getItem()).isBloody() : false;
	}

	public CarcassType getCarcassType()
	{
		return content != null ? ((ItemCarcass) content.getItem())
				.getCarcassType() : CarcassType.NONE;
	}

	@Override
	public boolean onActivate(World world, BlockPos pos, EntityPlayer player, ItemStack equipped)
	{
		if (!world.isRemote)
		{
			boolean boolOut = false;

			if (content != null)
			{
				if (current instanceof IDurationRecipe || current == null)
				{
					current = null;
					this.progress = 0;
					if (!player.inventory.addItemStackToInventory(content)) Block
							.spawnAsEntity(world, pos, content);
					content = null;
				}
				else if (equipped != null && equipped
						.getItem() == TanneryItems.boneKnife && isDrained())
				{
					if (current == null) this.current = getRecipeHandler().find(this);
					if (current != null)
					{
						if (futureOutput.isEmpty()) for (ItemStack s : current.getOutput())
							futureOutput.add(s);

						Block.spawnAsEntity(world, pos, futureOutput.removeFirst());
						equipped.damageItem(1, player);
						if (futureOutput.isEmpty()) current = null;
					}
				}
			}
			else
			{
				if (isItemValidForSlot(0, equipped))
				{
					content = equipped.splitStack(1);
					current = getRecipeHandler().find(this);
				}
			}
			if (boolOut) this.markDirty();
			return boolOut;
		}
		return false;
	}

	@Override
	public void update()
	{
		if (current instanceof IDurationRecipe)
		{
			if (progress >= ((IDurationRecipe) current).getDuration())
			{
				this.content = current.craft(this, worldObj, pos)[0];
				current = null;
				markDirty();
			}
			else progress++;
		}
	}

	@Override
	public ItemStack[] getContents()
	{
		return new ItemStack[] { content };
	}

	@Override
	public int getProgress()
	{
		return progress;
	}

	@Override
	public boolean isReady()
	{
		return this.content != null ? this.current instanceof IDurationRecipe && this.progress >= getDuration() : false;
	}

	public boolean isProcessing()
	{
		return !isReady();
	}

	@Override
	public int getDuration()
	{
		return current instanceof IDurationRecipe ? ((IDurationRecipe) current).getDuration() : -1;
	}

	@Override
	public RecipeHandler getRecipeHandler()
	{
		return recipeHandler;
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
		return content;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.content = stack;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
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
		return stack.getItem() instanceof ItemCarcass;
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
}
