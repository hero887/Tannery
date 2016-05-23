package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.block.processing.BlockGallows;
import com.minecraftuberverse.tannery.block.processing.ButchersBlock;
import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryBlocks
{
	// This makes the new block
	public static Block butcherBlock;
	public static Block gallowsBlock;

	public static void init()
	{
		// This method gives the block it's properties
		butcherBlock = new ButchersBlock();
		gallowsBlock = new BlockGallows();
		GameRegistry.registerTileEntity(TileEntityGallows.class, "tileEntityGallows");
		register(butcherBlock);
		register(gallowsBlock);
	}

	// This method adds the block into the game
	private static void register(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	// This method registers the block model
	public static void registerRenderers()
	{
		registerRender(butcherBlock);
		registerRender(gallowsBlock);
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
