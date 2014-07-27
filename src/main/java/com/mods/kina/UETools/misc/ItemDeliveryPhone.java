package com.mods.kina.UETools.misc;

import com.mods.kina.UETools.container.ContainerDPhone;
import com.mods.kina.UETools.proxy.CommonProxy;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.common.registry.VillagerRegistry;
import io.netty.buffer.Unpooled;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

import java.io.IOException;
import java.util.Random;

public class ItemDeliveryPhone extends Item{
    public ItemDeliveryPhone(){
        super();
        setUnlocalizedName("itemDeliveryPhone");
        setTextureName("kina:delivery_phone");
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
        Random r=new Random();
        CommonProxy.villager = new EntityVillager(par2World, r.nextInt(5 + VillagerRegistry.getRegisteredVillagers().size()));
        if (!par2World.isRemote)
        {
            CommonProxy.villager.setCustomer(par3EntityPlayer);
            if(par3EntityPlayer instanceof EntityPlayerSP)par3EntityPlayer.displayGUIMerchant(CommonProxy.villager,"");
            else if(par3EntityPlayer instanceof EntityPlayerMP)displayGUIMerchant(CommonProxy.villager, "", (EntityPlayerMP) par3EntityPlayer);
        }
        return par1ItemStack;
    }
    public void displayGUIMerchant(IMerchant p_71030_1_, String p_71030_2_,EntityPlayerMP player)
    {
        player.getNextWindowId();
        player.openContainer = new ContainerDPhone(player.inventory, p_71030_1_, player.worldObj);
        player.openContainer.windowId = player.currentWindowId;
        player.openContainer.addCraftingToCrafters(player);
        InventoryMerchant inventorymerchant = ((ContainerDPhone)player.openContainer).getMerchantInventory();
        player.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(player.currentWindowId, 6, p_71030_2_ == null ? "" : p_71030_2_, inventorymerchant.getSizeInventory(), p_71030_2_ != null));
        MerchantRecipeList merchantrecipelist = p_71030_1_.getRecipes(player);

        if (merchantrecipelist != null)
        {
            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());

            try
            {
                packetbuffer.writeInt(player.currentWindowId);
                merchantrecipelist.func_151391_a(packetbuffer);
                player.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|TrList", packetbuffer));
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            finally
            {
                packetbuffer.release();
            }
        }
    }
}
