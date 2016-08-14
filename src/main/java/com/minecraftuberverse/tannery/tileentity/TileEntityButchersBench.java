package com.minecraftuberverse.tannery.tileentity;

import com.minecraftuberverse.tannery.init.TanneryRecipes;
import com.minecraftuberverse.ubercore.util.RecipeHandler;

public class TileEntityButchersBench extends TileEntityActionCrafter
{
	@Override
	public RecipeHandler getRecipeHandler()
	{
		return TanneryRecipes.butchersBenchRecipeHandler;
	}

	@Override
	public boolean onActivate()
	{

		return false;
	}
}
