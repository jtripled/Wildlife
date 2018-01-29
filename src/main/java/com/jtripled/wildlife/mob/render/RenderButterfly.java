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
    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(Wildlife.ID, "textures/entity/butterfly_blue.png");
    private static final ResourceLocation TEXTURE_ORANGE = new ResourceLocation(Wildlife.ID, "textures/entity/butterfly_orange.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(Wildlife.ID, "textures/entity/butterfly_purple.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(Wildlife.ID, "textures/entity/butterfly_white.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(Wildlife.ID, "textures/entity/butterfly_yellow.png");

    public RenderButterfly(RenderManager manager)
    {
        super(manager, new ModelButterfly(), 0.0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(MobButterfly entity)
    {
        switch (entity.getButterflyType())
        {
            case 0: return TEXTURE_BLUE;
            case 1: return TEXTURE_ORANGE;
            case 2: return TEXTURE_PURPLE;
            case 3: return TEXTURE_WHITE;
            case 4: return TEXTURE_YELLOW;
            default: return TEXTURE_YELLOW;
        }
    }
}
