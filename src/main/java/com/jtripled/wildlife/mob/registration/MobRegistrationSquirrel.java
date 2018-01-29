package com.jtripled.wildlife.mob.registration;

import com.jtripled.voxen.entity.IMobRegistration;
import com.jtripled.wildlife.mob.MobSquirrel;
import com.jtripled.wildlife.mob.render.RenderSquirrel;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 *
 * @author jtripled
 */
public class MobRegistrationSquirrel implements IMobRegistration
{
    @Override
    public String getName()
    {
        return MobSquirrel.NAME;
    }

    @Override
    public Class<? extends EntityLiving> getEntityClass()
    {
        return MobSquirrel.class;
    }

    @Override
    public IRenderFactory getRenderFactory()
    {
        return RenderSquirrel::new;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return MobSquirrel.RESOURCE;
    }
    
    @Override
    public int getEggPrimary()
    {
        return MobSquirrel.EGG_PRIMARY;
    }
    
    @Override
    public int getEggSecondary()
    {
        return MobSquirrel.EGG_SECONDARY;
    }
    
    @Override
    public boolean canSpawn(Biome biome)
    {
        return MobSquirrel.SPAWN_PREDICATE.test(biome);
    }
        
    @Override
    public int getSpawnRate()
    {
        return MobSquirrel.SPAWN_RATE;
    }

    @Override
    public int getSpawnMin()
    {
        return MobSquirrel.SPAWN_MIN;
    }

    @Override
    public int getSpawnMax()
    {
        return MobSquirrel.SPAWN_MAX;
    }
    
    @Override
    public SoundEvent[] getSounds()
    {
        return new SoundEvent[]{
            MobSquirrel.AMBIENT_SOUND,
            MobSquirrel.DEATH_SOUND,
            MobSquirrel.HURT_SOUND
        };
    }
}
