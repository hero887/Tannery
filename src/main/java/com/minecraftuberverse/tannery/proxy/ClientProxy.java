package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.block.TanneryBlock;
import com.minecraftuberverse.tannery.item.TanneryItem;

public class ClientProxy extends ServerProxy{
	public void registerRenderThings(){

		//RenderingRegistry.registerEntityRenderingHandler(RidePat.class,new RenderPat(new ModelBentPlayer(), 0));

		
		TanneryItem.registerRenders();
		TanneryBlock.registerRenders();
		
	}

}
