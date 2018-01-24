package com.jtripled.wildlife.mob;

import com.jtripled.wildlife.Wildlife;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author jtripled
 */
public final class MobFirefly extends EntityFlying implements IMob
{
    public static final String NAME = "firefly";
    public static final ResourceLocation RESOURCE = new ResourceLocation(Wildlife.ID, NAME);
    public static final int EGG_PRIMARY = 0x92923B;
    public static final int EGG_SECONDARY = 0xFFFFE2;

    public MobFirefly(World world)
    {
        super(world);
        this.setSize(0.7F, 0.7F);
        this.moveHelper = new MobFirefly.ButterflyMoveHelper();
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(3, new MobFirefly.AIButterflyRandomFly());
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.5D);
    }
    
    @Override
    public boolean canBePushed()
    {
        return false;
    }
    
    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }
    
    @Override
    protected void collideWithEntity(Entity entity)
    {
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    public float getGlowBrightness()
    {
        return this.world.getWorldTime() < 12000 ? 0 : (float) Math.sin(this.ticksExisted / 7.0) + 1F;
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
    	BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (pos.getY() <= this.world.getSeaLevel() || pos.getY() > 100)
        {
            return false;
        }
        else
        {
            return this.world.getWorldTime() > 10000 && this.world.getWorldTime() < 22000 && super.getCanSpawnHere();
        }
    }
    
    // Helper class representing a point in space that the butterfly is targeting for some reason
    class ButterflyMoveTargetPos
    {
        private MobFirefly butterfly = MobFirefly.this;

        public double posX;
        public double posY;
        public double posZ;
        public double distX;
        public double distY;
        public double distZ;
        public double dist;
        public double aimX;
        public double aimY;
        public double aimZ;
        
        public ButterflyMoveTargetPos()
        {
            this(0, 0, 0);
        }
        
        public ButterflyMoveTargetPos(double posX, double posY, double posZ)
        {
            this.setTarget(posX, posY, posZ);
        }
        
        public void setTarget(double posX, double posY, double posZ)
        {
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.refresh();
        }
        
        public void refresh()
        {
            this.distX = this.posX - this.butterfly.posX;
            this.distY = this.posY - this.butterfly.posY;
            this.distZ = this.posZ - this.butterfly.posZ;
            
            this.dist = (double)MathHelper.sqrt(this.distX * this.distX + this.distY * this.distY + this.distZ * this.distZ);
            
            // (aimX,aimY,aimZ) is a unit vector in the direction we want to go
            if (this.dist == 0.0D)
            {
                this.aimX = 0.0D;
                this.aimY = 0.0D;
                this.aimZ = 0.0D;
            }
            else
            {
                this.aimX = this.distX / this.dist;
                this.aimY = this.distY / this.dist;
                this.aimZ = this.distZ / this.dist;                
            }
         }
        
        public boolean isBoxBlocked(AxisAlignedBB box)
        {
            return !this.butterfly.world.getCollisionBoxes(this.butterfly, box).isEmpty();
        }
        
        // check nothing will collide with the butterfly in the direction of aim, for howFar units (or until the destination - whichever is closer)
        public boolean isPathClear(double howFar)
        {
            howFar = Math.min(howFar, this.dist);
            AxisAlignedBB box = this.butterfly.getEntityBoundingBox();
            for (double i = 0.5D; i < howFar; ++i)
            {
                // check there's nothing in the way
                if (this.isBoxBlocked(box.offset(this.aimX * i, this.aimY * i, this.aimZ * i)))
                {
                    return false;
                }
            }
            if (this.isBoxBlocked(box.offset(this.aimX * howFar, this.aimY * howFar, this.aimZ * howFar)))
            {
                return false;
            }
            return true;
        }
        
    }
            
    class ButterflyMoveHelper extends EntityMoveHelper
    {
        // EntityMoveHelper has the boolean 'update' which is set to true when the target is changed, and set to false when a bearing is set
        // So it means 'the target has changed but we're not yet heading for it'
        // We'll re-use it here with a slightly different interpretation
        // Here it will mean 'has a target and not yet arrived'
        
        private MobFirefly butterfly = MobFirefly.this;
        private int courseChangeCooldown = 0;
        private double closeEnough = 0.3D;
        private ButterflyMoveTargetPos targetPos = new ButterflyMoveTargetPos();

        public ButterflyMoveHelper()
        {
            super(MobFirefly.this);
        }
                        
        @Override
        public void setMoveTo(double x, double y, double z, double speedIn)
        {
            super.setMoveTo(x,y,z,speedIn);
            this.targetPos.setTarget(x, y, z);
        }

        @Override
        public void onUpdateMoveHelper()
        {
            // if we have arrived at the previous target, or we have no target to aim for, do nothing
            if (this.action != EntityMoveHelper.Action.MOVE_TO) {return;}
            
            if (this.courseChangeCooldown-- > 0) {
                // limit the rate at which we change course
                return;
            }
            this.courseChangeCooldown += this.butterfly.getRNG().nextInt(2) + 2;
            
            // update the target position
            this.targetPos.refresh();
            
            // accelerate the butterfly towards the target
            double acceleration = 0.1D;
            this.butterfly.motionX += this.targetPos.aimX * acceleration;
            this.butterfly.motionY += this.targetPos.aimY * acceleration;
            this.butterfly.motionZ += this.targetPos.aimZ * acceleration;
           
            // rotate to point at target
            this.butterfly.renderYawOffset = this.butterfly.rotationYaw = -((float)Math.atan2(this.targetPos.distX, this.targetPos.distZ)) * 180.0F / (float)Math.PI;            

            // abandon this movement if we have reached the target or there is no longer a clear path to the target
            if (!this.targetPos.isPathClear(5.0D))
            {
                //System.out.println("Abandoning move target - way is blocked" );
                this.action = EntityMoveHelper.Action.WAIT;
            } else if (this.targetPos.dist < this.closeEnough) {
                //System.out.println("Arrived (close enough) dist:"+this.targetPos.dist);
                this.action = EntityMoveHelper.Action.WAIT;
            }
        }        

    }
    
    // AI class for implementing the random flying behaviour
    class AIButterflyRandomFly extends EntityAIBase
    {
        private MobFirefly butterfly = MobFirefly.this;
        private ButterflyMoveTargetPos targetPos = new ButterflyMoveTargetPos();
        
        public AIButterflyRandomFly()
        {
            this.setMutexBits(1);
        }

        // should we choose a new random destination for the butterfly to fly to?
        // yes, if the butterfly doesn't already have a destination
        @Override
        public boolean shouldExecute()
        {
            return !this.butterfly.getMoveHelper().isUpdating();
        }
        
        @Override
        public boolean shouldContinueExecuting() {return false;}
        
        // choose a a new random destination for the butterfly to fly to
        @Override
        public void startExecuting()
        {            
            Random rand = this.butterfly.getRNG();
            // pick a random nearby point and see if we can fly to it
            if (this.tryGoingRandomDirection(rand, 1.0D)) {return;}
            // pick a random closer point to fly to instead
            if (this.tryGoingRandomDirection(rand, 1.0D)) {return;}
            // try going straight along axes (try all 6 directions in random order)
            List<EnumFacing> directions = Arrays.asList(EnumFacing.values());
            Collections.shuffle(directions);
            for (EnumFacing facing : directions)
            {
                if (this.tryGoingAlongAxis(rand, facing, 1.0D)) {return;}
            }
        }
        
        
        // note y direction has a slight downward bias to stop them flying too high
        public boolean tryGoingRandomDirection(Random rand, double maxDistance)
        {
            double dirX = ((rand.nextDouble() * 2.0D - 1.0D) * maxDistance);
            double dirY = ((rand.nextDouble() * 2.0D - 1.1D) * maxDistance);
            double dirZ = ((rand.nextDouble() * 2.0D - 1.0D) * maxDistance);
            return this.tryGoing(dirX, dirY, dirZ);
        }
        
        public boolean tryGoingAlongAxis(Random rand, EnumFacing facing, double maxDistance)
        {
            double dirX = 0.0D;
            double dirY = 0.0D;
            double dirZ = 0.0D;
            switch (facing.getAxis())
            {
                case X:
                    dirX = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                    break;
                case Y:
                    dirY = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                    break;
                case Z: default:
                    dirZ = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                    break;
            }
            return this.tryGoing(dirX, dirY, dirZ);
        }
        
        public boolean tryGoing(double dirX, double dirY, double dirZ)
        {
            //System.out.println("("+dirX+","+dirY+","+dirZ+")");
            this.targetPos.setTarget(this.butterfly.posX + dirX, this.butterfly.posY + dirY, this.butterfly.posZ + dirZ);
            //System.out.println("Testing random move target distance:"+this.targetPos.dist+" direction:("+this.targetPos.aimX+","+this.targetPos.aimY+","+this.targetPos.aimZ+")");
            boolean result = this.targetPos.isPathClear(5.0F);
            if (result)
            {
                this.butterfly.getMoveHelper().setMoveTo(this.targetPos.posX, this.targetPos.posY, this.targetPos.posZ, 1.0D);
            }
            return result;
        }
    }
}
