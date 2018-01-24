package com.jtripled.wildlife.mob.render;

import com.jtripled.wildlife.Wildlife;
import com.jtripled.wildlife.mob.MobDeer;
import com.jtripled.wildlife.mob.model.ModelDeer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author jtripled
 */
public class RenderDeer extends RenderLiving<MobDeer>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(Wildlife.ID, "textures/entity/deer.png");
    
    public RenderDeer(RenderManager manager)
    {
        super(manager, new ModelDeer(), 0.5f);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(MobDeer entity)
    {
        return TEXTURE;
    }
}
