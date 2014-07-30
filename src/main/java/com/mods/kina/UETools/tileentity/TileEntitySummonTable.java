package com.mods.kina.UETools.tileentity;

import com.mods.kina.UETools.block.BlockSummonTable;
import com.mods.kina.UETools.entity.EntityRidden;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntitySummonTable extends TileEntity implements IInventory{
    private ItemStack itemStack;
    private boolean isSpawned;
    private EntityRidden entityRidden;
    private EntityVillager entityVillager;
    private Random random = new Random();

    public TileEntitySummonTable(EntityRidden entityRidden){
        entityRidden.setPosition(xCoord + 0.5, yCoord + 1, zCoord + 0.5);
        this.entityRidden = entityRidden;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("items", 10);
        for(int i = 0; i < nbttaglist.tagCount(); ++i){
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            itemStack = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
        isSpawned = par1NBTTagCompound.getBoolean("is_spawned");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        itemStack.writeToNBT(nbttagcompound1);
        nbttaglist.appendTag(nbttagcompound1);
        par1NBTTagCompound.setTag("items", nbttaglist);
        par1NBTTagCompound.setBoolean("is_spawned", isSpawned);
        System.out.println("Fine");
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void updateEntity(){
        //System.out.println(getStackInSlot(0)!=null&&getStackInSlot(0).getItem().equals(UEFieldsDeclaration.itemDeliveryPhone));
    }

    @Override
    public void markDirty(){
        super.markDirty();
        if(!worldObj.isRemote){
            if(getStackInSlot(0) != null && getStackInSlot(0).getItem().equals(UEFieldsDeclaration.itemDeliveryPhone) && !isSpawned){
                entityRidden = new EntityRidden(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5);
                entityVillager = new EntityVillager(worldObj, random.nextInt(5 + VillagerRegistry.getRegisteredVillagers().size()));
                entityVillager.setPosition(xCoord + 0.5, yCoord + 1, zCoord + 0.5);
                isSpawned = worldObj.spawnEntityInWorld(entityRidden) & worldObj.spawnEntityInWorld(entityVillager);
                System.out.println(isSpawned);
            }else if(isSpawned && getStackInSlot(0) == null){
                entityRidden.setDead();
                entityVillager.setDead();
                isSpawned = false;
            }
        }
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
