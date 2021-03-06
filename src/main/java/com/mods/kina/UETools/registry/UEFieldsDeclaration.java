package com.mods.kina.UETools.registry;

import com.mods.kina.UETools.tab.CreativeTabMisc;
import com.mods.kina.UETools.tab.CreativeTabSword;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;

public class UEFieldsDeclaration{
    public static final String MODID = "kina_UETools";
    public static final Item.ToolMaterial THUNDER = EnumHelper.addToolMaterial("THUNDER", 0, 150, 4.0F, 2.0F, 15);
    public static final Item.ToolMaterial EXPLOSION = EnumHelper.addToolMaterial("EXPLOSION", 0, 200, 4.0F, 2.5F, 10);
    public static final Item.ToolMaterial LIGHT = EnumHelper.addToolMaterial("LIGHT", 0, 100, 4.0F, 1.0F, 35);
    public static final Item.ToolMaterial TELEPORT = EnumHelper.addToolMaterial("TELEPORT", 0, 200, 4.0F, 2.0F, 10);
    public static final Item.ToolMaterial TNT = EnumHelper.addToolMaterial("TNT", 2, 200, 2.0F, 0.0F, 10);
    public static final Item.ToolMaterial RUSTY = EnumHelper.addToolMaterial("RUSTY", 0, 59, 2.0F, 0.0F, 15);
    public static final Item.ToolMaterial TIME = EnumHelper.addToolMaterial("TIME", 0, 200, 2.0F, 0.0F, 15);
    public static final Item.ToolMaterial FIRE = EnumHelper.addToolMaterial("FIRE", 0, 250, 2.0F, 0.0F, 10);
    public static final Item.ToolMaterial WATER = EnumHelper.addToolMaterial("WATER", 2, 250, 1.0F, 1.0F, 12);
    public static final Item.ToolMaterial LAVA = EnumHelper.addToolMaterial("LAVA", 2, 200, 1.0F, 2.0F, 12);
    public static final Item.ToolMaterial BONE = EnumHelper.addToolMaterial("BONE", 2, 200, 1.0F, 0.0F, 1);
    public static final CreativeTabs tabUESword = new CreativeTabSword("UESword");
    public static final CreativeTabs tabUEMisc =  new CreativeTabMisc("UEMisc");
    public static final Enchantment ENC_AutoRepair=Enchantment.efficiency;
    //public static Item ;
    public static Item itemThunderSword;
    public static Item itemExplosionSword;
    public static Item itemLightSword;
    public static Item itemTeleportSword;
    public static Item itemRustySword;
    public static Item itemBrokenSword;
    public static Item itemTntSword;
    public static Item itemTntPickaxe;
    public static Item itemCreeperPickaxe;
    public static Item itemTimeSword;
    public static Item itemFireSword;
    public static Item itemFastHand;
    public static Item itemEnderBow;
    public static Item itemWaterSword;
    public static Item itemLavaSword;
    public static Item itemInfinityCobblePickaxe;
    public static Item itemBoneMealSword;
    public static Item itemCraftSword;
    public static Item itemPickHoe;
    public static Item itemGrassPart;
    public static Item itemMycelPart;
    public static Item itemEdibleSword;
    public static Item itemTileWatcher;
    public static Item itemDeliveryPhone;
    public static Item itemEnderSword;
    public static Item itemSuperMover;
    public static Block blockSummonTable;
    public static Block blockLuminousDirt;
    public static Block blockLuminousFarmland;
    public static Block blockReinforcedEnchantmentTable;
    public static final String DUNGEON_CHEST = ChestGenHooks.DUNGEON_CHEST;
}