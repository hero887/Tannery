package com.minecraftuberverse.tannery.block;

import com.minecraftuberverse.tannery.Tannery;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TanneryBlock extends Block
{
	public TanneryBlock(Material materialIn)
	{
		super(materialIn);
		this.setCreativeTab(Tannery.tabTannery);
	}
}
