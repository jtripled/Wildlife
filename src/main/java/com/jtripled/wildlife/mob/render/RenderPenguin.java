package com.jtripled.wildlife.mob.render;

import com.jtripled.wildlife.Wildlife;
import com.jtripled.wildlife.mob.MobPenguin;
import com.jtripled.wildlife.mob.model.ModelPenguin;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author jtripled
 */
public class RenderPenguin extends RenderLiving<MobPenguin>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(Wildlife.ID, "textures/entity/penguin.png");
    
    public RenderPenguin(RenderManager manager)
    {
        super(manager, new ModelPenguin(), 0.5f);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(MobPenguin entity)
    {
        return TEXTURE;
    }
}
