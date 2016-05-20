package com.minecraftuberverse.tannery;

import com.minecraftuberverse.tannery.block.TanneryBlock;
import com.minecraftuberverse.tannery.handler.ModLogonEventHandler;
import com.minecraftuberverse.tannery.item.TanneryItem;
import com.minecraftuberverse.tannery.proxy.ServerProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Tannery {

	ModLogonEventHandler logon = new ModLogonEventHandler();

	
	@Instance("tannery")
	public static Tannery modInstance;


	@SidedProxy(clientSide = "com.minecraftuberverse.tannery.proxy.ClientProxy", serverSide ="com.minecraftuberverse.tannery.proxy.ServerProxy")
	public static ServerProxy proxy;
	

	


	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		TanneryItem.init();
		TanneryItem.register();
		TanneryBlock.init();
		TanneryBlock.register();

		FMLCommonHandler.instance().bus().register(logon);
		MinecraftForge.EVENT_BUS.register(logon);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderThings();
		
		
		
		
	}
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	

		
	}
	
	


};