package com.minecraftuberverse.tannery.tileentity;

import com.minecraftuberverse.ubercore.util.RecipeHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityActionCrafter extends TileEntity
{
	private ItemStack[] input;
	private ItemStack[] output;

	public TileEntityActionCrafter()
	{
		input = new ItemStack[getRecipeHandler().getInputStackLimit()];
		output = new ItemStack[getRecipeHandler().getOutputStackLimit()];
	}

	public final boolean addInput(ItemStack stack)
	{
		int index = 0;
		for (ItemStack s : input)
		{
			if (s == null) break;
			index++;
		}
		if (0 >= input.length) return false;
		input[index] = stack;
		return true;
	}

	public final ItemStack removeInput()
	{
		ItemStack out = null;
		for (ItemStack next : input)
		{
			if (next != null) out = next;
			else break;
		}
		return out;
	}

	protected final void setOutput(ItemStack[] stacks)
	{
		if (stacks.length > output.length) throw new IllegalArgumentException(
				"Invalid amount of itemstacks for the output of the TileEntityActionCrafter - contact the Tannery team with this message.");
		for (int i = 0; i < stacks.length; i++)
		{
			output[i] = stacks[i];
		}
	}

	public final ItemStack removeOutput()
	{
		ItemStack out = null;
		for (ItemStack next : output)
		{
			if (next != null) out = next;
			else break;
		}
		return out;
	}

	public abstract RecipeHandler getRecipeHandler();

	public abstract boolean onActivate();
}
