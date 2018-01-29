package com.jtripled.wildlife.mob.registration;

import com.jtripled.voxen.entity.IMobRegistration;
import com.jtripled.wildlife.mob.MobPenguin;
import com.jtripled.wildlife.mob.render.RenderPenguin;
import java.util.Set;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 *
 * @author jtripled
 */
public class MobRegistrationPenguin implements IMobRegistration
{
    @Override
    public String getName()
    {
        return MobPenguin.NAME;
    }

    @Override
    public Class<? extends EntityLiving> getEntityClass()
    {
        return MobPenguin.class;
    }

    @Override
    public IRenderFactory getRenderFactory()
    {
        return RenderPenguin::new;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return MobPenguin.RESOURCE;
    }
    
    @Override
    public int getEggPrimary()
    {
        return MobPenguin.EGG_PRIMARY;
    }
    
    @Override
    public int getEggSecondary()
    {
        return MobPenguin.EGG_SECONDARY;
    }
    
    @Override
    public boolean canSpawn(Biome biome)
    {
        return MobPenguin.SPAWN_PREDICATE.test(biome);
    }
        
    @Override
    public int getSpawnRate()
    {
        return MobPenguin.SPAWN_RATE;
    }

    @Override
    public int getSpawnMin()
    {
        return MobPenguin.SPAWN_MIN;
    }

    @Override
    public int getSpawnMax()
    {
        return MobPenguin.SPAWN_MAX;
    }
    
    @Override
    public SoundEvent[] getSounds()
    {
        return new SoundEvent[]{
            MobPenguin.AMBIENT_SOUND,
            MobPenguin.DEATH_SOUND,
            MobPenguin.HURT_SOUND
        };
    }
}
