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

		TanneryItems.registerRenderers();
		TanneryBlocks.registerRenderers();
	}
}
