package com.mods.kina.UETools.items.tools;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;

public class ItemHandLikeRemover extends ItemTool{
    public ItemHandLikeRemover(){
        super(0f, ToolMaterial.EMERALD, Sets.newHashSet(Blocks.air));
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("itemFastHand");
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return ForgeHooks.isToolEffective(new ItemStack(Items.diamond_pickaxe), p_150893_2_, 0) || ForgeHooks.isToolEffective(new ItemStack(Items.diamond_axe), p_150893_2_, 0) || ForgeHooks.isToolEffective(new ItemStack(Items.diamond_shovel), p_150893_2_, 0) ? 1f:efficiencyOnProperMaterial;
    }

    public Multimap getItemAttributeModifiers()
    {
        return super.getItemAttributeModifiers();
    }
}
