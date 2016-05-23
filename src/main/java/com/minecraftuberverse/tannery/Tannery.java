package com.minecraftuberverse.tannery;

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

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Tannery
{
	@Instance(Reference.MOD_ID)
	public static Tannery modInstance;

	@SidedProxy(clientSide = "com.minecraftuberverse.tannery.proxy.ClientProxy", serverSide = "com.minecraftuberverse.tannery.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabTannery;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
		tabTannery = new CreativeTabs(CreativeTabs.getNextID(), "tabTannery")
		{
			@Override
			public Item getTabIconItem()
			{
				// TODO Auto-generated method stub
				return Items.leather;
			}
		};
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