package com.minecraftuberverse.tannery.util.recipe;

import java.util.ArrayList;

import com.minecraftuberverse.tannery.tileentity.IContainer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Recipe implements IRecipe
{
	private final int priority;
	private final ItemStack[] input;
	private final ItemStack[] output;

	public Recipe(ItemStack[] input, ItemStack[] output, int priority)
	{
		super();
		this.input = input;
		this.output = output;
		this.priority = priority;
	}

	public Recipe(ItemStack[] input, ItemStack[] output)
	{
		this(input, output, 0);
	}

	@Override
	public int compareTo(IRecipe r)
	{
		return this.getPriority() - r.getPriority();
	}

	@Override
	public boolean canCraft(IContainer inv)
	{
		boolean out = true;
		for (int i = 0; i < input.length; i++)
		{
			out = find(inv, input[i]);
			if (!out) return false;
		}
		return true;
	}

	private boolean find(IContainer inv, ItemStack stack)
	{
		int amount = 0;
		for (int i = 0; i < inv.getContents().length; i++)
		{
			ItemStack s = inv.getContents()[i];
			if (s.getItem().equals(stack.getItem())) amount += s.stackSize;
		}
		if (amount >= stack.stackSize) return true;
		return false;
	}

	@Override
	public int getPriority()
	{
		return priority;
	}

	@Override
	public ItemStack[] craft(IContainer inv, World worldIn, BlockPos pos)
	{
		if (canCraft(inv))
		{
			for (int j = 0; j < input.length; j++)
			{
				ItemStack stack = input[j];
				int amountLeft = stack.stackSize;
				for (int i = 0; i < inv.getContents().length; i++)
				{
					ItemStack s = inv.getContents()[i];
					if (s.getItem().equals(stack.getItem()))
					{
						if (s.stackSize <= amountLeft)
						{
							amountLeft -= s.stackSize;
							inv.getContents()[i] = null;
						}
						else
						{
							s.stackSize -= amountLeft;
						}
						amountLeft -= s.stackSize;
					}
				}
			}

			ArrayList<ItemStack> list = new ArrayList<ItemStack>();
			for (int i = 0; i < output.length; i++)
			{
				ItemStack s = output[i];
				if (s.stackSize > 64)
				{
					int amountLeft = s.stackSize;
					while (amountLeft > 64)
					{
						list.add(new ItemStack(s.getItem(), 64));
						amountLeft -= 64;
					}
					list.add(new ItemStack(s.getItem(), amountLeft));
				}
			}
			return list.toArray(new ItemStack[0]);
		}
		return null;
	}

	@Override
	public ItemStack[] getInput()
	{
		return input;
	}

	@Override
	public ItemStack[] getOutput()
	{
		return output;
	}
}