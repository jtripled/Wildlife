package com.jtripled.wildlife.mob;

import com.jtripled.wildlife.Wildlife;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

/**
 *
 * @author jtripled
 */
public class MobDeer extends EntityAnimal
{
    public static final String NAME = "deer";
    public static final ResourceLocation RESOURCE = new ResourceLocation(Wildlife.ID, NAME);
    public static final int EGG_PRIMARY = 0x92923B;
    public static final int EGG_SECONDARY = 0xFFFFE2;
    public static final int SPAWN_RATE = 12;
    public static final int SPAWN_MIN = 4;
    public static final int SPAWN_MAX = 4;
    public static final Predicate<Biome> SPAWN_PREDICATE = (Biome biome) -> {
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
        return (types.contains(BiomeDictionary.Type.FOREST)
                || types.contains(BiomeDictionary.Type.CONIFEROUS))
                || biome.getBiomeName().equals("Birch Forest")
                || biome.getBiomeName().equals("Birch Forest Hills");
    };
    
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(Wildlife.ID, "entity/deer");
    
    public MobDeer(World world)
    {
        super(world);
        this.setSize(0.9F, 1.7F);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.5d);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2d);
    }

    @Override
    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.5F;
    }

    @Override
    public MobDeer createChild(EntityAgeable entity)
    {
        return new MobDeer(world);
    }
    
    @Override
    public int getExperiencePoints(EntityPlayer player)
    {
        return this.rand.nextInt(3) + 1;
    }
    
    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return LOOT_TABLE;
    }
}
