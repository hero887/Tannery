package com.minecraftuberverse.tannery.item;

import com.minecraftuberverse.tannery.Tannery;

import net.minecraft.item.Item;

/**
 * @author Lewis_McReu
 */
public class TanneryItem extends Item
{
	public TanneryItem(String name)
	{
		this.setUnlocalizedName(name);
		this.setCreativeTab(Tannery.tabTannery);
	}
}
