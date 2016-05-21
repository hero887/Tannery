package com.minecraftuberverse.tannery.handler;

import java.util.Iterator;
import java.util.List;

import com.minecraftuberverse.tannery.init.TanneryItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityDropsHandler
{
	@SubscribeEvent
	public void onEntityDrops(LivingDropsEvent event)
	{
		Entity e = event.entity;
		List<EntityItem> drops = event.drops;
		// Remove leather from cow drops
		if (e instanceof EntityCow)
		{
			Iterator<EntityItem> it = drops.iterator();
			while (it.hasNext())
			{
				EntityItem i = it.next();
				if (i.getEntityItem().getItem().equals(Items.leather))
				{
					it.remove();
				}
			}
		}

		// Add carcass to sheep and cow drops
		if (e instanceof EntitySheep || e instanceof EntityCow)
		{
			if (((EntityAnimal) e).getGrowingAge() == 0) e.dropItem(TanneryItems.carcass, 1);
		}
	}
}
