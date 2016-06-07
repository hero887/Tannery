package com.minecraftuberverse.tannery.util;

import com.minecraftuberverse.ubercore.util.DurationRecipe;

import net.minecraft.item.Item;

public class GallowsDrainingRecipe extends DurationRecipe
{
	public GallowsDrainingRecipe(int duration)
	{
		super(duration);
	}

	public GallowsDrainingRecipe(Item input, Item output, int duration)
	{
		super(input, output, duration);
	}

	public GallowsDrainingRecipe(Item[] input, Item output, int duration)
	{
		super(input, output, duration);
	}

	public GallowsDrainingRecipe(Item input, Item[] output, int duration)
	{
		super(input, output, duration);
	}

	public GallowsDrainingRecipe(Item[] input, Item[] output, int duration)
	{
		super(input, output, duration);
	}
}
