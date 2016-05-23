package com.minecraftuberverse.tannery.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

/**
 * @author Lewis_McReu
 */
public class PotionEffectHypothermia extends PotionEffect
{

	public PotionEffectHypothermia(int id, int effectDuration, int effectAmplifier, boolean ambient, boolean showParticles)
	{
		super(id, effectDuration, effectAmplifier, ambient, showParticles);
	}

	@Override
	public void performEffect(EntityLivingBase entityIn)
	{
		// TODO Auto-generated method stub
		super.performEffect(entityIn);
	}
}
