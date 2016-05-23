package com.minecraftuberverse.tannery.entity.damage;

import net.minecraft.util.DamageSource;

public class DamageSourceHypothermia extends DamageSource
{
	public DamageSourceHypothermia()
	{
		super("tannery.hypothermia");
		setDamageBypassesArmor();
		setDamageIsAbsolute();
	}
}
