package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.item.TanneryItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TanneryItems
{
	public static TanneryItem bloodyCowCarcass;
	public static TanneryItem bloodyPigCarcass;
	public static TanneryItem bloodySheepCarcass;
	public static TanneryItem cowCarcass;
	public static TanneryItem pigCarcass;
	public static TanneryItem sheepCarcass;

	public static void init()
	{
		bloodyCowCarcass = new TanneryItem("bloody_carcass_cow");
		bloodyPigCarcass = new TanneryItem("bloody_carcass_pig");
		bloodySheepCarcass = new TanneryItem("bloody_carcass_sheep");
		cowCarcass = new TanneryItem("carcass_cow");
		pigCarcass = new TanneryItem("carcass_pig");
		sheepCarcass = new TanneryItem("carcass_sheep");
	}

	public static void register()
	{

	}

	public static void registerRenderers()
	{
		registerItemRenderer(bloodyCowCarcass);
		registerItemRenderer(bloodyPigCarcass);
		registerItemRenderer(bloodySheepCarcass);
		registerItemRenderer(cowCarcass);
		registerItemRenderer(pigCarcass);
		registerItemRenderer(sheepCarcass);
	}

	public static void registerItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(
						Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
						"inventory"));
	}
}
