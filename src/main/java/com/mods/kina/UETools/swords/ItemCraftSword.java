package com.mods.kina.UETools.swords;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
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

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){

        //MovingObjectPosition position =world.rayTraceBlocks(Vec3.createVectorHelper(player.posX, player.posY, player.posZ), getNearestBlockPosition(player));
        MovingObjectPosition teleportTo = getMouseOver(1.0f);
        System.out.println(teleportTo);
        if(teleportTo!=null){
            Vec3 teleportPos=teleportTo.hitVec;
            //Vec3 airPosition = getAirPosition(position);
            if(player instanceof EntityPlayerMP&&teleportTo.typeOfHit!= MovingObjectPosition.MovingObjectType.MISS){
                ((EntityPlayerMP) player).playerNetServerHandler.setPlayerLocation(teleportPos.xCoord, teleportPos.yCoord, teleportPos.zCoord, ((EntityPlayerMP) player).rotationYaw, ((EntityPlayerMP) player).rotationPitch);
            }
            //System.out.println(airPosition);
        }
        return itemStack;
    }

    /*private Vec3 getNearestBlockPosition(EntityPlayer player){
        final int d = 100;
        switch(MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3){
            case 0:return Vec3.createVectorHelper(player.posX, player.posY, player.posZ+d);
            case 1:return Vec3.createVectorHelper(player.posX+d, player.posY, player.posZ);
            case 2:return Vec3.createVectorHelper(player.posX, player.posY, player.posZ-d);
            case 3:return Vec3.createVectorHelper(player.posX-d, player.posY, player.posZ);
            default:return null;
        }
    }

    private Vec3 getAirPosition(MovingObjectPosition position){
        Vec3 vec3=position.hitVec;
        switch(position.sideHit){
            case 2:return Vec3.createVectorHelper(vec3.xCoord+1, vec3.yCoord, vec3.zCoord);
            case 3:return Vec3.createVectorHelper(vec3.xCoord, vec3.yCoord, vec3.zCoord+1);
            case 4:return Vec3.createVectorHelper(vec3.xCoord-1, vec3.yCoord, vec3.zCoord);
            case 5:return Vec3.createVectorHelper(vec3.xCoord, vec3.yCoord, vec3.zCoord-1);
            default:return null;
        }
    }*/

    public MovingObjectPosition getMouseOver(float p_78473_1_)
    {
        if (this.mc.renderViewEntity != null)
        {
            if (this.mc.theWorld != null)
            {
                return this.mc.renderViewEntity.rayTrace(100, p_78473_1_);
            }
        }
        return null;
    }
}