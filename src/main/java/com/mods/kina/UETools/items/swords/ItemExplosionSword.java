package com.mods.kina.UETools.items.swords;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemExplosionSword extends ItemSword{
    public ItemExplosionSword(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemExplosionSword");
        setTextureName("kina:explosion_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){
        EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z);
        world.addWeatherEffect(entitylightningbolt);
        world.createExplosion(null, x, y, z, 1.0F, true);
        item.damageItem(10, player);
        return true;
    }
}