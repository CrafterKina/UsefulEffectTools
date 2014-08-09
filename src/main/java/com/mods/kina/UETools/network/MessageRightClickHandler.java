package com.mods.kina.UETools.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class MessageRightClickHandler implements IMessageHandler<MessageRightClick, IMessage>{
    public EntityPlayerMP playerMP;

    /**
     Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no
     reply is needed.

     @param mes
     The message
     @param ctx
     @return an optional return message
     */
    @Override
    public IMessage onMessage(MessageRightClick mes, MessageContext ctx){
        playerMP=ctx.getServerHandler().playerEntity;
        playerMP.playerNetServerHandler.setPlayerLocation(mes.posX, mes.posY, mes.posZ, playerMP.rotationYaw, playerMP.rotationPitch);
        return null;
    }
}