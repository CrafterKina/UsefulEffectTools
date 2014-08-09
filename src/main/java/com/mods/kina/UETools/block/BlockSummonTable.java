package com.mods.kina.UETools.block;

import com.mods.kina.UETools.UsefulEffectToolsCore;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import com.mods.kina.UETools.tileentity.TileEntitySummonTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSummonTable extends BlockContainer{
    public BlockSummonTable(){
        super(Material.rock);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
    }

    public TileEntity createNewTileEntity(World var1, int var2){
        return new TileEntitySummonTable();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float dx, float dy, float dz){
        if(!world.isRemote){
            player.openGui(UsefulEffectToolsCore.core, 1, world, x, y, z);
        }
        return true;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
}
