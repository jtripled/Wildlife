package com.jtripled.wildlife.mob.entry;

import com.jtripled.wildlife.mob.MobSquirrel;
import com.jtripled.wildlife.mob.render.RenderSquirrel;
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
public class MobEntrySquirrel extends MobEntry
{
    public MobEntrySquirrel()
    {
        super(MobSquirrel.class, MobSquirrel.NAME);
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
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
        return (types.contains(BiomeDictionary.Type.FOREST)
                || types.contains(BiomeDictionary.Type.CONIFEROUS))
                || biome.getBiomeName().equals("Birch Forest")
                || biome.getBiomeName().equals("Birch Forest Hills");
    }
        
    @Override
    public int getSpawnRate()
    {
        return 8;
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
