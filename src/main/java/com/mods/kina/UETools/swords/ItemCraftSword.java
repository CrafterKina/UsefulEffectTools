package com.mods.kina.UETools.swords;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemCraftSword extends Item{
    Minecraft mc=Minecraft.getMinecraft();
    public ItemCraftSword(){
        super();
        setUnlocalizedName("KINADEBUG");
        setCreativeTab(CreativeTabs.tabCombat);
    }

    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        return false;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
        entity.setDead();
        return true;
    }
}