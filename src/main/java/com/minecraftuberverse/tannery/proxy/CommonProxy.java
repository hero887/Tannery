package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.handler.ModLogonEventHandler;
import com.minecraftuberverse.tannery.init.TanneryBlock;
import com.minecraftuberverse.tannery.init.TanneryItem;

import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class CommonProxy
{
	public abstract void init();

	public void preInit()
	{
		TanneryItem.init();
		TanneryItem.register();
		TanneryBlock.init();
		TanneryBlock.register();

		FMLCommonHandler.instance().bus().register(new ModLogonEventHandler());
	}

	public void postInit()
	{

	}
}
