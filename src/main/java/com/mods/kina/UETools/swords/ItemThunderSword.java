package com.mods.kina.UETools.swords;

import com.mods.kina.KinaCore.misclib.KinaLib;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemThunderSword extends ItemSword{
    public ItemThunderSword(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemThunderSword");
        setTextureName("kina:thunder_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        KinaLib kinaLib = new KinaLib();
        kinaLib.spawnThunder(world, x, y, z);
        item.damageItem(5, player);//-5
        return true;
    }
}