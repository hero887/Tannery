package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.block.blocktype.ButchersBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryBlocks
{
	// This makes the new block
	public static Block butcherBlock;

	public static void init()
	{
		// This method gives the block it's properties
		butcherBlock = new ButchersBlock(Material.wood);
	}

	public static void register()
	{
		// This method adds the block into the game
		GameRegistry.registerBlock(butcherBlock, butcherBlock.getUnlocalizedName().substring(5));
	}

	public static void registerRenderers()
	{
		// This method registers the block model
		registerRender(butcherBlock);
	}

	// This is used to make the registerRenders() method work
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(
						Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
						"inventory"));
	}
}
