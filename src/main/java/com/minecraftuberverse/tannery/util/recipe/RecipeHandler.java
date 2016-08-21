package com.minecraftuberverse.tannery.util.recipe;

import java.util.Collection;
import java.util.TreeSet;

import com.minecraftuberverse.tannery.tileentity.IContainer;

public class RecipeHandler
{
	private Collection<IRecipe> recipes;

	public RecipeHandler()
	{
		recipes = new TreeSet<IRecipe>();
	}

	public void register(IRecipe recipe)
	{
		recipes.add(recipe);
	}

	public IRecipe find(IContainer crafter)
	{
		for (IRecipe r : recipes)
		{
			if (r.canCraft(crafter)) return r;
		}
		return null;
	}
}
