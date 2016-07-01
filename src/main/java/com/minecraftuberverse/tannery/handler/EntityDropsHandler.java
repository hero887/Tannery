package com.minecraftuberverse.tannery.handler;

import java.util.Iterator;
import java.util.List;

import com.minecraftuberverse.tannery.init.TanneryItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Lewis_McReu
 */
public class EntityDropsHandler
{
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		Entity e = event.entity;
		List<EntityItem> drops = event.drops;
		// Remove all drops from cows, sheep and pigs, add carcass drop
		if (e instanceof EntityCow || e instanceof EntityPig || e instanceof EntitySheep)
		{
			EntityAnimal a = (EntityAnimal) e;
			Iterator<EntityItem> it = drops.iterator();
			while (it.hasNext())
			{
				EntityItem i = it.next();
				it.remove();
			}

			if (a.getGrowingAge() == 0)
			{
				if (a instanceof EntityCow) a.dropItem(TanneryItems.bloodyCowCarcass, 1);
				else if (a instanceof EntityPig) a.dropItem(TanneryItems.bloodyPigCarcass, 1);
				else if (a instanceof EntitySheep) a.dropItem(TanneryItems.bloodySheepCarcass, 1);
			}
		}
	}
}
