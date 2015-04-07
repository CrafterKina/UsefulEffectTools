package com.mods.kina.UETools.items.swords;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemTntSword extends ItemSword{
    public ItemTntSword(ToolMaterial material){
        super(material);
        this.setUnlocalizedName("itemTntSword");
        this.setTextureName("kina:tnt_sword");
        this.setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
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