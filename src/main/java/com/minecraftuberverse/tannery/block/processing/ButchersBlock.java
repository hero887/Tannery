package com.minecraftuberverse.tannery.block.processing;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.block.TanneryBlockDirectional;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;

public class ButchersBlock extends TanneryBlockDirectional
{
	public ButchersBlock()
	{
		super(Material.wood);
		this.setUnlocalizedName("butcherBlock");
		this.setHarvestLevel("axe", 1);
		this.setHardness(1.0F);
		this.setCreativeTab(Tannery.tabTannery);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}
}
