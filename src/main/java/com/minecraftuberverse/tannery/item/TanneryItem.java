package com.minecraftuberverse.tannery.item;

import com.minecraftuberverse.tannery.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class TanneryItem {
	
	

	public static void init() {
		
		
		
	}
	
	public static void register()
	{

		
		
		

	}
	
	public static void registerRenders()
	{

		

		
	}
	
	public static void registerRenders(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory")); 						
	}
}
