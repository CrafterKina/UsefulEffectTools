package com.mods.kina.UETools.items.swords;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemEnderSword extends ItemSword{
    public static int posx, posy, posz;
    public static boolean flag;

    public ItemEnderSword(){
        super(UEFieldsDeclaration.TELEPORT);
        setUnlocalizedName("itemEnderSword");
        setTextureName("kina:ender_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
        readFromNBT(par1ItemStack);
        Random rand = new Random();
        double d0 = 8.0D;
        double d1 = 5.0D;
        List list = par2World.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(par3EntityPlayer.posX - d0, par3EntityPlayer.posY - d1, par3EntityPlayer.posZ - d0, par3EntityPlayer.posX + d0, par3EntityPlayer.posY + d1, par3EntityPlayer.posZ + d0));
        if (!list.isEmpty())
        {
            if(Minecraft.getMinecraft().gameSettings.language.equals("ja_JP"))
                par3EntityPlayer.addChatMessage(new ChatComponentText("\u30E2\u30F3\u30B9\u30BF\u30FC\u304C\u8FD1\u304F\u306B\u3044\u308B\u305F\u3081\u7720\u308B\u3053\u3068\u304C\u3067\u304D\u307E\u305B\u3093"));
            else par3EntityPlayer.addChatMessage(new ChatComponentText("You may not teleport now, there are monsters nearby"));
            return par1ItemStack;
        }
        if(posx == 0 && posy == -1 && posz == 0 && !par2World.isRemote){
            if(Minecraft.getMinecraft().gameSettings.language.equals("ja_JP"))
                par3EntityPlayer.addChatMessage(new ChatComponentText("\u5730\u70B9\u3092\u767B\u9332\u3057\u3066\u304F\u3060\u3055\u3044"));
            else par3EntityPlayer.addChatMessage(new ChatComponentText("Please record the position"));
        }else{
            if(par3EntityPlayer instanceof EntityPlayerMP){
                ((EntityPlayerMP)par3EntityPlayer).playerNetServerHandler.setPlayerLocation((double) posx, (double) posy + 1, (double) posz, ((EntityPlayerMP) par3EntityPlayer).rotationYaw,((EntityPlayerMP) par3EntityPlayer).rotationPitch);
            }
            if(!par2World.isRemote){
                par1ItemStack.damageItem(1, par3EntityPlayer);
                flag = true;
            }else{
                byte i = 0;
                while(i <= 10){
                    i++;
                    par2World.spawnParticle("portal", par3EntityPlayer.posX + (rand.nextDouble() - 0.5D) * (double) par3EntityPlayer.width, par3EntityPlayer.posY + rand.nextDouble() * (double) par3EntityPlayer.height - 0.25D, par3EntityPlayer.posZ + (rand.nextDouble() - 0.5D) * (double) par3EntityPlayer.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
                }
            }
        }
        return par1ItemStack;
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

    private NBTTagCompound readFromNBT(ItemStack itemStack){
        NBTTagCompound nbttagcompound = itemStack.getTagCompound();
        try{
            posx = nbttagcompound.getInteger("x");
            posy = nbttagcompound.getInteger("y");
            posz = nbttagcompound.getInteger("z");
        }catch(NullPointerException e){
            posx = 0;
            posy = -1;
            posz = 0;
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
