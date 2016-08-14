package com.minecraftuberverse.tannery.util.recipe;

import java.util.ArrayList;

import com.minecraftuberverse.ubercore.util.recipe.Recipe;

import net.minecraft.item.ItemStack;

public class ButchersBenchRecipe extends Recipe
{
	@Override
	public ItemStack[] getOutputAsItemStacks()
	{
		ArrayList<ItemStack> list = new ArrayList<>();

		for (ItemStack stack : super.getOutputAsItemStacks())
		{
			while (stack.stackSize > 1)
			{
				list.add(new ItemStack(stack.getItem(), 1));
				stack.stackSize--;
			}
			if (stack.stackSize > 0) list.add(stack);
		}

		return list.toArray(new ItemStack[list.size()]);
	}
}
