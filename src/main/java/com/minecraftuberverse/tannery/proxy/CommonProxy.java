package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.entity.TanneryEntities;
import com.minecraftuberverse.tannery.handler.EntityDropsHandler;
import com.minecraftuberverse.tannery.handler.ModLogonEventHandler;
import com.minecraftuberverse.tannery.init.TanneryBlocks;
import com.minecraftuberverse.tannery.init.TanneryItems;
import com.minecraftuberverse.tannery.init.TanneryRecipes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class CommonProxy
{
	public void preInit()
	{
		TanneryItems.init();
		TanneryBlocks.init();
		TanneryEntities.init();
		FMLCommonHandler.instance().bus().register(new ModLogonEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityDropsHandler());
	}

	public void init()
	{
		TanneryRecipes.init();
	}

	public void postInit()
	{

	}
}
