package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.entity.elk.Elk;
import com.minecraftuberverse.tannery.entity.elk.ModelElk;
import com.minecraftuberverse.tannery.entity.elk.RenderElk;
import com.minecraftuberverse.tannery.init.TanneryBlocks;
import com.minecraftuberverse.tannery.init.TanneryItems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init()
	{
		super.init();
		registerRenderers();
	}

	private void registerRenderers()
	{

		TanneryItems.registerRenderers();
		TanneryBlocks.registerRenderers();
		RenderingRegistry.registerEntityRenderingHandler(Elk.class, new RenderElk(Minecraft.getMinecraft().getRenderManager(), new ModelElk(), 0));
	}
}
