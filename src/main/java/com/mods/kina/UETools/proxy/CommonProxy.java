package com.mods.kina.UETools.proxy;

import com.mods.kina.UETools.container.ContainerDPhone;
import com.mods.kina.UETools.container.ContainerSummonTable;
import com.mods.kina.UETools.gui.GuiSummonTable;
import com.mods.kina.UETools.tileentity.TileEntitySummonTable;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

public class CommonProxy implements IGuiHandler{
    public static IMerchant villager;
    private Random r=new Random();
    /**
     Returns a Server side Container to be displayed to the user.

     @param ID
     The Gui ID Number
     @param player
     The player viewing the Gui
     @param world
     The current world
     @param x
     X Position
     @param y
     Y Position
     @param z
     Z Position
     @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        //if(world.getTileEntity(x,y,z)!=null){
            switch(ID){
                case 0:return new ContainerDPhone(player.inventory,villager,world);
                case 1:return new ContainerSummonTable(player.inventory,(TileEntitySummonTable)world.getTileEntity(x,y,z));
            }
        //}
        return null;
    }

    /**
     Returns a Container to be displayed to the user. On the client side, this needs to return a instance of GuiScreen On
     the server side, this needs to return a instance of Container

     @param ID
     The Gui ID Number
     @param player
     The player viewing the Gui
     @param world
     The current world
     @param x
     X Position
     @param y
     Y Position
     @param z
     Z Position
     @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        //if(world.getTileEntity(x,y,z)!=null){
            switch(ID){
                case 0:return new GuiMerchant(player.inventory,villager,world,"");
                case 1:return new GuiSummonTable(player.inventory,(TileEntitySummonTable)world.getTileEntity(x,y,z));
            }
        //}
        return null;
    }
    public void registerRender(){}
}
