package com.minecraftuberverse.tannery.item;

import com.minecraftuberverse.tannery.util.CarcassType;

import net.minecraft.item.Item;

public class ItemCarcass extends TanneryItem
{
	private final CarcassType carcassType;
	private final boolean bloody;

	public ItemCarcass(CarcassType carcassType)
	{
		this(carcassType, false);
	}

	public ItemCarcass(CarcassType carcassType, boolean bloody)
	{
		super("");
		this.carcassType = carcassType;
		this.bloody = bloody;
	}

	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		unlocalizedName = isBloody() ? "bloody_" : "" + "carcass_" + getCarcassType().toString();
		return super.setUnlocalizedName(unlocalizedName);
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
