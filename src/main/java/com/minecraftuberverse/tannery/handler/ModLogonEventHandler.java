package com.minecraftuberverse.tannery.handler;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ModLogonEventHandler
{
	private final String PREFIX = "-> ";

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e)
	{
		System.out.println("I blame George");
		EntityPlayer player = (EntityPlayer) e.player;
		if (!player.worldObj.isRemote)
		{
			ChatComponentText prefix = new ChatComponentText(
					EnumChatFormatting.GOLD + "Thank you for downloading The Tannery Mod");
			prefix.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(
							"Made By SoggyMustache, Hero887, LewisMcReu, BubbleTrouble, TheJurrasicAlien, Wolfgank, RdV01")));
			player.addChatMessage(prefix);


	}
}
}
