package com.jtripled.wildlife.mob.registration;

import com.jtripled.voxen.entity.IMobRegistration;
import com.jtripled.wildlife.mob.MobButterfly;
import com.jtripled.wildlife.mob.render.RenderButterfly;
import java.util.Set;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 *
 * @author jtripled
 */
public class MobRegistrationButterfly implements IMobRegistration
{
    @Override
    public String getName()
    {
        return MobButterfly.NAME;
    }

    @Override
    public Class<? extends EntityLiving> getEntityClass()
    {
        return MobButterfly.class;
    }

    @Override
    public IRenderFactory getRenderFactory()
    {
        return RenderButterfly::new;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return MobButterfly.RESOURCE;
    }
    
    @Override
    public int getEggPrimary()
    {
        return MobButterfly.EGG_PRIMARY;
    }
    
    @Override
    public int getEggSecondary()
    {
        return MobButterfly.EGG_SECONDARY;
    }
    
    @Override
    public boolean canSpawn(Biome biome)
    {
        return MobButterfly.SPAWN_PREDICATE.test(biome);
    }
        
    @Override
    public int getSpawnRate()
    {
        return MobButterfly.SPAWN_RATE;
    }

    @Override
    public int getSpawnMin()
    {
        return MobButterfly.SPAWN_MAX;
    }

    @Override
    public int getSpawnMax()
    {
        return MobButterfly.SPAWN_MIN;
    }
}
