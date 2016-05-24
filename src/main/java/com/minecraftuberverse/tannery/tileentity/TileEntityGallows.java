package com.minecraftuberverse.tannery.tileentity;

import com.minecraftuberverse.tannery.init.TanneryItems;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.util.CarcassType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TileEntityGallows extends TileEntity implements IUpdatePlayerListBox
{
	public static final String NBTKEY_PROGRESSCOUNTER = "progressCounter";
	public static final String NBTKEY_CONTENT = "carcass";

	private ItemStack content = null;
	private int progressCounter = 0;

	public TileEntityGallows()
	{
	}

	private ItemStack getContent()
	{
		return content;
	}

	private void setContent(ItemStack content)
	{
		this.content = content;
	}

	public ItemStack retrieveContent()
	{
		ItemStack content = getContent();
		setContent(null);
		resetProgress();
		markForUpdate();
		return content;
	}

	public void putContent(ItemStack stack)
	{
		if (stack != null && stack
				.getItem() instanceof ItemCarcass && ((ItemCarcass) stack.getItem())
						.isBloody() && stack.stackSize == 1)
		{
			setContent(stack);
		}
		markForUpdate();
	}

	public CarcassType getCarcassType()
	{
		if (content != null) return ((ItemCarcass) content.getItem()).getCarcassType();
		return CarcassType.NONE;
	}

	public boolean isBloody()
	{
		return content != null && ((ItemCarcass) content.getItem()).isBloody();
	}

	@Override
	public void update()
	{
		System.out.println(getCarcassType().toString() + " " + isBloody());
		if (isBloody())
		{
			if (progressCounter >= 20 * getCarcassType().getSize())
			{
				switch (getCarcassType())
				{
					case ELK:
						setContent(new ItemStack(TanneryItems.drainedElkCarcass, 1));
						break;
					case COW:
						setContent(new ItemStack(TanneryItems.drainedCowCarcass, 1));
						break;
					case PIG:
						setContent(new ItemStack(TanneryItems.drainedPigCarcass, 1));
						break;
					case SHEEP:
						setContent(new ItemStack(TanneryItems.drainedSheepCarcass, 1));
						break;
					default:
						break;
				}
				resetProgress();
				markForUpdate();
			}
			else if (getCarcassType() != CarcassType.NONE)
			{
				if (progressCounter == 0) markForUpdate();
				progressCounter++;
			}
		}
	}

	private void resetProgress()
	{
		progressCounter = 0;
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
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		if (getContent() != null)
		{
			NBTTagCompound p = new NBTTagCompound();
			getContent().writeToNBT(p);
			compound.setTag(NBTKEY_CONTENT, p);
		}
		compound.setInteger(NBTKEY_PROGRESSCOUNTER, progressCounter);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey(NBTKEY_CONTENT)) setContent(
				ItemStack.loadItemStackFromNBT(compound.getCompoundTag(NBTKEY_CONTENT)));
		progressCounter = compound.getInteger(NBTKEY_PROGRESSCOUNTER);
	}

	private void markForUpdate()
	{
		worldObj.markBlockForUpdate(getPos());
		markDirty();
	}
}
