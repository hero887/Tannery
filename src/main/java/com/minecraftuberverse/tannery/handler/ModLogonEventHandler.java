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

			ChatComponentText url;
			Random rand = new Random();
			int sel = rand.nextInt(3);
			switch (sel)
			{
				case 1:
					player.addChatMessage(new ChatComponentText(
							EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.GREEN + "Make sure you get all the new updates at soggymustache.tk"));
					url = new ChatComponentText(
							EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.RESET + "soggymustache.tk");
					url.getChatStyle().setChatClickEvent(
							new ClickEvent(ClickEvent.Action.OPEN_URL, "http://soggymustache.tk"));
					url.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
							new ChatComponentText("Open URL")));
					player.addChatMessage(url);
					break;
				case 2:
					player.addChatMessage(new ChatComponentText(
							EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.GREEN + "Check out SoggyMustache's website for more mods"));
					url = new ChatComponentText(
							EnumChatFormatting.GOLD + PREFIX + EnumChatFormatting.RESET + "soggymustache.tk");
					url.getChatStyle().setChatClickEvent(
							new ClickEvent(ClickEvent.Action.OPEN_URL, "http://soggymustache.tk"));
					url.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
							new ChatComponentText("Open URL")));
					player.addChatMessage(url);
					break;
			}
			// ConfigurationHandler.hasDisplayedOnce = true;
		}
	}
}
