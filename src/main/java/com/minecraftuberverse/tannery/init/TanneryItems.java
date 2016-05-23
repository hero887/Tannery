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
	public static TanneryItem cowCarcass;
	public static TanneryItem pigCarcass;
	public static TanneryItem sheepCarcass;
	public static TanneryItem elkCarcass;

	public static void init()
	{
		bloodyCowCarcass = new ItemCarcass(CarcassType.COW, true);
		bloodyPigCarcass = new ItemCarcass(CarcassType.PIG, true);
		bloodySheepCarcass = new ItemCarcass(CarcassType.SHEEP, true);
		bloodyElkCarcass = new ItemCarcass(CarcassType.ELK, true);
		cowCarcass = new ItemCarcass(CarcassType.COW);
		pigCarcass = new ItemCarcass(CarcassType.PIG);
		sheepCarcass = new ItemCarcass(CarcassType.SHEEP);
		elkCarcass = new ItemCarcass(CarcassType.ELK);

		register(bloodyCowCarcass);
		register(bloodyPigCarcass);
		register(bloodySheepCarcass);
		register(bloodyElkCarcass);
		register(cowCarcass);
		register(pigCarcass);
		register(sheepCarcass);
		register(elkCarcass);
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
		registerItemRenderer(cowCarcass);
		registerItemRenderer(pigCarcass);
		registerItemRenderer(sheepCarcass);
		registerItemRenderer(elkCarcass);
	}

	public static void registerItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(
						Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
						"inventory"));
	}
}
