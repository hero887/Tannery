package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.init.TanneryBlocks;
import com.minecraftuberverse.tannery.init.TanneryItems;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		registerRenderers();
	}

	private void registerRenderers()
	{
		// RenderingRegistry.registerEntityRenderingHandler(RidePat.class,new
		// RenderPat(new ModelBentPlayer(), 0));

		TanneryItems.registerRenderers();
		TanneryBlocks.registerRenderers();
	}
}
