package com.minecraftuberverse.tannery.util.recipe;

import com.minecraftuberverse.tannery.tileentity.IContainer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IRecipe extends Comparable<IRecipe>
{
	public boolean canCraft(IContainer inv);

	/**
	 * Higher means less priority
	 * 
	 * @return
	 */
	public int getPriority();

	public ItemStack[] craft(IContainer inv, World worldIn, BlockPos pos);

	public ItemStack[] getInput();

	public ItemStack[] getOutput();
}