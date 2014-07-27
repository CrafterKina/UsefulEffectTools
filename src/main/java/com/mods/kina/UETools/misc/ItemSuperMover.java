package com.mods.kina.UETools.misc;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.item.Item;

public class ItemSuperMover extends Item{
    public ItemSuperMover(){
        super();
        setUnlocalizedName("KINADEBUG");
        setCreativeTab(UEFieldsDeclaration.tabUEMisc);
    }
}
