package com.mods.kina.UETools.tools;

import com.mods.kina.KinaCore.misclib.KinaLib;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemInfinityCobblePickaxe extends ItemPickaxe{
    public ItemInfinityCobblePickaxe(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemI12ePickaxe");
        setTextureName("kina:infinitycobble_pickaxe");
        setCreativeTab(CreativeTabs.tabTools);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
        KinaLib.setOnBlockVanilla(par4,par5,par6,Blocks.cobblestone,par3World,par2EntityPlayer,par1ItemStack,par7,par8,par9,par10);
        par1ItemStack.damageItem(1, par2EntityPlayer);
        return true;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
        ItemStack itemstack1 = new ItemStack(Blocks.cobblestone);
        if(entityplayer.isSneaking()){
            entityplayer.inventory.addItemStackToInventory(itemstack1);
            itemstack.damageItem(1, entityplayer);
        }else{
            itemstack.damageItem(-1, entityplayer);
        }


        return itemstack;
    }
}