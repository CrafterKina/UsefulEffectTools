package com.mods.kina.UETools.items.misc;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTileWatcher extends Item{
    /*public static int sX;
    public static int sY;
    public static int sZ;*/

    public ItemTileWatcher(){
        setUnlocalizedName("itemTileWatcher");
        setTextureName("kina:tile_watcher");
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){

        //TileEntityEnderChest tileentityenderchest = (TileEntityEnderChest)world.getTileEntity(sX, sY, sZ);

        //if (inventoryenderchest != null && tileentityenderchest != null)
        //{
            /*if (world.getBlock(sX, sY + 1, sZ).isNormalCube())
            {
                return itemstack;
            }*/
        if(world.isRemote || entityplayer.getInventoryEnderChest() == null){
            return itemstack;
        }else{
            //inventoryenderchest.func_146031_a(tileentityenderchest);
            InventoryEnderChest chest = entityplayer.getInventoryEnderChest();
            entityplayer.displayGUIChest(chest);
            return itemstack;
        }
        /*}
        else
        {
            return itemstack;
        }*/
    }

    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        /*if(world.isRemote){
            return true;
        }else{
            if(player.isSneaking()){
                Block gB = world.getBlock(x, y, z);
                if(gB == Blocks.ender_chest){
                    *//*InventoryEnderChest*//* chest = player.getInventoryEnderChest();
                    return true;
                }*//*else if(gB==Blocks.chest){
                    chest=(IInventory)world.getTileEntity(x,y,z);
                    return true;
                }*//*
                else {
                    onItemRightClick(item, world, player);
                    return true;
                }

            }else {
                onItemRightClick(item, world, player);
                return true;
            }
        }*/
        return false;
    }
}
