package com.minecraftuberverse.tannery.entity.babyelk;

import com.minecraftuberverse.tannery.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBabyElk extends RenderLiving
{
	private static final ResourceLocation elkTextures = new ResourceLocation(
			Reference.MOD_ID + ":" + "textures/entity/elk/elk_normal.png");

	public RenderBabyElk(RenderManager r, ModelBase m, float f)
	{
		super(r, m, f);
	}

	protected ResourceLocation func_180572_a(ElkBaby e)
	{
		return elkTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.func_180572_a((ElkBaby) entity);
	}
}