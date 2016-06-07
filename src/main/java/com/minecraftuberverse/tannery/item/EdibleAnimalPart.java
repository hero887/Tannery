package com.minecraftuberverse.tannery.item;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

//SoggyMustache
public class EdibleAnimalPart extends ItemFood{

	public EdibleAnimalPart(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
	}
	{
		this.setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
	}
}
