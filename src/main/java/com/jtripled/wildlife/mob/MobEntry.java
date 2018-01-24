package com.jtripled.wildlife.mob;

import com.google.common.collect.Lists;
import com.jtripled.wildlife.Wildlife;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 *
 * @author jtripled
 */
public abstract class MobEntry extends EntityEntry
{
    public MobEntry(Class<? extends Entity> entityClass, String name)
    {
        super(entityClass, name);
        this.setRegistryName(getResourceLocation());
        if (getEggPrimary() != -1 && getEggSecondary() != -1)
            this.setEgg(new EntityEggInfo(getResourceLocation(), getEggPrimary(), getEggSecondary()));
    }
        
    @Override
    public abstract Class<? extends EntityLiving> getEntityClass();

    public abstract IRenderFactory getRenderFactory();

    public abstract ResourceLocation getResourceLocation();
        
    public int getEggPrimary()
    {
        return -1;
    }

    public int getEggSecondary()
    {
        return -1;
    }
    
    public boolean canSpawn(Biome biome)
    {
        return false;
    }

    public int getSpawnRate()
    {
        return 4;
    }

    public int getSpawnMin()
    {
        return 2;
    }

    public int getSpawnMax()
    {
        return 3;
    }
        
    public EnumCreatureType getSpawnType()
    {
        return EnumCreatureType.CREATURE;
    }
    
    public void registerSpawn()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY)
            if (canSpawn(biome))
                biomes.add(biome);
        if (!biomes.isEmpty())
            EntityRegistry.addSpawn(getEntityClass(), getSpawnRate(), getSpawnMin(), getSpawnMax(), getSpawnType(), biomes.toArray(new Biome[biomes.size()]));
    }
    
    public void registerRenderer()
    {
        Wildlife.PROXY.registerEntityRenderer(getEntityClass(), getRenderFactory());
    }
}
