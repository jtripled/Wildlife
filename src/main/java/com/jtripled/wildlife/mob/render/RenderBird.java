package com.jtripled.wildlife.mob.render;

import com.jtripled.wildlife.Wildlife;
import com.jtripled.wildlife.mob.MobBird;
import com.jtripled.wildlife.mob.model.ModelBird;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 *
 * @author jtripled
 */
public class RenderBird extends RenderLiving<MobBird>
{
    public static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(Wildlife.ID, "textures/entity/bird_blue.png");
    public static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(Wildlife.ID, "textures/entity/bird_brown.png");
    public static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(Wildlife.ID, "textures/entity/bird_yellow.png");
    public static final ResourceLocation TEXTURE_RED = new ResourceLocation(Wildlife.ID, "textures/entity/bird_red.png");
    
    public RenderBird(RenderManager manager)
    {
        super(manager, new ModelBird(), 0.5f);
    }
    
    
    @Override
    protected float handleRotationFloat(MobBird living, float time)
    {
        float var3 = living.lastFlapLength + (living.flapLength - living.lastFlapLength) * time;
        float var4 = living.lastFlapIntensity + (living.flapIntensity - living.lastFlapIntensity) * time;
        return (MathHelper.sin(var3) + 1.0F) * var4;
    }
    
    @Override
    protected ResourceLocation getEntityTexture(MobBird entity)
    {
        switch (entity.getBirdType())
        {
            case 0: return TEXTURE_BLUE;
            case 1: return TEXTURE_BROWN;
            case 2: return TEXTURE_YELLOW;
            case 3: return TEXTURE_RED;
            default: return TEXTURE_BLUE;
        }
    }
}
