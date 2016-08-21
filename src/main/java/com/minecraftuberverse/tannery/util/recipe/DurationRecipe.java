package com.minecraftuberverse.tannery.util.recipe;

import net.minecraft.item.ItemStack;

public class DurationRecipe extends Recipe implements IDurationRecipe
{
	private final int duration;

	public DurationRecipe(ItemStack[] input, ItemStack[] output, int duration)
	{
		this(input, output, duration, 0);
	}

	public DurationRecipe(ItemStack[] input, ItemStack[] output, int duration, int priority)
	{
		super(input, output, priority);
		this.duration = duration;
	}

	@Override
	public int getDuration()
	{
		return duration;
	}
}