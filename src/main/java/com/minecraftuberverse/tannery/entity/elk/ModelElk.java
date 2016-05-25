package com.minecraftuberverse.tannery.entity.elk;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelElk extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer chest;
    public ModelRenderer Leg1BL;
    public ModelRenderer Leg1BR;
    public ModelRenderer neck;
    public ModelRenderer leg1L;
    public ModelRenderer leg1R;
    public ModelRenderer head;
    public ModelRenderer mouth;
    public ModelRenderer lowjaw;
    public ModelRenderer hornR;
    public ModelRenderer hornL;
    public ModelRenderer Leg2L;
    public ModelRenderer Leg2R;
    public ModelRenderer Leg2BL;
    public ModelRenderer Leg2BR;

    public ModelElk() {
        this.textureWidth = 250;
        this.textureHeight = 150;
        this.leg1R = new ModelRenderer(this, 30, 70);
        this.leg1R.setRotationPoint(-4.5F, 4.0F, -10.0F);
        this.leg1R.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 7, 0.0F);
        this.Leg1BL = new ModelRenderer(this, 50, 90);
        this.Leg1BL.setRotationPoint(4.5F, 3.0F, 16.0F);
        this.Leg1BL.addBox(-3.0F, 0.0F, -4.0F, 6, 10, 9, 0.0F);
        this.setRotateAngle(Leg1BL, 0.136659280431156F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.body.addBox(-7.0F, -7.0F, 0.0F, 14, 14, 22, 0.0F);
        this.setRotateAngle(body, -0.136659280431156F, 0.0F, 0.0F);
        this.Leg2BR = new ModelRenderer(this, 27, 92);
        this.Leg2BR.setRotationPoint(-2.0F, 9.0F, 0.0F);
        this.Leg2BR.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.Leg2L = new ModelRenderer(this, 0, 92);
        this.Leg2L.setRotationPoint(-2.5F, 9.0F, -0.5F);
        this.Leg2L.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 40);
        this.head.setRotationPoint(-0.4F, -4.0F, -13.2F);
        this.head.addBox(-5.0F, 0.0F, -5.0F, 9, 9, 9, 0.0F);
        this.setRotateAngle(head, -0.31869712141416456F, 0.0F, 0.0F);
        this.leg1L = new ModelRenderer(this, 0, 70);
        this.leg1L.setRotationPoint(4.5F, 4.0F, -10.0F);
        this.leg1L.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 7, 0.0F);
        this.Leg2R = new ModelRenderer(this, 27, 92);
        this.Leg2R.setRotationPoint(-1.5F, 9.0F, -0.5F);
        this.Leg2R.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.lowjaw = new ModelRenderer(this, 45, 55);
        this.lowjaw.setRotationPoint(-2.0F, 7.0F, 1.5F);
        this.lowjaw.addBox(0.0F, 0.0F, 0.0F, 3, 5, 2, 0.0F);
        this.Leg1BR = new ModelRenderer(this, 50, 90);
        this.Leg1BR.setRotationPoint(-4.5F, 3.0F, 16.0F);
        this.Leg1BR.addBox(-3.0F, 0.0F, -4.0F, 6, 10, 9, 0.0F);
        this.setRotateAngle(Leg1BR, 0.136659280431156F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 142, 0);
        this.neck.setRotationPoint(1.0F, 2.5F, -11.0F);
        this.neck.addBox(-6.0F, -6.0F, -16.0F, 10, 10, 16, 0.0F);
        this.setRotateAngle(neck, -1.1383037381507017F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 39, 40);
        this.mouth.setRotationPoint(0.0F, 7.5F, -1.0F);
        this.mouth.addBox(-3.0F, 0.0F, -3.0F, 5, 6, 5, 0.0F);
        this.setRotateAngle(mouth, 0.31869712141416456F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 76, 0);
        this.chest.setRotationPoint(0.0F, -0.5F, 7.0F);
        this.chest.addBox(-8.0F, -8.0F, -16.0F, 16, 16, 16, 0.0F);
        this.setRotateAngle(chest, 0.136659280431156F, 0.0F, 0.0F);
        this.hornR = new ModelRenderer(this, -3, 121);
        this.hornR.setRotationPoint(-8.0F, 1.0F, -3.0F);
        this.hornR.addBox(-20.0F, 0.0F, -14.0F, 26, 1, 16, 0.0F);
        this.setRotateAngle(hornR, -0.36425021489121656F, 0.0F, 0.136659280431156F);
        this.hornL = new ModelRenderer(this, 86, 121);
        this.hornL.setRotationPoint(1.0F, 1.0F, -3.0F);
        this.hornL.addBox(0.0F, 0.0F, -14.0F, 26, 1, 16, 0.0F);
        this.setRotateAngle(hornL, -0.36425021489121656F, 0.0F, -0.136659280431156F);
        this.Leg2BL = new ModelRenderer(this, 0, 92);
        this.Leg2BL.setRotationPoint(-2.0F, 9.0F, 0.0F);
        this.Leg2BL.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.chest.addChild(this.leg1R);
        this.body.addChild(this.Leg1BL);
        this.Leg1BR.addChild(this.Leg2BR);
        this.leg1L.addChild(this.Leg2L);
        this.neck.addChild(this.head);
        this.chest.addChild(this.leg1L);
        this.leg1R.addChild(this.Leg2R);
        this.head.addChild(this.lowjaw);
        this.body.addChild(this.Leg1BR);
        this.chest.addChild(this.neck);
        this.head.addChild(this.mouth);
        this.body.addChild(this.chest);
        this.head.addChild(this.hornR);
        this.head.addChild(this.hornL);
        this.Leg1BL.addChild(this.Leg2BL);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
