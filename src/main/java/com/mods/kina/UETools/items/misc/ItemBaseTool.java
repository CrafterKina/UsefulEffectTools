package com.mods.kina.UETools.items.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBaseTool extends Item{
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack){
        return super.onEntitySwing(entityLiving, stack);
    }

    public boolean onEntityItemUpdate(EntityItem entityItem){
        return super.onEntityItemUpdate(entityItem);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
        return super.onLeftClickEntity(stack, player, entity);
    }

    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_){
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_){
        return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
    }

    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_){
        super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count){
        super.onUsingTick(stack, player, count);
    }

    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_){
        super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    }

    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player){
        return super.onDroppedByPlayer(item, player);
    }

    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_){
        return super.onEaten(p_77654_1_, p_77654_2_, p_77654_3_);
    }

    public void onCreated(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_){
        super.onCreated(p_77622_1_, p_77622_2_, p_77622_3_);
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}
