package com.minecraftuberverse.tannery.block;

import com.minecraftuberverse.tannery.Tannery;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TanneryBlock extends Block
{
	public TanneryBlock(Material materialIn, String unlocalizedName)
	{
		super(materialIn);
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(Tannery.tabTannery);
	}
}
