package com.mods.kina.UETools.blocks;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLuminousFarmland extends BlockFarmland{
    public BlockLuminousFarmland(){
        super();
        setLightLevel(1);
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
    }
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_){}
    public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_)
    {
        if (!p_149746_1_.isRemote && p_149746_1_.rand.nextFloat() < p_149746_6_ - 0.5F)
        {
            if (!(p_149746_5_ instanceof EntityPlayer) && !p_149746_1_.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            p_149746_1_.setBlock(p_149746_2_, p_149746_3_, p_149746_4_, UEFieldsDeclaration.blockLuminousDirt);
        }
    }
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return UEFieldsDeclaration.blockLuminousDirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(UEFieldsDeclaration.blockLuminousDirt);
    }
}
