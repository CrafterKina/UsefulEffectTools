package com.mods.kina.UETools.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTntPickaxe extends ItemPickaxe{
    public ItemTntPickaxe(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemTntPickaxe");
        setTextureName("kina:tnt_pickaxe");
        setCreativeTab(CreativeTabs.tabTools);
    }

    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        double a = (int) Math.floor(x);
        double b = (int) Math.floor(y + 2);
        double c = (int) Math.floor(z);
        if(!world.isRemote){
            EntityTNTPrimed entitytnt = new EntityTNTPrimed(world, a, b, c, player);
            world.spawnEntityInWorld(entitytnt);
            world.playSoundAtEntity(entitytnt, "random.fuse", 1.0F, 1.0F);
        }
        item.damageItem(10, player);
        return true;
    }
}