package com.minecraftuberverse.tannery.proxy;

import com.minecraftuberverse.tannery.entity.TanneryEntities;
import com.minecraftuberverse.tannery.handler.EntityDropsHandler;
import com.minecraftuberverse.tannery.handler.ModLogonEventHandler;
import com.minecraftuberverse.tannery.init.TanneryBlocks;
import com.minecraftuberverse.tannery.init.TanneryItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CommonProxy
{
	public void preInit()
	{
		TanneryItems.init();
		TanneryBlocks.init();
		TanneryEntities.init();
		FMLCommonHandler.instance().bus().register(new ModLogonEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityDropsHandler());
	}

	public void init()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { Item.getItemFromBlock(Blocks.wool) });
	}

	public void postInit()
	{

	}
}
