/*
package com.mods.kina.UETools.block;

import com.mods.kina.UETools.UsefulEffectToolsCore;
import com.mods.kina.UETools.tileentity.TileEntityReinforcedEnchantmentTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockReinforcedEnchantmentTable extends BlockContainer{
    public BlockReinforcedEnchantmentTable(){
        super(Material.rock);
    }

    public TileEntity createNewTileEntity(World world, int p_149915_2_){
        return new TileEntityReinforcedEnchantmentTable();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float dizX, float dizY, float dizZ){
        if(player.isSneaking()){
            TileEntityReinforcedEnchantmentTable table=(TileEntityReinforcedEnchantmentTable)world.getTileEntity(x,y,z);
            if(table.itemStacks[0]!=null){
                world.spawnEntityInWorld(new EntityItem(world,x+0.5,y,z+0.5,table.itemStacks[0]));
            }else table.itemStacks[0]=player.getCurrentEquippedItem();
        }else if(!world.isRemote){
            player.openGui(UsefulEffectToolsCore.core,2,world,x,y,z);
        }else {
            return false;
        }
        return true;
    }
}
*/
