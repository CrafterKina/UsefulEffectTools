package com.mods.kina.UETools;

import com.mods.kina.KinaCore.toExtends.KinaMod;
import com.mods.kina.UETools.network.PacketHandler;
import com.mods.kina.UETools.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import static com.mods.kina.UETools.registry.UEFieldsDeclaration.*;
import static com.mods.kina.UETools.registry.UERegistrarCore.*;

@KinaMod
@Mod(modid = MODID)
public class UsefulEffectToolsCore{
    @Mod.Instance("kina_UETools")
    public static UsefulEffectToolsCore core;

    @SidedProxy(modId = "kina_UETools", clientSide = "com.mods.kina.UETools.proxy.ClientProxy", serverSide = "com.mods.kina.UETools.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        assignItems();
        registrarItems();
        PacketHandler.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        addRecipes();
        registerTileEntity();
        registerEntity();
        new com.mods.kina.UETools.event.EventHandler().registerHandler();
        ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(itemRustySword), 1, 1, 3));
        ChestGenHooks.getInfo(DUNGEON_CHEST).setMax(10);
        ChestGenHooks.getInfo(DUNGEON_CHEST).setMin(5);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    }
}