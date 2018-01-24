package com.jtripled.wildlife.mob;

import com.jtripled.wildlife.mob.render.RenderPenguin;
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
public class MobEntryPenguin extends MobEntry
{
    public MobEntryPenguin()
    {
        super(MobPenguin.class, MobPenguin.NAME);
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
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
        return (types.contains(BiomeDictionary.Type.FOREST)
                || types.contains(BiomeDictionary.Type.CONIFEROUS));
    }
        
    @Override
    public int getSpawnRate()
    {
        return 5;
    }
}