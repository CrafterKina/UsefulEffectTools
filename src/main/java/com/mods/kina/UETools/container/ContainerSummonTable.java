package com.mods.kina.UETools.container;

import com.mods.kina.UETools.tileentity.TileEntitySummonTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSummonTable extends Container{
    private static TileEntitySummonTable Table;

    public ContainerSummonTable(IInventory inventory, TileEntitySummonTable summonTable){
        Table = summonTable;
        int i;
        int j;

        addSlotToContainer(new Slot(summonTable, 0, 80, 35));


        for(i = 0; i < 3; ++i){
            for(j = 0; j < 9; ++j){
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i){
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer var1){
        return Table.isUseableByPlayer(var1);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if(slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(par2 < 9){
                if(!this.mergeItemStack(itemstack1, 9, 45, true)){
                    return null;
                }
            }else if(!this.mergeItemStack(itemstack1, 0, 9, false)){
                return null;
            }

            if(itemstack1.stackSize == 0){
                slot.putStack(null);
            }else{
                slot.onSlotChanged();
            }

            if(itemstack1.stackSize == itemstack.stackSize){
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

    public static TileEntitySummonTable getTable(){
        return Table;
    }
}
