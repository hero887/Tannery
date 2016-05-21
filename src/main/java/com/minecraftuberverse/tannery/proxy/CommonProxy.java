package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.handler.EntityDropsHandler;
import com.minecraftuberverse.tannery.handler.ModLogonEventHandler;
import com.minecraftuberverse.tannery.init.TanneryBlocks;
import com.minecraftuberverse.tannery.init.TanneryItems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class CommonProxy
{
	public void preInit()
	{
		TanneryItems.init();
		TanneryItems.register();
		TanneryBlocks.init();
		TanneryBlocks.register();

		FMLCommonHandler.instance().bus().register(new ModLogonEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityDropsHandler());
	}

	public abstract void init();

	public void postInit()
	{

	}
}
