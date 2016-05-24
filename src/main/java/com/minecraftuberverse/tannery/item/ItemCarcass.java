package com.minecraftuberverse.tannery.item;

import com.minecraftuberverse.tannery.util.CarcassType;

public class ItemCarcass extends TanneryItem
{
	private final CarcassType carcassType;
	private final boolean bloody;

	public ItemCarcass(CarcassType carcassType)
	{
		super("carcass_" + carcassType.toString());
		this.bloody = false;
		this.carcassType = carcassType;
	}

	public ItemCarcass(CarcassType carcassType, boolean bloody)
	{
		super((bloody ? "bloody" : "drained") + "_carcass_" + carcassType.toString());
		this.carcassType = carcassType;
		this.bloody = bloody;
	}

	public CarcassType getCarcassType()
	{
		return carcassType;
	}

	public boolean isBloody()
	{
		return bloody;
	}
}
