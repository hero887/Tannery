package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.init.TanneryBlock;
import com.minecraftuberverse.tannery.init.TanneryItem;

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

		TanneryItem.registerRenderers();
		TanneryBlock.registerRenderers();
	}
}
