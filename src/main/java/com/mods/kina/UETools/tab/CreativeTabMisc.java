package com.mods.kina.UETools.tab;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMisc extends CreativeTabs{
    public CreativeTabMisc(String lable){
        super(lable);
    }

    public Item getTabIconItem(){
        return UEFieldsDeclaration.itemDeliveryPhone;
    }
}
