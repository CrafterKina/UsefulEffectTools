package com.mods.kina.UETools.swords;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemTeleportSword extends ItemSword{
    public static int posx, posy, posz;
    public static boolean flag;

    public ItemTeleportSword(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemTeleportSword");
        setTextureName("kina:teleport_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase){
        readFromNBT(par1ItemStack);
        Random rand = new Random();
        if(!(par3EntityLivingBase instanceof EntityAgeable) && !par3EntityLivingBase.worldObj.isRemote){
            if(Minecraft.getMinecraft().gameSettings.language.equals("ja_JP"))
                par2EntityPlayer.addChatMessage(new ChatComponentText(getEntityString(par3EntityLivingBase) + "\u306F\u8EE2\u9001\u3067\u304D\u307E\u305B\u3093"));
            else
                par2EntityPlayer.addChatMessage(new ChatComponentText(getEntityString(par3EntityLivingBase) + " can not transfer"));
        }else{
            if(posx == 0 && posy == -1 && posz == 0 && !par3EntityLivingBase.worldObj.isRemote){
                if(Minecraft.getMinecraft().gameSettings.language.equals("ja_JP"))
                    par2EntityPlayer.addChatMessage(new ChatComponentText("\u5730\u70B9\u3092\u767B\u9332\u3057\u3066\u304F\u3060\u3055\u3044"));
                else par2EntityPlayer.addChatMessage(new ChatComponentText("Please record the position"));
            }else{
                if(!par3EntityLivingBase.worldObj.isRemote){
                    //entity.worldObj.spawnParticle("portal", entity.posX + (rand.nextDouble() - 0.5D) * (double)entity.width, entity.posY + rand.nextDouble() * (double)entity.height - 0.25D, entity.posZ + (rand.nextDouble() - 0.5D) * (double)entity.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
                    par3EntityLivingBase.setPosition((double) posx, (double) posy + 1, (double) posz);
                    par1ItemStack.damageItem((int) par3EntityLivingBase.getHealth(), par3EntityLivingBase);
                    flag = true;
                }else{
                    byte i = 0;
                    while(i <= 10){
                        i++;
                        par3EntityLivingBase.worldObj.spawnParticle("portal", par3EntityLivingBase.posX + (rand.nextDouble() - 0.5D) * (double) par3EntityLivingBase.width, par3EntityLivingBase.posY + rand.nextDouble() * (double) par3EntityLivingBase.height - 0.25D, par3EntityLivingBase.posZ + (rand.nextDouble() - 0.5D) * (double) par3EntityLivingBase.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        posx = x;
        posy = y;
        posz = z;
        if(!world.isRemote){
            if(Minecraft.getMinecraft().gameSettings.language.equals("ja_JP"))
                player.addChatMessage(new ChatComponentText("\u4F4D\u7F6E" + posx + "," + posy + "," + posz + "\u3092\u8A18\u9332\u3057\u307E\u3057\u305F"));
            else
                player.addChatMessage(new ChatComponentText("Recorded the position[" + posx + "," + posy + "," + posz + "]"));
        }
        writeToNBT(item);
        return true;
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4){
        try{
            readFromNBT(par1ItemStack);
            par3List.add(EnumChatFormatting.AQUA + "[" + readFromNBT(par1ItemStack).getInteger("x") + "," + readFromNBT(par1ItemStack).getInteger("y") + "," + readFromNBT(par1ItemStack).getInteger("z") + "]");
        } catch(Exception e){
            par3List.add(EnumChatFormatting.AQUA + "[0,0,0]");
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
        if(!player.isSneaking()){
            flag = false;
        }else if(entity instanceof EntityLivingBase){
            itemInteractionForEntity(stack, player, (EntityLivingBase) entity);
        }else {
            super.onLeftClickEntity(stack, player, entity);
        }
        return flag;
    }

    public String getEntityString(Entity entity){
        return EntityList.getEntityString(entity);
    }

    private NBTTagCompound readFromNBT(ItemStack itemStack){
        NBTTagCompound nbttagcompound = itemStack.getTagCompound();
        try{
            posx = nbttagcompound.getInteger("x");
            posy = nbttagcompound.getInteger("y");
            posz = nbttagcompound.getInteger("z");
        }catch(NullPointerException e){
            posx=0;
            posy=-1;
            posz=0;
        }
        return nbttagcompound;
    }

    private NBTTagCompound writeToNBT(ItemStack itemStack){
        NBTTagCompound nbttagcompound1 = itemStack.getTagCompound();
        if(nbttagcompound1 == null) nbttagcompound1 = new NBTTagCompound();
        itemStack.setTagCompound(nbttagcompound1);
        try{
            nbttagcompound1.setInteger("x", posx);
            nbttagcompound1.setInteger("y", posy);
            nbttagcompound1.setInteger("z", posz);
        } catch(Exception e){
            nbttagcompound1.setInteger("x", 0);
            nbttagcompound1.setInteger("y", -1);
            nbttagcompound1.setInteger("z", 0);
        }
        return nbttagcompound1;
    }
}