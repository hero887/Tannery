package com.minecraftuberverse.tannery;

import org.apache.logging.log4j.Logger;

import com.minecraftuberverse.tannery.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, canBeDeactivated = true)
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
		logger = event.getModLog();

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
