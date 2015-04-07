package com.mods.kina.UETools.items.swords;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemCraftSword extends Item{
    Minecraft mc=Minecraft.getMinecraft();
    boolean pushing=false;
    int mode=0;
    public ItemCraftSword(){
        super();
        setUnlocalizedName("KINADEBUG");
        setCreativeTab(CreativeTabs.tabCombat);
    }

    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
        return true;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack){
            if (mc.currentScreen == null && mc.gameSettings.keyBindAttack.getIsKeyPressed() && mc.inGameHasFocus && getMouseOver(1f) != null && getMouseOver(1f).typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = getMouseOver(1f).blockX;
                int j = getMouseOver(1f).blockY;
                int k = getMouseOver(1f).blockZ;

                if (mc.theWorld.getBlock(i, j, k).getMaterial() != Material.air)
                {
                    mc.playerController.onPlayerDamageBlock(i, j, k, getMouseOver(1f).sideHit);

                    if (mc.thePlayer.isCurrentToolAdventureModeExempt(i, j, k))
                    {
                        mc.effectRenderer.addBlockHitEffects(i, j, k, getMouseOver(1f));
                    }
                }
            }
            else
            {
                mc.playerController.resetBlockRemoving();
            }
        return true;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_){

    }

    public MovingObjectPosition getMouseOver(float p_78473_1_){
        if(mc.renderViewEntity != null && mc.theWorld != null){
            return mc.renderViewEntity.rayTrace(mc.gameSettings.renderDistanceChunks * 16, p_78473_1_);
        }
        return null;
    }
}