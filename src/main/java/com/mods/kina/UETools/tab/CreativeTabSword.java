package com.mods.kina.UETools.tab;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabSword extends CreativeTabs{
    public CreativeTabSword(String label){
        super(label);
    }

    @Override
    public Item getTabIconItem(){
        return UEFieldsDeclaration.itemThunderSword;
    }
}
