package com.mods.kina.UETools.items.swords;

import com.google.common.collect.Multimap;
import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;


public class ItemEdibleSword extends ItemFood{
    private float damageVsEntity;

    public ItemEdibleSword(int par1){
        super(par1, 0.6F, false);
        setMaxStackSize(1);
        setUnlocalizedName("itemEdibleSword");
        setTextureName("kina:edible_sword");
        setCreativeTab(UEFieldsDeclaration.tabUESword);
        setMaxDamage(50);
        setAlwaysEdible();
        damageVsEntity = 6;
        EnumHelper.addEnchantmentType("weapon");
        setFull3D();
    }

    public boolean isItemTool(ItemStack par1ItemStack){
        return true;
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
        //worldTime=par2World.getWorldTime();
        par1ItemStack.damageItem(1, par3EntityPlayer);
        par3EntityPlayer.getFoodStats().func_151686_a(this, par1ItemStack);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        if(!par2World.isRemote/* && par2World.rand.nextFloat() < 0.1F*/){
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 30 * 20, -4));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 30 * 20, 0));
        }
        /*par3EntityPlayer.setAbsorptionAmount(par3EntityPlayer.getAbsorptionAmount() + (float)(4 * (-50 + 1)));*/
        return par1ItemStack;
    }

    /*public boolean canEatThis(World world,int par2){
        if(!(worldTime<world.getWorldTime()+par2)){
            return true;
        }else {
            return false;
        }
    }*/
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase){
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_){
        if((double) p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D){
            p_150894_1_.damageItem(2, p_150894_7_);
        }

        return true;
    }

    public int getItemEnchantability(){
        return 10;
    }

    @SuppressWarnings("unchecked")
    public Multimap getAttributeModifiers(ItemStack stack){
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double) this.damageVsEntity, 0));
        return multimap;
    }
}
