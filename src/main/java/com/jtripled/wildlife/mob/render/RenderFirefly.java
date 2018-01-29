package com.jtripled.wildlife.mob.render;

import com.jtripled.wildlife.Wildlife;
import com.jtripled.wildlife.mob.MobFirefly;
import com.jtripled.wildlife.mob.model.ModelFirefly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author jtripled
 */
public class RenderFirefly extends RenderLiving<MobFirefly>
{
    private static final ModelFirefly MODEL = new ModelFirefly();
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wildlife.ID, "textures/entity/firefly.png");

    public RenderFirefly(RenderManager manager)
    {
        super(manager, new ModelFirefly(), 0.0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(MobFirefly entity)
    {
        return TEXTURE;
    }
}
