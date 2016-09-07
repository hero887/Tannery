package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.entity.TanneryEntities;
import com.minecraftuberverse.tannery.handler.EntityDropsHandler;
import com.minecraftuberverse.tannery.init.TanneryBlocks;
import com.minecraftuberverse.tannery.init.TanneryItems;
import com.minecraftuberverse.tannery.init.TanneryRecipes;
import com.minecraftuberverse.tannery.worldgen.WorldGenQuebracho;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy
{
	public void preInit()
	{
		TanneryBlocks.init();
		TanneryItems.init();
		TanneryEntities.init();
		MinecraftForge.EVENT_BUS.register(new EntityDropsHandler());
		GameRegistry.registerWorldGenerator(new WorldGenQuebracho(false), 0);
	}

	public void init()
	{
		TanneryRecipes.init();
	}

	public void postInit()
	{

	}
}
