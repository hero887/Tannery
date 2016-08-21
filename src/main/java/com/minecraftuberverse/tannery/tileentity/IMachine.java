package com.minecraftuberverse.tannery.tileentity;

import com.minecraftuberverse.tannery.util.recipe.RecipeHandler;

import net.minecraft.server.gui.IUpdatePlayerListBox;

public interface IMachine extends IUpdatePlayerListBox, IContainer
{
	public int getProgress();

	public boolean isReady();

	public int getDuration();

	public RecipeHandler getRecipeHandler();
}
