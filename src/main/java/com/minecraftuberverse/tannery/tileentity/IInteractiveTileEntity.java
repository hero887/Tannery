package com.minecraftuberverse.tannery.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IInteractiveTileEntity
{
	public boolean onActivate(World world, BlockPos pos, EntityPlayer player, ItemStack stack);
}
