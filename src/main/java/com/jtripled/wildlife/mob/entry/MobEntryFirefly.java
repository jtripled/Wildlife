package com.jtripled.wildlife.mob.entry;

import com.jtripled.wildlife.mob.MobFirefly;
import com.jtripled.wildlife.mob.render.RenderFirefly;
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
public class MobEntryFirefly extends MobEntry
{
    public MobEntryFirefly()
    {
        super(MobFirefly.class, MobFirefly.NAME);
    }

    @Override
    public Class<? extends EntityLiving> getEntityClass()
    {
        return MobFirefly.class;
    }

    @Override
    public IRenderFactory getRenderFactory()
    {
        return RenderFirefly::new;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return MobFirefly.RESOURCE;
    }
    
    @Override
    public int getEggPrimary()
    {
        return MobFirefly.EGG_PRIMARY;
    }
    
    @Override
    public int getEggSecondary()
    {
        return MobFirefly.EGG_SECONDARY;
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
        return 10;
    }

    @Override
    public int getSpawnMin()
    {
        return 1;
    }

    @Override
    public int getSpawnMax()
    {
        return 3;
    }
}
