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
    private ItemStack[] itemStack=new ItemStack[1];
    private boolean isSpawned;
    private EntityRidden entityRidden;
    private EntityVillager entityVillager;
    private Random random = new Random();

    public TileEntitySummonTable(EntityRidden entityRidden){
        entityRidden.setPosition(xCoord+0.5,yCoord+1,zCoord+0.5);
        this.entityRidden=entityRidden;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        itemStack = new ItemStack[getSizeInventory()];

        for(int i = 0; i < nbttaglist.tagCount(); ++i){
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if(b0 >= 0 && b0 < itemStack.length){
                itemStack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        isSpawned=par1NBTTagCompound.getBoolean("isSpawned");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for(int i = 0; i < itemStack.length; ++i){
            if(itemStack[i] != null){
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                itemStack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
        par1NBTTagCompound.setBoolean("isSpawned",isSpawned);
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
        if(!worldObj.isRemote){
            if(getStackInSlot(0) != null && getStackInSlot(0).getItem().equals(UEFieldsDeclaration.itemDeliveryPhone)&&!isSpawned){
                entityRidden = new EntityRidden(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5);
                entityVillager = new EntityVillager(worldObj, random.nextInt(5 + VillagerRegistry.getRegisteredVillagers().size()));
                entityVillager.setPosition(xCoord + 0.5, yCoord + 1, zCoord + 0.5);
                isSpawned = worldObj.spawnEntityInWorld(entityRidden) & worldObj.spawnEntityInWorld(entityVillager);
                System.out.println(isSpawned);
            }
            else if(isSpawned && getStackInSlot(0) == null){
                entityRidden.setDead();
                entityVillager.setDead();
                isSpawned=false;
            }
        }
    }

    /**
     Returns the number of slots in the inventory.
     */

    @Override
    public int getSizeInventory(){
        return itemStack.length;
    }

    @Override
    public ItemStack getStackInSlot(int i){
        if(i < getSizeInventory()){
            return itemStack[i];
        }else return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j){
        if(itemStack[i] != null){
            ItemStack itemstack;

            if(itemStack[i].stackSize <= j){
                itemstack = itemStack[i].copy();
                itemStack[i].stackSize = 0;
                itemStack[i] = null;
                return itemstack;
            }else{
                itemstack = itemStack[i].splitStack(j);

                if(itemStack[i].stackSize == 0){
                    itemStack[i] = null;
                }

                return itemstack;
            }
        }else{
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i){
        if(itemStack[i] != null){
            ItemStack itemstack = itemStack[i];
            itemStack[i] = null;
            return itemstack;
        }else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack){
        if(i < 5){
            itemStack[i] = itemstack;

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

    public void openInventory(){}

    public void closeInventory(){}

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack){
        return false;
    }
}
