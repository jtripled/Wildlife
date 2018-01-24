package com.jtripled.wildlife.mob.entry;

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
public class MobEntryButterfly extends MobEntry
{
    public MobEntryButterfly()
    {
        super(MobButterfly.class, MobButterfly.NAME);
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
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
        return (((types.contains(BiomeDictionary.Type.FOREST)
                || types.contains(BiomeDictionary.Type.PLAINS)))
                && !types.contains(BiomeDictionary.Type.COLD)
                && !types.contains(BiomeDictionary.Type.SNOWY))
                || biome.getBiomeName().equals("Birch Forest")
                || biome.getBiomeName().equals("Birch Forest Hills");
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
        return 5;
    }
}
