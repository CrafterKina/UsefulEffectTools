package com.mods.kina.UETools.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class MessageRightClick implements IMessage{
    float posX;
    float posY;
    float posZ;
    public MessageRightClick(){}
    public MessageRightClick(float x,float y,float z){
        posX=x;
        posY=y;
        posZ=z;
    }
    /**
     Convert from the supplied buffer into your specific message type

     @param buf
     */
    public void fromBytes(ByteBuf buf){
        posX=buf.readFloat();
        posY=buf.readFloat();
        posZ=buf.readFloat();
    }

    /**
     Deconstruct your message into the supplied byte buffer

     @param buf
     */
    public void toBytes(ByteBuf buf){
        buf.writeFloat(posX);
        buf.writeFloat(posY);
        buf.writeFloat(posZ);
    }
}
