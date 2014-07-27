package com.mods.kina.UETools.swords;

import com.mods.kina.KinaCore.misclib.KinaLib;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import java.util.Random;

public class ItemLightSword extends ItemSword{
    public ItemLightSword(ToolMaterial material){
        super(material);
        setUnlocalizedName("itemLightSword");
        setTextureName("kina:light_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float disX, float disY, float disZ){

        KinaLib kinaLib = new KinaLib();
        Random rnd = new Random();

        int a = x + rnd.nextInt(10);
        int c = z + rnd.nextInt(10);
        int d = x + rnd.nextInt(10);
        int f = z + rnd.nextInt(10);
        int g = x + rnd.nextInt(10);
        int i = z + rnd.nextInt(10);
        int j = x + rnd.nextInt(10);
        int l = z + rnd.nextInt(10);

        //EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z);
        //world.addWeatherEffect(entitylightningbolt);
        //world.spawnEntityInWorld(entitylightningbolt);
        kinaLib.spawnThunder(world, x, y, z);

        //EntityLightningBolt entitylightningbolt1 = new EntityLightningBolt(world, a, y, c);
        //world.addWeatherEffect(entitylightningbolt1);
        //world.spawnEntityInWorld(entitylightningbolt1);
        kinaLib.spawnThunder(world, a, y, c);

        //EntityLightningBolt entitylightningbolt2 = new EntityLightningBolt(world, d, y, f);
        //world.addWeatherEffect(entitylightningbolt2);
        //world.spawnEntityInWorld(entitylightningbolt2);
        kinaLib.spawnThunder(world, d, y, f);

        //EntityLightningBolt entitylightningbolt3 = new EntityLightningBolt(world, g, y, i);
        //world.addWeatherEffect(entitylightningbolt3);
        //world.spawnEntityInWorld(entitylightningbolt3);
        kinaLib.spawnThunder(world, g, y, i);

        //EntityLightningBolt entitylightningbolt4 = new EntityLightningBolt(world, j, y, l);
        //world.addWeatherEffect(entitylightningbolt4);
        //world.spawnEntityInWorld(entitylightningbolt4);
        kinaLib.spawnThunder(world, j, y, l);

        item.damageItem(2, player);

        return true;
    }

}