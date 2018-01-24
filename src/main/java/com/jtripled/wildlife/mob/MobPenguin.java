package com.jtripled.wildlife.mob;

import com.google.common.collect.Sets;
import com.jtripled.wildlife.Wildlife;
import java.util.Set;
import javax.annotation.Nonnull;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 *
 * @author jtripled
 */
public class MobPenguin extends EntityAnimal
{
    public static final String NAME = "penguin";
    public static final ResourceLocation RESOURCE = new ResourceLocation(Wildlife.ID, NAME);
    public static final int EGG_PRIMARY = 0x000000;
    public static final int EGG_SECONDARY = 0xFFFFFF;
    
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.COD.getMetadata()).getItem(), new ItemStack(Items.FISH, 1, ItemFishFood.FishType.SALMON.getMetadata()).getItem());
    public short rotationFlipper;
    private boolean moveFlipper = false;

    public MobPenguin(World world)
    {
        super(world);
        this.setSize(0.4F, 0.95F);
    }

    @Override
    protected void initEntityAI()
    {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.5D));
        tasks.addTask(2, new EntityAIMate(this, 0.8D));
        tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityPolarBear.class, 6.0F, 1.0D, 1.2D));
        tasks.addTask(4, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));
        tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(6, new EntityAIWander(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAIWatchClosest(this, MobPenguin.class, 6.0F));
        tasks.addTask(9, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.16D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (world.isRemote) {
            if (this.posZ != this.prevPosZ) {
                if (moveFlipper) {
                    rotationFlipper++;
                }
            }
        }
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 1;
    }

    @Override
    public boolean isBreedingItem(@Nonnull ItemStack stack)
    {
        return !stack.isEmpty() && TEMPTATION_ITEMS.contains(stack.getItem());
    }

    @Override
    public float getEyeHeight()
    {
        return this.isChild() ? 0.5F : 0.9F;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return new MobPenguin(world);
    }
}
