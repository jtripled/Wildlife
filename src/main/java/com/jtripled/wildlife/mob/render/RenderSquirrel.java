package com.jtripled.wildlife.mob.render;

import com.jtripled.wildlife.Wildlife;
import com.jtripled.wildlife.mob.MobSquirrel;
import com.jtripled.wildlife.mob.model.ModelSquirrel;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author jtripled
 */
public class RenderSquirrel extends RenderLiving<MobSquirrel>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(Wildlife.ID, "textures/entity/squirrel.png");
    
    public RenderSquirrel(RenderManager manager)
    {
        super(manager, new ModelSquirrel(), 0.6f);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(MobSquirrel entity)
    {
        return TEXTURE;
    }
}
