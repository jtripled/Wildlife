package com.jtripled.wildlife.mob.model;

import com.jtripled.wildlife.mob.MobPenguin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 *
 * @author jtripled
 */
public class ModelPenguin extends ModelBase
{
    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer beak;
    private final ModelRenderer flipperRight;
    private final ModelRenderer flipperLeft;
    private final ModelRenderer feetLeft;
    private final ModelRenderer feetRight;
    private final ModelRenderer tail;

    public ModelPenguin()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.beak = new ModelRenderer(this, 18, 0);
        this.beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.beak.addBox(-0.5F, -3.0F, -4.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(beak, 0.08726646259971647F, -0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 12.0F, 1.0F);
        this.body.addBox(-2.5F, 0.0F, -2.0F, 5, 11, 5, 0.0F);
        this.feetRight = new ModelRenderer(this, 0, 25);
        this.feetRight.setRotationPoint(-1.0F, 11.0F, 0.0F);
        this.feetRight.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(feetRight, 0.0F, 0.2617993877991494F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 5, 0.0F);
        this.tail = new ModelRenderer(this, 20, 20);
        this.tail.setRotationPoint(0.0F, 11.0F, 3.0F);
        this.tail.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(tail, 1.2566370614359172F, 0.0F, 0.0F);
        this.flipperRight = new ModelRenderer(this, 20, 10);
        this.flipperRight.setRotationPoint(-2.5F, 1.0F, 0.0F);
        this.flipperRight.addBox(-1.0F, 0.0F, -1.0F, 1, 7, 3, 0.0F);
        this.setRotateAngle(flipperRight, 0.0F, 0.0F, 0.08726646259971647F);
        this.feetLeft = new ModelRenderer(this, 0, 25);
        this.feetLeft.mirror = true;
        this.feetLeft.setRotationPoint(1.0F, 11.0F, 0.0F);
        this.feetLeft.addBox(0.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(feetLeft, 0.0F, -0.2617993877991494F, 0.0F);
        this.flipperLeft = new ModelRenderer(this, 20, 10);
        this.flipperLeft.mirror = true;
        this.flipperLeft.setRotationPoint(2.5F, 1.0F, 0.0F);
        this.flipperLeft.addBox(0.0F, 0.0F, -1.0F, 1, 7, 3, 0.0F);
        this.setRotateAngle(flipperLeft, 0.0F, 0.0F, -0.08726646259971647F);
        this.head.addChild(this.beak);
        this.body.addChild(this.feetRight);
        this.body.addChild(this.tail);
        this.body.addChild(this.flipperRight);
        this.body.addChild(this.feetLeft);
        this.body.addChild(this.flipperLeft);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 6.0F * scale, 0.0F);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.4F / f, 1.0F / f, 1.2F / f);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
        }
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
    {
        MobPenguin penguin = (MobPenguin) entity;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleZ = (MathHelper.cos(limbSwing * 1.3324F) * 1.4F * limbSwingAmount) / 6;
        this.body.rotateAngleZ = (MathHelper.cos(limbSwing * 1.3324F) * 1.4F * limbSwingAmount) / 6;
        this.feetRight.rotateAngleX = MathHelper.cos(limbSwing * 1.3324F) * 1.2F * limbSwingAmount;
        this.feetLeft.rotateAngleX = MathHelper.cos(limbSwing * 1.3324F + (float) Math.PI) * 1.2F * limbSwingAmount;
        this.flipperRight.rotateAngleZ = 0.08726646259971647F + (MathHelper.cos((float) penguin.rotationFlipper) * limbSwingAmount);
        this.flipperLeft.rotateAngleZ = -0.08726646259971647F + (MathHelper.cos((float) penguin.rotationFlipper + (float) Math.PI) * limbSwingAmount);
        this.tail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * 1.4F * limbSwingAmount;
    }
}
