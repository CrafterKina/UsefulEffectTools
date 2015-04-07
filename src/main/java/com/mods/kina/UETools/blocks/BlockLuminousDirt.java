package com.mods.kina.UETools.blocks;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.block.BlockDirt;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockLuminousDirt extends BlockDirt{
    public BlockLuminousDirt(){
        super();
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
        setBlockName("blockLuminousDirt");
        setLightLevel(1);
        setBlockTextureName("kina:luminous_dirt");
    }

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_){
        p_149660_1_.setLightValue(p_149660_1_.isAirBlock(p_149660_2_,p_149660_3_,p_149660_4_)? EnumSkyBlock.Sky:EnumSkyBlock.Block,p_149660_2_,p_149660_3_,p_149660_4_,15);
        return p_149660_9_;
    }
}
