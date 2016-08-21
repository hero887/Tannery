package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.Reference;
import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.item.EdibleAnimalPart;
import com.minecraftuberverse.tannery.item.ItemCarcass;
import com.minecraftuberverse.tannery.item.TanneryItem;
import com.minecraftuberverse.tannery.util.CarcassType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryItems
{
	public static ItemCarcass bloodyCowCarcass;
	public static ItemCarcass bloodyPigCarcass;
	public static ItemCarcass bloodySheepCarcass;
	public static ItemCarcass bloodyElkCarcass;
	public static ItemCarcass drainedCowCarcass;
	public static ItemCarcass drainedPigCarcass;
	public static ItemCarcass drainedSheepCarcass;
	public static ItemCarcass drainedElkCarcass;
	public static ItemCarcass cowCarcass;
	public static ItemCarcass pigCarcass;
	public static ItemCarcass sheepCarcass;
	public static ItemCarcass elkCarcass;
	public static TanneryItem antler;
	public static TanneryItem boneKnife;
	public static EdibleAnimalPart animal_brain;;
	public static TanneryItem pigskin;
	public static TanneryItem sheepskin;
	public static TanneryItem cowHide;
	public static TanneryItem elkhide;
	public static TanneryItem barkSpud;
	public static TanneryItem bark;
	public static TanneryItem sinew;

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
		boneKnife = (TanneryItem) new TanneryItem("boneknife").setMaxDamage(255);
		pigskin = new TanneryItem("pigskin");
		sheepskin = new TanneryItem("sheepskin");
		cowHide = new TanneryItem("cowhide");
		elkhide = new TanneryItem("elkhide");
		animal_brain = new EdibleAnimalPart("animal_brain", 2, 1.0F, true);
		boneKnife = new TanneryItem("boneknife");
		barkSpud = new TanneryItem("barkSpud");
		bark = new TanneryItem("bark");
		sinew = new TanneryItem("sinew");
		
		register(sinew);
		register(bark);
		register(barkSpud);
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
		register(boneKnife);
		register(animal_brain);
		register(pigskin);
		register(sheepskin);
		register(cowHide);
		register(elkhide);
	}

	private static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerRenderers()
	{
		registerItemRenderer(sinew);
		registerItemRenderer(bark);
		registerItemRenderer(barkSpud);
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
		registerItemRenderer(boneKnife);
		registerItemRenderer(animal_brain);
	}

	public static void registerItemRenderer(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(
						Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
						"inventory"));
		item.setCreativeTab(Tannery.tabTannery);
	}
}
