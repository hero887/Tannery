package com.minecraftuberverse.tannery.util;

public enum CarcassType
{
	ELK, COW, PIG, SHEEP, NONE;

	private int value;
	private static int valueCounter = 0;

	private CarcassType()
	{
		setValue();
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

	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
}
