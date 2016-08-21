package com.minecraftuberverse.tannery.item.itemblock;

import com.minecraftuberverse.tannery.Tannery;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author Lewis_McReu
 */
public class TanneryItemBlock extends ItemBlock
{
	public TanneryItemBlock(Block block)
	{
		super(block);
		this.setCreativeTab(Tannery.tabTannery);
	}
}
