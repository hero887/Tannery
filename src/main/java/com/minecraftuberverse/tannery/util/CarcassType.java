package com.minecraftuberverse.tannery.util;

import net.minecraft.util.IStringSerializable;

public enum CarcassType implements IStringSerializable
{
	ELK(1.5), COW(1.5), PIG(1), SHEEP(1), NONE(0);

	private int value;
	private double size;
	private static int valueCounter = 0;

	private CarcassType(double size)
	{
		setValue();
		setSize(size);
	}

	private void setValue()
	{
		this.value = valueCounter;
		valueCounter++;
	}

	public int getValue()
	{
		return value;
	}

	private void setSize(double size)
	{
		this.size = size;
	}

	public double getSize()
	{
		return size;
	}

	@Override
	public String toString()
	{
		return name().toLowerCase();
	}

	@Override
	public String getName()
	{
		return toString();
	}
}
