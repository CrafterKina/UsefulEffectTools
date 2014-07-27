package com.mods.kina.UETools.swords;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemLavaSword extends ItemSword{
    public ItemLavaSword(Item.ToolMaterial material){
        super(material);
        setUnlocalizedName("itemLavaSword");
        setTextureName("kina:lava_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
        MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(par3World, par2EntityPlayer, true);
        int i = movingobjectposition.blockX;
        int j = movingobjectposition.blockY;
        int k = movingobjectposition.blockZ;
        Material material = par3World.getBlock(i, j, k).getMaterial();
        int l = par3World.getBlockMetadata(i, j, k);
        if(par7 == 0){
            --par5;
        }

        if(par7 == 1){
            ++par5;
        }

        if(par7 == 2){
            --par6;
        }

        if(par7 == 3){
            ++par6;
        }

        if(par7 == 4){
            --par4;
        }

        if(par7 == 5){
            ++par4;
        }

        if(!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)){
            return false;
        }else{
            if(par3World.isAirBlock(par4, par5, par6)){
                par3World.setBlock(par4, par5, par6, Blocks.flowing_lava);
                par1ItemStack.damageItem(2, par2EntityPlayer);
            }
            if(material == Material.lava && l == 0){
                par3World.setBlockToAir(i, j, k);
                par1ItemStack.damageItem(-1, par2EntityPlayer);
            }


            return true;
        }
    }
}
