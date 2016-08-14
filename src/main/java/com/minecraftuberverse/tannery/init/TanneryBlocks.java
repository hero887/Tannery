package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.block.processing.BlockButchersBench;
import com.minecraftuberverse.tannery.block.processing.BlockGallows;
import com.minecraftuberverse.tannery.item.itemblock.ItemGallows;
import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;
import com.minecraftuberverse.ubercore.tileentity.TileEntityMachine;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryBlocks
{
	public static Block butcherBench;
	public static Block gallows;

	public static void init()
	{
		// This method gives the block it's properties
		butcherBench = new BlockButchersBench();
		registerBlock(butcherBench);

		gallows = new BlockGallows();
		registerBlockWithCustomItemBlock(gallows, ItemGallows.class);
		GameRegistry.registerTileEntity(TileEntityGallows.class, "tileEntityGallows");
		TileEntityMachine.registerMachine(TileEntityGallows.RECIPE_HANDLER_KEY, 1, 5);
	}

	// This method adds the block into the game
	private static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	private static void registerBlockWithCustomItemBlock(Block block, Class<? extends ItemBlock> itemclass)
	{
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}

	// This method registers the block model
	public static void registerRenderers()
	{
		registerRender(butcherBench);
	}

	// This is used to make the registerRenders() method work
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(
						Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
						"inventory"));
		item.setCreativeTab(Tannery.tabTannery);
	}
}
