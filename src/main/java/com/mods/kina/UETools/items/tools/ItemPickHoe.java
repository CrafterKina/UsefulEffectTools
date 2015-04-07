package com.mods.kina.UETools.items.tools;

import com.mods.kina.UETools.registry.UEFieldsDeclaration;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemPickHoe extends ItemHoe{
    public ItemPickHoe(ToolMaterial material){
        super(material);
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("itemPickHoe");
        setTextureName("kina:pick_hoe");
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
        if(!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)){
            return false;
        }else{
            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
            if(MinecraftForge.EVENT_BUS.post(event)){
                return false;
            }

            if(event.getResult() == Event.Result.ALLOW){
                par1ItemStack.damageItem(1, par2EntityPlayer);
                return true;
            }

            Block block = par3World.getBlock(par4, par5, par6);

            if(par7 != 0 && par3World.getBlock(par4, par5 + 1, par6).isAir(par3World, par4, par5 + 1, par6) && (block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium)){
                Block block1 = Blocks.farmland;
                par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                if(par3World.isRemote){
                    return true;
                }else{
                    if(block == Blocks.mycelium || block == Blocks.grass){
                        ItemStack itemStack;
                        itemStack = null;
                        if(block == Blocks.grass){
                            itemStack = new ItemStack(UEFieldsDeclaration.itemGrassPart);
                        }else if(block == Blocks.mycelium){
                            itemStack = new ItemStack(UEFieldsDeclaration.itemMycelPart);
                        }
                        if(!par3World.isRemote && par3World.getGameRules().getGameRuleBooleanValue("doTileDrops")){
                            float f = 0.7F;
                            double d0 = (double) (par3World.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                            double d1 = (double) (par3World.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                            double d2 = (double) (par3World.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                            EntityItem entityitem = new EntityItem(par3World, (double) par4 + d0, (double) par5 + d1, (double) par6 + d2, itemStack);
                            entityitem.delayBeforeCanPickup = 10;
                            par3World.spawnEntityInWorld(entityitem);
                        }
                    }
                    par3World.setBlock(par4, par5, par6, block1);
                    par1ItemStack.damageItem(1, par2EntityPlayer);
                    return true;
                }
            }else{
                return false;
            }
        }
    }
}
