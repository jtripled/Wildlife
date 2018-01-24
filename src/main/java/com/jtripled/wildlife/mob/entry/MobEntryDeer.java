package com.jtripled.wildlife.mob.entry;

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
public class MobEntryDeer extends MobEntry
{
    public MobEntryDeer()
    {
        super(MobDeer.class, MobDeer.NAME);
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
        return 8;
    }
}
