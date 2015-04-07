package com.mods.kina.UETools.items.misc;

import com.mods.kina.UETools.network.MessageRightClick;
import com.mods.kina.UETools.network.PacketHandler;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSuperMover extends Item{

    Minecraft mc = Minecraft.getMinecraft();

    public ItemSuperMover(){
        super();
        setUnlocalizedName("itemSuperMover");
        setTextureName("kina:super_mover");
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
        MovingObjectPosition teleportTo = getMouseOver(1.0f);
        System.out.println(teleportTo);
        if(teleportTo != null){
            Vec3 teleportPos = teleportTo.hitVec;
            if(player instanceof EntityPlayerMP && teleportTo.typeOfHit != MovingObjectPosition.MovingObjectType.MISS&& teleportTo.blockY != ((EntityPlayerMP) player).posY - 1 && isAir(world, teleportTo)){
                ((EntityPlayerMP) player).playerNetServerHandler.setPlayerLocation(teleportPos.xCoord, teleportPos.yCoord + getBlockHeight(world, teleportTo), teleportPos.zCoord, ((EntityPlayerMP) player).rotationYaw, ((EntityPlayerMP) player).rotationPitch);
            }
        }
        player.setSneaking(false);
        return itemStack;
    }

    private double getBlockHeight(World world, MovingObjectPosition pos){
        Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
        return block.getCollisionBoundingBoxFromPool(world, pos.blockX, pos.blockY, pos.blockZ) != null || world.getBlock(pos.blockX, pos.blockY, pos.blockZ).getMaterial().getMaterialMobility() == 1 ? block.getBlockBoundsMaxY() : 0;
    }

    private boolean isAir(World world, MovingObjectPosition pos){
        return isAir(world, pos.blockX, pos.blockY, pos.blockZ);
    }

    private boolean isAir(World world, int x, int y, int z){
        return (world.isAirBlock(x, y + 1, z) || world.getBlock(x, y + 1, z).getMaterial().getMaterialMobility() == 1) && (world.isAirBlock(x, y + 2, z) || world.getBlock(x, y + 2, z).getMaterial().getMaterialMobility() == 1);
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        if(y != (int) player.posY - 2 && isAir(world, x, y, z))
            PacketHandler.INSTANCE.sendToServer(new MessageRightClick(x+0.5f, y + 1, z+0.5f));
        System.out.println(y + "," + ((int) player.posY - 2));
        return true;
    }

    public MovingObjectPosition getMouseOver(float p_78473_1_){
        if(mc.renderViewEntity != null && mc.theWorld != null){
            return mc.renderViewEntity.rayTrace(mc.gameSettings.renderDistanceChunks * 16, p_78473_1_);
        }
        return null;
    }
}
