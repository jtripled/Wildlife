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

    public ModelFirefly()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        glow = new ModelRenderer(this, 20, 0);
        glow.addBox(-5.0f, -5.0f, -0.0f, 10, 10, 0, 0.0f);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        
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
}
