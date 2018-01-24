package com.jtripled.wildlife.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author jtripled
 */
@SideOnly(Side.CLIENT)
public class ModelButterfly extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer wingRight;
    public ModelRenderer wingLeft;

    public ModelButterfly()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        head = new ModelRenderer(this, 4, 0);
        head.addBox(-1F, 0F, -2F, 3, 1, 2);
        head.setRotationPoint(0F, 21.5F, 0F);
        head.rotateAngleX = 0.0f;
        head.rotateAngleY = 0.0f;
        head.rotateAngleZ = 0.0f;
        
        body = new ModelRenderer(this, 0, 0);
        body.addBox(0F, -2F, -1F, 1, 5, 1);
        body.setRotationPoint(0F, 21F, 2F);
        body.rotateAngleX = 1.570796f;
        body.rotateAngleY = 0.0f;
        body.rotateAngleZ = 0.0f;
        
        wingRight = new ModelRenderer(this, 0, 6);
        wingRight.addBox(-6F, 0F, -4F, 6, 1, 9);
        wingRight.setRotationPoint(0F, 21.5F, 2F);
        wingRight.rotateAngleX = 0.0f;
        wingRight.rotateAngleY = 0.0f;
        wingRight.rotateAngleZ = 0.0f;
        
        wingLeft = new ModelRenderer(this, 0, 16);
        wingLeft.addBox(0F, 0F, -4F, 6, 1, 9);
        wingLeft.setRotationPoint(1F, 21.5F, 2F);
        wingLeft.rotateAngleX = 0.0f;
        wingLeft.rotateAngleY = 0.0f;
        wingLeft.rotateAngleZ = 0.0f;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.head.render(scale);
        this.body.render(scale);
        this.wingLeft.render(scale);
        this.wingRight.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        wingRight.rotateAngleZ = -(MathHelper.cos(ageInTicks * 1.7f) * (float) Math.PI * 0.2f);
        wingLeft.rotateAngleZ = MathHelper.cos(ageInTicks * 1.7f) * (float) Math.PI * 0.2f;
    }
}
