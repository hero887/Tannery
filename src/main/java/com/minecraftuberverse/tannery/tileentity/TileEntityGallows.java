package com.minecraftuberverse.tannery.tileentity;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.util.CarcassType;
import com.minecraftuberverse.tannery.util.GallowsDrainingRecipe;
import com.minecraftuberverse.ubercore.tileentity.TileEntityMachine;
import com.minecraftuberverse.ubercore.tileentity.TileEntityMachineSingular;
import com.minecraftuberverse.ubercore.util.Recipe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityGallows extends TileEntityMachineSingular
{
	public static final String RECIPE_HANDLER_KEY = Reference.MOD_ID + ".gallows";
	public static final String NBTKEY_DRAINED_CONTENT = "drained_content";
	public static final String NBTKEY_DRAINING = "draining";
	public static final String NBTKEY_CUTTING = "cutting";

	private boolean draining = false;
	private boolean cutting = false;

	private ItemStack[] drainedOutputContent = new ItemStack[TileEntityMachine
			.getOutputStackLimit(RECIPE_HANDLER_KEY)];

	@Override
	protected String getRecipeHandlerKey()
	{
		return RECIPE_HANDLER_KEY;
	}

	public CarcassType getCarcassType()
	{
		return getContent() != null ? ((ItemCarcass) getContent().getItem())
				.getCarcassType() : CarcassType.NONE;
	}

	public boolean isBloody()
	{
		return getContent() != null ? ((ItemCarcass) getContent().getItem()).isBloody() : false;
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

	public boolean isDraining()
	{
		return draining;
	}

	public boolean isCutting()
	{
		return cutting;
	}

	@Override
	public void update()
	{
		super.update();
		if (getActiveRecipe() instanceof GallowsDrainingRecipe && !isReady())
		{
			draining = true;
			markDirty();
		}
		else if (getActiveRecipe() instanceof Recipe)
		{
			Tannery.logger.info("Cutting!");
			if (!draining && !cutting) setOutput(getActiveRecipe().getOutputAsItemStacks());
			draining = false;
			markDirty();
		}
	}

	@Override
	public void setOutput(ItemStack[] output)
	{
		if (draining) super.setOutput(output);
		else drainedOutputContent = output;
	}

	@Override
	public void addInput(ItemStack stack)
	{
		super.addInput(stack);
		if (!isBloody()) selectActiveRecipe();
	}

	@Override
	public ItemStack[] removeInput()
	{
		if (!cutting) return super.removeInput();
		else
		{
			cutting = false;
			draining = false;
			return new ItemStack[] {};
		}
	}

	/**
	 * Overridden to either return the original output
	 */
	@Override
	public ItemStack[] removeOutput()
	{
		if (isReady() && draining) draining = false;
		if (draining) return super.removeOutput();
		else
		{
			ItemStack out = null;
			cutting = true;
			for (int i = 0; i < drainedOutputContent.length; i++)
			{
				ItemStack is = drainedOutputContent[i];
				if (is != null)
				{
					out = is;
					drainedOutputContent[i] = null;
					break;
				}
			}
			ItemStack testNext = null;
			for (int i = 0; i < drainedOutputContent.length; i++)
			{
				ItemStack is = drainedOutputContent[i];
				if (is != null)
				{
					testNext = is;
					drainedOutputContent[i] = null;
					break;
				}
			}
			if (testNext == null)
			{
				setContent(null);
				cutting = false;
			}
			return new ItemStack[] { out };
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagList list = new NBTTagList();
		for (ItemStack stack : drainedOutputContent)
		{
			if (stack != null) list.appendTag(stack.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag(NBTKEY_DRAINED_CONTENT, list);
		compound.setBoolean(NBTKEY_DRAINING, draining);
		compound.setBoolean(NBTKEY_CUTTING, cutting);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList(NBTKEY_DRAINED_CONTENT, NBT.TAG_COMPOUND);
		ItemStack[] output = new ItemStack[list.tagCount()];
		for (int i = 0; i < list.tagCount(); i++)
		{
			output[i] = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(i));
		}
		drainedOutputContent = output;
		draining = compound.getBoolean(NBTKEY_DRAINING);
		cutting = compound.getBoolean(NBTKEY_CUTTING);
	}

	@Override
	public String getName()
	{
		return "gallows";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return new ChatComponentText(getName());
	}
}
