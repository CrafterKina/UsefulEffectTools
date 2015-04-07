package com.mods.kina.UETools.items.swords;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class ItemTimeSword extends ItemSword{
    public ItemTimeSword(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemTimeSword");
        setTextureName("kina:time_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4){
        int j = getMaxItemUseDuration(par1ItemStack) - par4;
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.isCanceled()){
            return;
        }
        j = event.charge;
        float f = (float) j / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if(f > 1.0F){
            f = 1.0F;
        }
        if(f == 1.0F){
            long a = par2World.getWorldTime();
            if((a >= 0) && (a < 12000)){
                par2World.setWorldTime((long) 12000);
            }else{
                par2World.setWorldTime((long) 0);
            }
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack){
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack){
        return 72000;
    }
}