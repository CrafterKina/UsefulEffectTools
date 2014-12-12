/*
package com.mods.kina.UETools.tileentity;

import com.mods.kina.KinaCore.toExtends.IInventoryImpl;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class TileEntityReinforcedEnchantmentTable extends IInventoryImpl{//Todo 実装待ったなし

    public int field_145926_a;
    public float field_145933_i;
    public float field_145931_j;
    public float field_145932_k;
    public float field_145929_l;
    public float field_145930_m;
    public float field_145927_n;
    public float field_145928_o;
    public float field_145925_p;
    public float field_145924_q;
    private static Random rnd = new Random();
    public ItemStack[] itemStacks=new ItemStack[1];

    public TileEntityReinforcedEnchantmentTable(){
        setItemStack(itemStacks);
    }


    @Override
    public int getInventoryStackLimit(){
        return 1;
    }

    */
/**
     Returns the name of the inventory
     *//*

    public String getInventoryName(){
        return UEFieldsDeclaration.blockReinforcedEnchantmentTable.getLocalizedName();
    }

    public void updateEntity()
    {
        super.updateEntity();
        field_145927_n = field_145930_m;
        field_145925_p = field_145928_o;
        EntityPlayer entityplayer = worldObj.getClosestPlayer((double)((float)xCoord + 0.5F), (double)((float)yCoord + 0.5F), (double)((float)zCoord + 0.5F), 3.0D);

        if (entityplayer != null)
        {
            double d0 = entityplayer.posX - (double)((float)xCoord + 0.5F);
            double d1 = entityplayer.posZ - (double)((float)zCoord + 0.5F);
            field_145924_q = (float)Math.atan2(d1, d0);
            field_145930_m += 0.1F;

            if (field_145930_m < 0.5F || rnd.nextInt(40) == 0)
            {
                float f1 = field_145932_k;

                do
                {
                    field_145932_k += (float)(rnd.nextInt(4) - rnd.nextInt(4));
                }
                while (f1 == field_145932_k);
            }
        }
        else
        {
            field_145924_q += 0.02F;
            field_145930_m -= 0.1F;
        }

        while (field_145928_o >= (float)Math.PI)
        {
            field_145928_o -= ((float)Math.PI * 2F);
        }

        while (field_145928_o < -(float)Math.PI)
        {
            field_145928_o += ((float)Math.PI * 2F);
        }

        while (field_145924_q >= (float)Math.PI)
        {
            field_145924_q -= ((float)Math.PI * 2F);
        }

        while (field_145924_q < -(float)Math.PI)
        {
            field_145924_q += ((float)Math.PI * 2F);
        }

        float f2= field_145924_q - field_145928_o;

        while(f2 >= (float)Math.PI)
        {
            f2 -= ((float)Math.PI * 2F);
        }

        while (f2 < -(float)Math.PI)
        {
            f2 += ((float)Math.PI * 2F);
        }

        field_145928_o += f2 * 0.4F;

        if (field_145930_m < 0.0F)
        {
            field_145930_m = 0.0F;
        }

        if (field_145930_m > 1.0F)
        {
            field_145930_m = 1.0F;
        }

        ++field_145926_a;
        field_145931_j = field_145933_i;
        float f = (field_145932_k - field_145933_i) * 0.4F;
        float f3 = 0.2F;

        if (f < -f3)
        {
            f = -f3;
        }

        if (f > f3)
        {
            f = f3;
        }

        field_145929_l += (f - field_145929_l) * 0.9F;
        field_145933_i += field_145929_l;
    }
}
*/
