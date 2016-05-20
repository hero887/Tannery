package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.block.TanneryBlock;
import com.minecraftuberverse.tannery.item.TanneryItem;

public class ServerProxy{
	public void registerRenderThings(){
		
	
		TanneryItem.registerRenders();
		TanneryBlock.registerRenders();
	}

}
