package com.mods.kina.UETools.event;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class EventHandler{
    int count=0;
    @SubscribeEvent
    public void onPlayerUpdate(TickEvent.PlayerTickEvent event){
        EntityPlayer player=event.player;
        ItemStack itemStack = player.getCurrentEquippedItem();
        if(itemStack!=null&& EnchantmentHelper.getEnchantments(itemStack).keySet().contains(UEFieldsDeclaration.ENC_AutoRepair.effectId)){
            count+=1;
            if(count%1000==0){
                player.addChatComponentMessage(new ChatComponentText(String.valueOf(-itemStack.getItemDamage()/50*itemStack.getItem().getItemEnchantability())));
                itemStack.damageItem(-itemStack.getItemDamage()/itemStack.getItem().getItemEnchantability(),player);
            }
            if(count==Integer.MAX_VALUE-1){
                count=0;
            }
        }
    }
    @SubscribeEvent
    public void onUseHoeToLuminousDirt(UseHoeEvent event){
        Block block1 = UEFieldsDeclaration.blockLuminousFarmland;
        if(event.world.getBlock(event.x,event.y,event.z).equals(UEFieldsDeclaration.blockLuminousDirt)){
            event.world.playSoundEffect((double) ((float) event.x + 0.5F), (double) ((float) event.y + 0.5F), (double) ((float) event.z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
            event.world.setBlock(event.x,event.y,event.z,block1);
            event.setResult(Event.Result.ALLOW);
        }else{
            event.setResult(Event.Result.DEFAULT);
        }
    }
    public void registerHandler(){
        FMLCommonHandler.instance().bus().register(this);
    }
}
