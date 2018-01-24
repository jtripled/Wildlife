package com.jtripled.wildlife.mob.model;

import com.jtripled.wildlife.mob.MobFirefly;
import java.nio.FloatBuffer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author jtripled
 */
@SideOnly(Side.CLIENT)
public class ModelFirefly extends ModelBase
{
    public ModelRenderer glow;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer wingRight;
    public ModelRenderer wingLeft;

    public ModelFirefly()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        glow = new ModelRenderer(this, 20, 0);
        glow.addBox(-5.0f, -5.0f, -0.0f, 10, 10, 0, 0.0f);
        
        head = new ModelRenderer(this, 4, 0);
        head.addBox(-1F, 0F, -2F, 3, 1, 2);
        head.setRotationPoint(0F, 21.5F, 0F);
        head.rotateAngleX = 0.0f;
        head.rotateAngleY = 0.0f;
        head.rotateAngleZ = 0.0f;
        
        body = new ModelRenderer(this, 0, 0);
        body.addBox(0F, -2F, -1F, 1, 6, 1);
        body.setRotationPoint(0F, 21F, 2F);
        body.rotateAngleX = 1.570796f;
        body.rotateAngleY = 0.0f;
        body.rotateAngleZ = 0.0f;
        
        wingRight = new ModelRenderer(this, 0, 10);
        wingRight.addBox(-8F, 0F, -5.5F, 8, 1, 9);
        wingRight.setRotationPoint(0F, 21.5F, 2F);
        wingRight.rotateAngleX = 0.0f;
        wingRight.rotateAngleY = 0.0f;
        wingRight.rotateAngleZ = 0.0f;
        
        wingLeft = new ModelRenderer(this, 0, 20);
        wingLeft.addBox(0F, 0F, -5.5F, 8, 1, 9);
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
        
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.03f, 1.35f, 0.35f);
        FloatBuffer modelview = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelview);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 4 + j;
                if (i == j) {
                    modelview.put(index, 1.0f);
                } else {
                    modelview.put(index, 0.0f);
                }
            }
        }
        GL11.glLoadMatrix(modelview);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, ((MobFirefly) entity).getGlowBrightness());
        this.glow.render(0.0625f * 1.0f);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        wingRight.rotateAngleZ = -(MathHelper.cos(ageInTicks * 1.5f) * (float) Math.PI * 0.2f);
        wingLeft.rotateAngleZ = MathHelper.cos(ageInTicks * 1.5f) * (float) Math.PI * 0.2f;
    }
}
