package com.jtripled.wildlife.mob;

import com.jtripled.wildlife.Wildlife;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/**
 *
 * @author jtripled
 */
public class MobSquirrel extends EntityAnimal
{
    public static final String NAME = "squirrel";
    public static final ResourceLocation RESOURCE = new ResourceLocation(Wildlife.ID, NAME);
    public static final int EGG_PRIMARY = 0x525236;
    public static final int EGG_SECONDARY = 0xAEAEA3;
    public static final int SPAWN_RATE = 10;
    public static final int SPAWN_MIN = 2;
    public static final int SPAWN_MAX = 3;
    
    public static final SoundEvent AMBIENT_SOUND = new SoundEvent(new ResourceLocation(Wildlife.ID, "squirrel.ambient")).setRegistryName(new ResourceLocation(Wildlife.ID, "squirrel.ambient"));
    public static final SoundEvent DEATH_SOUND = new SoundEvent(new ResourceLocation(Wildlife.ID, "squirrel.death")).setRegistryName(new ResourceLocation(Wildlife.ID, "squirrel.death"));
    public static final SoundEvent HURT_SOUND = new SoundEvent(new ResourceLocation(Wildlife.ID, "squirrel.hurt")).setRegistryName(new ResourceLocation(Wildlife.ID, "squirrel.hurt"));
    
    public MobSquirrel(World world)
    {
        super(world);
        this.setSize(0.3F, 0.7F);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.38F));
        this.tasks.addTask(2, new EntityAITempt(this, 1.0F, Items.WHEAT_SEEDS, true));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 2.0F, 0.8F, 1.4F));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWander(this, 1.25F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    @Override
    public float getSoundVolume()
    {
        return 0.5f;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0d);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25d);
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
        return AMBIENT_SOUND;
    }
    
    @Override
    protected SoundEvent getDeathSound()
    {
        return DEATH_SOUND;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return HURT_SOUND;
    }

    @Override
    public float getRenderSizeModifier()
    {
        return 0.3f;
    }

    @Override
    public float getEyeHeight()
    {
        return this.height;
    }

    @Override
    public MobSquirrel createChild(EntityAgeable entity)
    {
        return new MobSquirrel(world);
    }
}
