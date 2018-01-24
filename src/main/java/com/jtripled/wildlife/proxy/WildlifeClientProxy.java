package com.jtripled.wildlife.proxy;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 *
 * @author jtripled
 */
public class WildlifeClientProxy extends WildlifeCommonProxy
{
    @Override
    public void registerEntityRenderer(Class<? extends Entity> entityClass, IRenderFactory renderFactory)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}
