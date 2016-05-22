package com.minecraftuberverse.tannery.entity.elk;

import com.minecraftuberverse.tannery.init.TanneryItems;

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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Elk extends EntityAnimal
{

	public Elk(World worldIn)
	{
		super(worldIn);
		this.setSize(0.9F, 1.3F);
		((PathNavigateGround) this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.wheat, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIEatGrass(this));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));

	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
				.setBaseValue(0.20000000298023224D);
	}

	protected String getLivingSound()
	{
		return "tannery:mob.elk.say";
	}

	protected String getHurtSound()
	{
		return "tannery:mob.elk.hurt";
	}

	protected String getDeathSound()
	{
		return "tannery:mob.elk.hurt";
	}

	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
	{
		this.playSound("tannery:mob.elk.step", 0.15F, 1.0F);
	}

	protected float getSoundVolume()
	{
		return 0.4F;
	}

	protected Item getDropItem()
	{
		return TanneryItems.bloodyElkCarcass;
	}

	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
	{
		this.dropItem(getDropItem(), 1);
	}

	public boolean interact(EntityPlayer player)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack != null && itemstack
				.getItem() == Items.bucket && !player.capabilities.isCreativeMode)
		{
			if (itemstack.stackSize-- == 1)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem,
						new ItemStack(Items.milk_bucket));
			}
			else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
			{
				player.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0),
						false);
			}

			return true;
		}
		else
		{
			return super.interact(player);
		}
	}

	public Elk createChild(EntityAgeable ageable)
	{
		return new Elk(this.worldObj);
	}

	public float getEyeHeight()
	{
		return this.height;
	}
}