package com.minecraftuberverse.tannery.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HypothermiaHandler
{
	@SubscribeEvent
	public void onPlayerAboveHeight(LivingEvent.LivingJumpEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) event.entityLiving;

			if (p.getEntityWorld().getBiomeGenForCoords(p.playerLocation).isSnowyBiome())
			{

			}

			if (p.getPosition().getY() > 130)
			{

			}
		}
	}
}
