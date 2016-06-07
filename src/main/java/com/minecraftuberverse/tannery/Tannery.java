package com.minecraftuberverse.tannery;

import com.minecraftuberverse.tannery.proxy.CommonProxy;
import com.minecraftuberverse.ubercore.util.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, canBeDeactivated = true, dependencies = "required-after:ubercore@1.8-1.0")
public class Tannery
{
	@Instance(Reference.MOD_ID)
	public static Tannery modInstance;

	@SidedProxy(clientSide = "com.minecraftuberverse.tannery.proxy.ClientProxy", serverSide = "com.minecraftuberverse.tannery.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabTannery;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new Logger(Reference.MOD_NAME);
		tabTannery = new CreativeTabs(CreativeTabs.getNextID(), "tabTannery")
		{
			@Override
			public Item getTabIconItem()
			{
				// TODO Assign proper item as icon
				return Items.leather;
			}
		};
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
}
