package com.mods.kina.UETools.registry;

import com.mods.kina.UETools.UsefulEffectToolsCore;
import com.mods.kina.UETools.block.BlockLuminousDirt;
import com.mods.kina.UETools.block.BlockLuminousFarmland;
import com.mods.kina.UETools.block.BlockSummonTable;
import com.mods.kina.UETools.entity.EntityRidden;
import com.mods.kina.UETools.event.EventHandler;
import com.mods.kina.UETools.misc.*;
import com.mods.kina.UETools.swords.*;
import com.mods.kina.UETools.tileentity.TileEntitySummonTable;
import com.mods.kina.UETools.tools.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.MinecraftForge;

import static com.mods.kina.UETools.registry.UEFieldsDeclaration.*;

public class UERegistrarCore{
    public static void addRecipes(){
        ItemStack lightsword = new ItemStack(itemLightSword);
        lightsword.addEnchantment(Enchantment.smite, 2);
        ItemStack firesword = new ItemStack(itemFireSword);
        firesword.addEnchantment(Enchantment.fireAspect,1);
        ItemStack lavasword = new ItemStack(itemLavaSword);
        lavasword.addEnchantment(Enchantment.fireAspect, 3);
        GameRegistry.addRecipe(new ItemStack(itemThunderSword), "X", "X", "Y", 'X', Items.iron_ingot, 'Y', Blocks.stone);
        GameRegistry.addRecipe(new ItemStack(itemExplosionSword), " Y ", "YXY", " Y ", 'X', itemThunderSword, 'Y', Items.gunpowder);
        GameRegistry.addRecipe(lightsword, "YYY", "YXY", "YYY", 'X', itemThunderSword, 'Y', Blocks.glowstone);
        GameRegistry.addRecipe(new ItemStack(itemTeleportSword), "YXY", 'X', itemExplosionSword, 'Y', Items.ender_pearl);
        GameRegistry.addShapelessRecipe(new ItemStack(itemTntSword), new ItemStack(itemExplosionSword), new ItemStack(Items.flint_and_steel), new ItemStack(Blocks.tnt));
        GameRegistry.addRecipe(new ItemStack(itemTntPickaxe), "YYY", " X ", " Z ", 'X', itemTntSword, 'Y', Items.iron_ingot, 'Z', Items.stick);
        GameRegistry.addRecipe(new ItemStack(itemCreeperPickaxe), "YYY", " X ", " Z ", 'X', itemTntPickaxe, 'Y', Items.diamond, 'Z', Items.stick);
        GameRegistry.addRecipe(firesword, "FTI", 'F', Items.flint, 'T', itemThunderSword, 'I', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(itemWaterSword), " W ", " W ", " T ", 'W', Items.water_bucket, 'T', itemTntSword);
        GameRegistry.addShapelessRecipe(lavasword, new ItemStack(itemFireSword), new ItemStack(Blocks.obsidian), new ItemStack(Items.lava_bucket, 2));
        GameRegistry.addRecipe(new ItemStack(itemInfinityCobblePickaxe), "LCW", " B ", " B ", 'L', itemLavaSword, 'W', itemWaterSword, 'C', Blocks.cobblestone, 'B', Items.stick);
        GameRegistry.addRecipe(new ItemStack(itemInfinityCobblePickaxe), "WCL", " B ", " B ", 'L', itemLavaSword, 'W', itemWaterSword, 'C', Blocks.cobblestone, 'B', Items.stick);
        GameRegistry.addRecipe(new ItemStack(itemBoneMealSword), " B ", " B ", " T ", 'B', Items.bone, 'T', itemTntSword);
        GameRegistry.addShapelessRecipe(new ItemStack(itemPickHoe), new ItemStack(itemBoneMealSword), new ItemStack(Items.wooden_hoe));
        GameRegistry.addRecipe(new ItemStack(itemTileWatcher), "PPP", "PBP", "PPP", 'B', Items.ender_eye, 'P', Items.paper);
        GameRegistry.addShapelessRecipe(new ItemStack(itemEdibleSword), new ItemStack(Items.poisonous_potato), new ItemStack(Items.iron_sword), new ItemStack(Items.cooked_beef), new ItemStack(Items.cooked_chicken), new ItemStack(Items.cooked_fished), new ItemStack(Items.cooked_porkchop), new ItemStack(Items.spider_eye), new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh));
        GameRegistry.addSmelting(itemRustySword, new ItemStack(itemTimeSword), 0.1F);
        GameRegistry.addRecipe(new ItemStack(itemDeliveryPhone), "EEE", "ESE", "EPE", 'E', Items.emerald, 'S', itemTeleportSword, 'P', itemTileWatcher);
        GameRegistry.addShapelessRecipe(new ItemStack(itemEnderSword), new ItemStack(itemTeleportSword), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl), new ItemStack(Items.ender_pearl));
    }
    public static void assignItems(){
        itemThunderSword = (new ItemThunderSword(THUNDER));
        itemExplosionSword = (new ItemExplosionSword(EXPLOSION));
        itemLightSword = (new ItemLightSword(LIGHT));
        itemTeleportSword = (new ItemTeleportSword(TELEPORT));
        itemRustySword = (new ItemSword(RUSTY).setUnlocalizedName("itemRustySword").setTextureName("kina:rusty_sword").setCreativeTab(tabUESword));
        itemBrokenSword = (new Item()).setUnlocalizedName("itemBrokenSword").setTextureName("kina:broken_sword").setCreativeTab(tabUESword);
        itemTntSword = (new ItemTntSword(TNT));
        itemTntPickaxe = (new ItemTntPickaxe(TNT));
        itemCreeperPickaxe = (new ItemCreeperPickaxe(Item.ToolMaterial.EMERALD));
        itemTimeSword = (new ItemTimeSword(TIME));
        itemFireSword = (new ItemFireSword(FIRE));
        itemWaterSword = (new ItemWaterSword(WATER));
        itemLavaSword = (new ItemLavaSword(LAVA));
        itemInfinityCobblePickaxe = (new ItemInfinityCobblePickaxe(Item.ToolMaterial.STONE));
        itemBoneMealSword = (new ItemBoneMealSword(BONE));
        itemCraftSword = (new ItemCraftSword());
        itemPickHoe = (new ItemPickHoe(Item.ToolMaterial.WOOD));
        itemGrassPart = (new ItemGrassPart());
        itemMycelPart = (new ItemMycelPart());
        itemEdibleSword = (new ItemEdibleSword(5));
        itemTileWatcher = (new ItemTileWatcher());
        //itemEnderBow = (new ItemCraftSword());
        itemFastHand = new ItemHandLikeRemover();
        itemDeliveryPhone = new ItemDeliveryPhone();
        itemEnderSword = new ItemEnderSword();
        itemSuperMover = new ItemSuperMover();
        blockSummonTable=new BlockSummonTable();
        blockLuminousDirt=new BlockLuminousDirt();
        blockLuminousFarmland=new BlockLuminousFarmland();
        //blockReinforcedEnchantmentTable=new BlockReinforcedEnchantmentTable();
    }
    public static void registrarItems(){
        GameRegistry.registerItem(itemThunderSword, "itemThunderSword");
        GameRegistry.registerItem(itemExplosionSword, "itemExplosionSword");
        GameRegistry.registerItem(itemLightSword, "itemLightSword");
        GameRegistry.registerItem(itemTeleportSword, "itemTeleportSword");
        GameRegistry.registerItem(itemRustySword, "itemRustySword");
        GameRegistry.registerItem(itemBrokenSword, "itemBrokenSword");
        GameRegistry.registerItem(itemTntSword, "itemTntSword");
        GameRegistry.registerItem(itemTntPickaxe, "itemTntPickaxe");
        GameRegistry.registerItem(itemCreeperPickaxe, "itemCreeperPickaxe");
        GameRegistry.registerItem(itemTimeSword, "itemTimeSword");
        GameRegistry.registerItem(itemFireSword, "itemFireSword");
        GameRegistry.registerItem(itemWaterSword, "itemWaterSword");
        GameRegistry.registerItem(itemLavaSword, "itemLavaSword");
        GameRegistry.registerItem(itemInfinityCobblePickaxe, "itemInfinityCobblePickaxe");
        GameRegistry.registerItem(itemBoneMealSword, "itemBoneMealSword");
        GameRegistry.registerItem(itemCraftSword, "itemCraftSword");
        GameRegistry.registerItem(itemPickHoe, "itemPickHoe");
        GameRegistry.registerItem(itemGrassPart, "itemGrassPart");
        GameRegistry.registerItem(itemMycelPart, "itemMycelPart");
        GameRegistry.registerItem(itemEdibleSword, "itemEdibleSword");
        GameRegistry.registerItem(itemTileWatcher, "itemTileWatcher");
        //GameRegistry.registerItem(itemEnderBow, "itemEnderBow");
        GameRegistry.registerItem(itemFastHand, "itemFastHand");
        GameRegistry.registerItem(itemDeliveryPhone, "itemDeliveryPhone");
        GameRegistry.registerItem(itemEnderSword,"itemEnderSword");
        GameRegistry.registerItem(itemSuperMover,"itemSuperMover");
        GameRegistry.registerBlock(blockSummonTable,"blockSummonTable");
        GameRegistry.registerBlock(blockLuminousDirt,"blockLuminousDirt");
        GameRegistry.registerBlock(blockLuminousFarmland,"blockLuminousFarmland");
//        GameRegistry.registerBlock(blockReinforcedEnchantmentTable,"blockReinforcedEnchantmentTable");
    }

    public static void registerTileEntity(){
        GameRegistry.registerTileEntity(TileEntitySummonTable.class,"TileEntitySummonTable");
        //GameRegistry.registerTileEntity(TileEntityReinforcedEnchantmentTable.class,"TileEntityReinforcedEnchantmentTable");
        NetworkRegistry.INSTANCE.registerGuiHandler(UsefulEffectToolsCore.core, UsefulEffectToolsCore.proxy);
    }

    public static void registerEntity(){
        EntityRegistry.registerModEntity(EntityRidden.class, "MeleeSkeleton", 0, UsefulEffectToolsCore.core, 250, 1, false);
        UsefulEffectToolsCore.proxy.registerRender();
    }

    public static void registerMisc(){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
