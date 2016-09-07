package com.minecraftuberverse.tannery.item;

import com.minecraftuberverse.tannery.Tannery;

import net.minecraft.item.Item;

public class ItemBarkSpud extends Item
{
	public ItemBarkSpud()
	{
		super();
		setMaxStackSize(1);
		setMaxDamage(120);
		setCreativeTab(Tannery.tabTannery);
	}
}
