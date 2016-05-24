package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.item.TanneryItem;
import com.minecraftuberverse.tannery.util.CarcassType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryItems
{
	public static TanneryItem bloodyCowCarcass;
	public static TanneryItem bloodyPigCarcass;
	public static TanneryItem bloodySheepCarcass;
	public static TanneryItem bloodyElkCarcass;
	public static TanneryItem drainedCowCarcass;
	public static TanneryItem drainedPigCarcass;
	public static TanneryItem drainedSheepCarcass;
	public static TanneryItem drainedElkCarcass;
	public static TanneryItem cowCarcass;
	public static TanneryItem pigCarcass;
	public static TanneryItem sheepCarcass;
	public static TanneryItem elkCarcass;
	public static TanneryItem antler;

	public static void init()
	{
		bloodyCowCarcass = new ItemCarcass(CarcassType.COW, true);
		bloodyPigCarcass = new ItemCarcass(CarcassType.PIG, true);
		bloodySheepCarcass = new ItemCarcass(CarcassType.SHEEP, true);
		bloodyElkCarcass = new ItemCarcass(CarcassType.ELK, true);
		drainedCowCarcass = new ItemCarcass(CarcassType.COW, false);
		drainedPigCarcass = new ItemCarcass(CarcassType.PIG, false);
		drainedSheepCarcass = new ItemCarcass(CarcassType.SHEEP, false);
		drainedElkCarcass = new ItemCarcass(CarcassType.ELK, false);
		cowCarcass = new ItemCarcass(CarcassType.COW);
		pigCarcass = new ItemCarcass(CarcassType.PIG);
		sheepCarcass = new ItemCarcass(CarcassType.SHEEP);
		elkCarcass = new ItemCarcass(CarcassType.ELK);
		antler = new TanneryItem("antler");

		register(bloodyCowCarcass);
		register(bloodyPigCarcass);
		register(bloodySheepCarcass);
		register(bloodyElkCarcass);
		register(drainedCowCarcass);
		register(drainedPigCarcass);
		register(drainedSheepCarcass);
		register(drainedElkCarcass);
		register(cowCarcass);
		register(pigCarcass);
		register(sheepCarcass);
		register(elkCarcass);
		register(antler);
	}

	private static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerRenderers()
	{
		registerItemRenderer(bloodyCowCarcass);
		registerItemRenderer(bloodyPigCarcass);
		registerItemRenderer(bloodySheepCarcass);
		registerItemRenderer(bloodyElkCarcass);
		registerItemRenderer(drainedCowCarcass);
		registerItemRenderer(drainedPigCarcass);
		registerItemRenderer(drainedSheepCarcass);
		registerItemRenderer(drainedElkCarcass);
		registerItemRenderer(cowCarcass);
		registerItemRenderer(pigCarcass);
		registerItemRenderer(sheepCarcass);
		registerItemRenderer(elkCarcass);
		registerItemRenderer(antler);
	}

	public static void registerItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(
						Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
						"inventory"));
	}
}
