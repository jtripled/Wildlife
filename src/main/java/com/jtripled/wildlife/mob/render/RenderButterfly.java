package com.jtripled.wildlife.mob.render;

import com.jtripled.wildlife.Wildlife;
import com.jtripled.wildlife.mob.MobButterfly;
import com.jtripled.wildlife.mob.model.ModelButterfly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author jtripled
 */
public class RenderButterfly extends RenderLiving<MobButterfly>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wildlife.ID, "textures/entity/butterfly_blue.png");

    public RenderButterfly(RenderManager manager)
    {
        super(manager, new ModelButterfly(), 0.25f);
        this.shadowSize = 0.0f;
    }

    @Override
    protected ResourceLocation getEntityTexture(MobButterfly entity)
    {
        return TEXTURE;
    }
}
