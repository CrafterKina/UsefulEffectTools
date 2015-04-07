package com.mods.kina.UETools.items.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMycelPart extends Item{
    public ItemMycelPart(){
        super();
        setTextureName("kina:mycelium_top");
        setUnlocalizedName("itemMycelPart");
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        Block block = world.getBlock(x, y, z);
        if(!player.capabilities.isCreativeMode){
            --item.stackSize;
        }
        if(block == Blocks.farmland){
            world.setBlock(x, y, z, Blocks.mycelium);
            return true;
        }else{
            return false;
        }
    }
}
