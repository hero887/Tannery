package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.block.processing.BlockButchersBench;
import com.minecraftuberverse.tannery.block.processing.BlockGallows;
import com.minecraftuberverse.tannery.block.quebracho.BlockLeavesQuebracho;
import com.minecraftuberverse.tannery.block.quebracho.BlockLogQuebracho;
import com.minecraftuberverse.tannery.block.quebracho.BlockSaplingQuebracho;
import com.minecraftuberverse.tannery.item.itemblock.ItemGallows;
import com.minecraftuberverse.tannery.tileentity.TileEntityButchersBench;
import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryBlocks
{
	public static BlockButchersBench butcherBench;
	public static BlockGallows gallows;
	public static BlockSaplingQuebracho quebrachoSapling;
	public static BlockLogQuebracho quebrachoLog;
	public static BlockLeavesQuebracho quebrachoLeaves;

	public static void init()
	{
		butcherBench = new BlockButchersBench();
		registerBlock(butcherBench);
		GameRegistry.registerTileEntity(TileEntityButchersBench.class, "tileEntityButchersBench");

		gallows = new BlockGallows();
		registerBlockWithCustomItemBlock(gallows, ItemGallows.class);
		GameRegistry.registerTileEntity(TileEntityGallows.class, "tileEntityGallows");

		quebrachoSapling = new BlockSaplingQuebracho();
		registerBlockWithStateBuilder(quebrachoSapling,
				new StateMap.Builder().addPropertiesToIgnore(BlockSaplingQuebracho.TYPE, BlockSaplingQuebracho.STAGE));
		quebrachoLog = new BlockLogQuebracho();
		registerBlockWithStateBuilder(quebrachoLog, new StateMap.Builder().addPropertiesToIgnore(BlockLogQuebracho.VARIANT));
		quebrachoLeaves = new BlockLeavesQuebracho();
		registerBlock(quebrachoLeaves);
	}

	private static void registerBlockWithStateBuilder(Block block, StateMap.Builder builder)
	{
		ModelLoader.setCustomStateMapper(block, builder.build());
		registerBlock(block);
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
				new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		item.setCreativeTab(Tannery.tabTannery);
	}
}
