package com.minecraftuberverse.tannery.entity.babyelk;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ElkBaby extends EntityAnimal
{

	public ElkBaby(World worldIn)
	{
		super(worldIn);
		this.setSize(1.8F, 2.5F);
		((PathNavigateGround) this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3,
				new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.grass), false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIEatGrass(this));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
				.setBaseValue(0.20000000298023224D);
	}

	@Override
	protected String getLivingSound()
	{
		return "tannery:mob.elk.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "tannery:mob.elk.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "tannery:mob.elk.death";
	}

	@Override
	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
	{
		this.playSound("tannery:mob.elk.step", 0.15F, 1.0F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public ElkBaby createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height;
	}
}