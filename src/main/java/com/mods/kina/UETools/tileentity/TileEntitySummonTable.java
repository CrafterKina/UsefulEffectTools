package com.mods.kina.UETools.tileentity;

import com.mods.kina.UETools.block.BlockSummonTable;
import com.mods.kina.UETools.entity.EntityRidden;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;

import java.util.List;
import java.util.Random;

public class TileEntitySummonTable extends TileEntity implements IInventory{//TODO 細々したこと
    private ItemStack itemStack;
    public boolean isSpawned;
    private EntityRidden entityRidden;
    private EntityVillager entityVillager;
    private Random random = new Random();

    public TileEntitySummonTable(){
        //entityRidden= new EntityRidden(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5);
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readFromNBT(par1NBTTagCompound);
        /*try{*/
            itemStack = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("item"));
            //entityRidden = (EntityRidden) EntityList.createEntityFromNBT(par1NBTTagCompound.getCompoundTag("entity"), worldObj);
        /*}catch(NullPointerException e){
            itemStack=null;
            entityRidden=null;
        }*/
        isSpawned = par1NBTTagCompound.getBoolean("is_spawned");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
        super.writeToNBT(par1NBTTagCompound);
        NBTTagCompound itemTag=new NBTTagCompound();
        //NBTTagCompound entityTag=new NBTTagCompound();
        itemStack.writeToNBT(itemTag);
        //entityRidden.writeToNBT(entityTag);
        par1NBTTagCompound.setTag("item", itemTag);
        //par1NBTTagCompound.setTag("entity",entityTag);
        par1NBTTagCompound.setBoolean("is_spawned", isSpawned);
    }

    /*@Override
    public Packet getDescriptionPacket(){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        readFromNBT(pkt.func_148857_g());
    }*/

    @Override
    public void updateEntity(){

    }

    @Override
    public void markDirty(){
        super.markDirty();
        if(!worldObj.isRemote){
            if(getStackInSlot(0) != null && getStackInSlot(0).getItem().equals(UEFieldsDeclaration.itemDeliveryPhone)&&!isSpawned){
                if(entityRidden == null||entityRidden.isDead)
                    entityRidden = new EntityRidden(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5,this);
                entityVillager = new EntityVillager(worldObj, random.nextInt(5 + VillagerRegistry.getRegisteredVillagers().size()));
                entityVillager.setPosition(xCoord + 0.5, yCoord + 1, zCoord + 0.5);
                boolean unused=worldObj.spawnEntityInWorld(entityRidden)&worldObj.spawnEntityInWorld(entityVillager);
                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("召喚"));
                isSpawned=unused;
            }
            if(entityRidden == null||entityRidden.isDead){
                isSpawned=false;
            }
        }
    }

    public Entity findNearestEntityWithinAABB(Class p_72857_1_, AxisAlignedBB p_72857_2_){
        List list = worldObj.getEntitiesWithinAABB(p_72857_1_, p_72857_2_);
        Entity entity1 = null;
        double d0 = Double.MAX_VALUE;

        for(int i = 0; i < list.size(); ++i){
            Entity entity2 = (Entity) list.get(i);

            /*if (entity2 != p_72857_3_)
            {*/
            double d1 = getDistanceSqToEntity(entity2);

            if(d1 <= d0){
                entity1 = entity2;
                d0 = d1;
            }
            //}
        }

        return entity1;
    }

    public double getDistanceSqToEntity(Entity p_70068_1_){
        double d0 = this.xCoord - p_70068_1_.posX;
        double d1 = this.yCoord - p_70068_1_.posY;
        double d2 = this.zCoord - p_70068_1_.posZ;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    /**
     Returns the number of slots in the inventory.
     */

    @Override
    public int getSizeInventory(){
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int i){
        if(i < getSizeInventory()){
            return itemStack;
        }else return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j){
        if(itemStack != null){
            ItemStack itemstack;

            if(itemStack.stackSize <= j){
                itemstack = itemStack.copy();
                itemStack.stackSize = 0;
                itemStack = null;
                return itemstack;
            }else{
                itemstack = itemStack.splitStack(j);

                if(itemStack.stackSize == 0){
                    itemStack = null;
                }

                return itemstack;
            }
        }else{
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i){
        if(itemStack != null){
            ItemStack itemstack = itemStack;
            itemStack = null;
            return itemstack;
        }else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack){
        if(i < 5){
            itemStack = itemstack;

            if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
                itemstack.stackSize = getInventoryStackLimit();
            }
        }
    }

    /**
     Returns the name of the inventory
     */
    public String getInventoryName(){
        return new BlockSummonTable().getLocalizedName();
    }

    /**
     Returns if the inventory is named
     */
    public boolean hasCustomInventoryName(){
        return getInventoryName() != null;
    }

    @Override
    public int getInventoryStackLimit(){

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer){

        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && entityplayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory(){
    }

    public void closeInventory(){
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack){
        return false;
    }
}
