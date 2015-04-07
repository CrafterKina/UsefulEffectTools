package com.mods.kina.UETools.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCreeperPickaxe extends ItemPickaxe{
    public ItemCreeperPickaxe(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemCreeperPickaxe");
        setTextureName("kina:creeper_pickaxe");
        setCreativeTab(CreativeTabs.tabTools);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ){
        boolean used = false;
        int hotbarSlot = player.inventory.currentItem;
        int itemSlot = hotbarSlot == 0 ? 8 : hotbarSlot + 1;
        ItemStack nearbyStack = null;

        if(hotbarSlot < 8){
            nearbyStack = player.inventory.getStackInSlot(itemSlot);
            if(nearbyStack != null){
                Item item = nearbyStack.getItem();
                if(item instanceof ItemBlock){
                    int posX = x;
                    int posY = y;
                    int posZ = z;
                    int playerPosX = (int) Math.floor(player.posX);
                    int playerPosY = (int) Math.floor(player.posY);
                    int playerPosZ = (int) Math.floor(player.posZ);
                    if(side == 0){
                        --posY;
                    }

                    if(side == 1){
                        ++posY;
                    }

                    if(side == 2){
                        --posZ;
                    }

                    if(side == 3){
                        ++posZ;
                    }

                    if(side == 4){
                        --posX;
                    }

                    if(side == 5){
                        ++posX;
                    }
                    if(posX == playerPosX && (posY == playerPosY || posY == playerPosY + 1 || posY == playerPosY - 1) && posZ == playerPosZ){
                        return false;
                    }

                    used = item.onItemUse(nearbyStack, player, world, x, y, z, side, clickX, clickY, clickZ);
                    if(nearbyStack.stackSize < 1){
                        nearbyStack = null;
                        player.inventory.setInventorySlotContents(itemSlot, null);
                    }
                }
            }
        }
        return used;
    }
}