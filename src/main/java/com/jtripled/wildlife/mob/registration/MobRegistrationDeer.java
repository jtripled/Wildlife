package com.jtripled.wildlife.mob.registration;

import com.jtripled.voxen.entity.IMobRegistration;
import com.jtripled.wildlife.mob.MobDeer;
import com.jtripled.wildlife.mob.render.RenderDeer;
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
public class MobRegistrationDeer implements IMobRegistration
{
    @Override
    public String getName()
    {
        return MobDeer.NAME;
    }

    @Override
    public Class<? extends EntityLiving> getEntityClass()
    {
        return MobDeer.class;
    }

    @Override
    public IRenderFactory getRenderFactory()
    {
        return RenderDeer::new;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return MobDeer.RESOURCE;
    }
    
    @Override
    public int getEggPrimary()
    {
        return MobDeer.EGG_PRIMARY;
    }
    
    @Override
    public int getEggSecondary()
    {
        return MobDeer.EGG_SECONDARY;
    }
    
    @Override
    public boolean canSpawn(Biome biome)
    {
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
        return (types.contains(BiomeDictionary.Type.FOREST)
                || types.contains(BiomeDictionary.Type.CONIFEROUS))
                || biome.getBiomeName().equals("Birch Forest")
                || biome.getBiomeName().equals("Birch Forest Hills");
    }
    
    @Override
    public int getSpawnRate()
    {
        return MobDeer.SPAWN_RATE;
    }
    
    @Override
    public int getSpawnMin()
    {
        return MobDeer.SPAWN_MIN;
    }
    
    @Override
    public int getSpawnMax()
    {
        return MobDeer.SPAWN_MAX;
    }
}
