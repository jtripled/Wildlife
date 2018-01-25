package com.jtripled.wildlife.mob.registration;

import com.jtripled.voxen.entity.IMobRegistration;
import com.jtripled.wildlife.mob.MobBird;
import com.jtripled.wildlife.mob.render.RenderBird;
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
public class MobRegistrationBird implements IMobRegistration
{
    @Override
    public String getName()
    {
        return MobBird.NAME;
    }

    @Override
    public Class<? extends EntityLiving> getEntityClass()
    {
        return MobBird.class;
    }

    @Override
    public IRenderFactory getRenderFactory()
    {
        return RenderBird::new;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return MobBird.RESOURCE;
    }
    
    @Override
    public int getEggPrimary()
    {
        return MobBird.EGG_PRIMARY;
    }
    
    @Override
    public int getEggSecondary()
    {
        return MobBird.EGG_SECONDARY;
    }
    
    @Override
    public boolean canSpawn(Biome biome)
    {
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
        return types.contains(BiomeDictionary.Type.FOREST);
    }
        
    @Override
    public int getSpawnRate()
    {
        return MobBird.SPAWN_RATE;
    }

    @Override
    public int getSpawnMin()
    {
        return MobBird.SPAWN_MIN;
    }

    @Override
    public int getSpawnMax()
    {
        return MobBird.SPAWN_MAX;
    }
    
    @Override
    public SoundEvent[] getSounds()
    {
        return new SoundEvent[]{
            MobBird.AMBIENT_SOUND,
            MobBird.DEATH_SOUND,
            MobBird.HURT_SOUND
        };
    }
}
