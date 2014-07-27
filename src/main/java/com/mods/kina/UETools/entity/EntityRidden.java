package com.mods.kina.UETools.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityRidden extends Entity{
    protected double zabutonX;
    protected double zabutonY;
    protected double zabutonZ;
    protected double zabutonYaw;
    protected double zabutonPitch;
    protected double velocityX;
    protected double velocityY;
    protected double velocityZ;
    protected int health;
    //public boolean isDispensed;

    protected int boatPosRotationIncrements;



    // Method
    public EntityRidden(World world) {
        super(world);
        preventEntitySpawning = true;
        setSize(0.81F, 0.2F);
        yOffset = 0F;
        health = 20;
        //isDispensed = false;
    }

    public EntityRidden(World world, double x, double y, double z) {
        this(world);
        setPositionAndRotation(x, y + (double)yOffset, z, 0F, 0F);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void entityInit() {
        //dataWatcher.addObject(17, (byte)(isDispensed ? 0x01 : 0x00));
        dataWatcher.addObject(18, Integer.valueOf(0).byteValue());
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity par1Entity) {
        return par1Entity.boundingBox;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        health = nbttagcompound.getShort("Health");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setShort("Health", (byte)health);
    }

    @Override
    public double getMountedYOffset() {
        if (riddenByEntity instanceof EntitySpider) {
            return (double)height * 0.0D - 0.1D;
        }
        if (	riddenByEntity instanceof EntityZombie ||
                riddenByEntity instanceof EntityEnderman) {
            return (double)height * 0.0D - 0.4D;
        }

        return (double)height * 0.0D + 0.1D;
    }


    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float pDammage) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !isDead;
    }

    @Override
    public void setPositionAndRotation2(double px, double py, double pz, float f, float f1, int i) {
        this.setPosition(px, py, pz);
        this.setRotation(f, f1);

        //super.setPositionAndRotation2(px, py, pz, f, f1, i);
        //		mod_VZN_zabuton.Debug("ID:%d - %f,  %f, %f", entityId, px, py, pz);
        //		mod_VZN_zabuton.Debug("ID:%d - %f,  %f, %f", entityId, posX, posY, posZ);
/*
//        this.setPosition(px, py, pz);
//        this.setRotation(f, f1);
		this.boatPosRotationIncrements = i + 5;


		this.zabutonX = px;
		this.zabutonY = py;
		this.zabutonZ = pz;
		this.zabutonYaw = (double)f;
		this.zabutonPitch = (double)f1;

//        motionX = velocityX;
//        motionY = velocityY;
//        motionZ = velocityZ;
*/
    }

    @Override
    public void setVelocity(double d, double d1, double d2) {
        velocityX = motionX = d;
        velocityY = motionY = d1;
        velocityZ = motionZ = d2;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        // ボートの判定のコピー
        // ボートは直接サーバーと位置情報を同期させているわけではなく、予測位置計算系に値を渡している。
        // 因みにボートの座標同期間隔は結構長めなので動きが変。


        double var6;
        double var8;
        double var12;
        double var26;
        double var24 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

        if (this.worldObj.isRemote) {
            // Client
            if (this.boatPosRotationIncrements > 0) {
                var6 = this.posX + (this.zabutonX - this.posX) / (double)this.boatPosRotationIncrements;
                var8 = this.posY + (this.zabutonY - this.posY) / (double)this.boatPosRotationIncrements;
                var26 = this.posZ + (this.zabutonZ - this.posZ) / (double)this.boatPosRotationIncrements;
                var12 = MathHelper.wrapAngleTo180_double(this.zabutonYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + var12 / (double)this.boatPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.zabutonPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(var6, var8, var26);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            } else {
                motionY -= 0.08D;
                if (this.onGround) {
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                }
                this.moveEntity(this.motionX, this.motionY, this.motionZ);

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        } else {
            // Server
            // 落下
            motionY -= 0.08D;

            // 搭乗者によるベクトル操作
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer) {
                this.motionX += this.riddenByEntity.motionX * 0.2D;
                this.motionZ += this.riddenByEntity.motionZ * 0.2D;
            }

            // 最高速度判定
            Double lmaxspeed = 0.35D;
            var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            if (var6 > lmaxspeed) {
                var8 = lmaxspeed / var6;
                this.motionX *= var8;
                this.motionZ *= var8;
                var6 = lmaxspeed;
            }
            if (this.onGround) {
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
                // setVelocityの呼ばれる回数が少なくて変な動きをするので対策
                //                this.velocityChanged = true;
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            this.motionX *= 0.9900000095367432D;
            this.motionY *= 0.949999988079071D;
            this.motionZ *= 0.9900000095367432D;

            // ヘッディング
            this.rotationPitch = 0.0F;
            var8 = (double)this.rotationYaw;
            var26 = this.prevPosX - this.posX;
            var12 = this.prevPosZ - this.posZ;

            if (var26 * var26 + var12 * var12 > 0.001D) {
                var8 = (double)((float)(Math.atan2(var12, var26) * 180.0D / Math.PI));
            }

            double var14 = MathHelper.wrapAngleTo180_double(var8 - (double) this.rotationYaw);
            if (var14 > 20.0D) {
                var14 = 20.0D;
            }
            if (var14 < -20.0D) {
                var14 = -20.0D;
            }

            this.rotationYaw = (float)((double)this.rotationYaw + var14);
            this.setRotation(this.rotationYaw, this.rotationPitch);

            // 当たり判定
            List var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.17D, 0.0D, 0.17D));
            if (var16 != null && !var16.isEmpty()) {

                for(Object aVar16 : var16){
                    Entity var18 = (Entity) aVar16;

                    if(var18 != this.riddenByEntity && var18.canBePushed() && var18 instanceof EntityRidden){
                        var18.applyEntityCollision(this);
                    }
                }
            }
        }
        if (this.riddenByEntity != null) {
            if (this.riddenByEntity instanceof EntityMob) {
                // 座ってる間は消滅させない
                ((EntityLiving)riddenByEntity).func_110163_bv();
            }
            if (riddenByEntity.isDead) {
                // 着座対象が死んだら無人化
                riddenByEntity = null;
                setRiddenByEntityID(null);
            }
        }
    }

    @Override
    public void applyEntityCollision(Entity entity) {
        // 吸着判定
        if (worldObj.isRemote) {
            return;
        }
        if (entity == riddenByEntity) {
            return;
        }
        if ((entity instanceof EntityLivingBase) && !(entity instanceof EntityPlayer) && riddenByEntity == null && entity.ridingEntity == null) {
            entity.mountEntity(this);
            setRiddenByEntityID(riddenByEntity);
        }
        //super.applyEntityCollision(entity);
    }

    /**
     Will get destroyed next tick.
     */
    public void setDead(){
        super.setDead();
        if(riddenByEntity!=null)riddenByEntity.setDead();
    }

    // クライアント側補正用
    public int getRiddenByEntityID() {
        return dataWatcher.getWatchableObjectInt(18);
    }

    public Entity getRiddenByEntity() {
        return worldObj.getEntityByID(getRiddenByEntityID());
    }

    public void setRiddenByEntityID(Entity pentity) {
        dataWatcher.updateObject(18, pentity == null ? 0 : pentity.getEntityId());
    }
}
