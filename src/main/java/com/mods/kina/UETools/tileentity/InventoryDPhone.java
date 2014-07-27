package com.mods.kina.UETools.tileentity;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.ItemStack;

public class InventoryDPhone extends InventoryMerchant{
    public InventoryDPhone(EntityPlayer p_i1820_1_, IMerchant p_i1820_2_){
        super(p_i1820_1_,p_i1820_2_);
    }
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        return null;
    }
}
