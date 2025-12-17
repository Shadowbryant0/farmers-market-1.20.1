package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.ModConfigs;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.*;
import net.shadow.farmersmarket.util.FarmersMarketItemTags;

public class CSbook extends BookItem {


    public CSbook(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            ItemStack itemStack = slot.getStack();

            if (itemStack.getItem() instanceof ArmorItem) {

                    if (ModConfigs.calciumenabled) {
                        if (EnchantmentHelper.getLevel(Enchantments.PROTECTION, itemStack) == 0 && EnchantmentHelper.getLevel(FarmersMarketEnchants.CalciumInfused, itemStack) == 0) {
                            this.playInsertSound(player);
                            itemStack.addEnchantment(FarmersMarketEnchants.CalciumInfused, 1);
                            stack.decrement(1);
                            player.damage(player.getDamageSources().wither(), 10);
                        }
                    }

            } else if (itemStack.isEmpty()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 6000, 1));
                player.damage(player.getDamageSources().wither(), 14);
                stack.decrement(1);
            } else if (itemStack.isOf(ModItems.CSBOOK)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 12000, 3));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2000, 0));
                player.damage(player.getDamageSources().wither(), 19);
                stack.decrement(1);
                itemStack.decrement(1);
            }else if (itemStack.isOf(ModItems.HEXSPADE)) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Syphon, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Syphon, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }

            }else if (itemStack.getItem() instanceof RapierWeaponItem) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Riposte, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Riposte, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }

            }else if (itemStack.getItem() instanceof GreatswordItem) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Shout, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Shout, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }


            }else if (itemStack.getItem() instanceof ExecutionersAxeItem) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Inferno, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Inferno, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }


            }else if (itemStack.getItem() instanceof BeardedAxeItem) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Sharpen, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Sharpen, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }


            }else if (itemStack.getItem() instanceof RustedSickleItem) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Rusted, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Rusted, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }


            }else if (itemStack.getItem() instanceof ElytraItem || itemStack.isIn(FarmersMarketItemTags.BRACE_EXCEPTION_1)) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.Bracing, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.Bracing, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }


            }else if (itemStack.getItem() instanceof WyrmSpearItem) {
                if(EnchantmentHelper.getLevel(FarmersMarketEnchants.WyrmStride, itemStack) == 0) {
                    itemStack.addEnchantment(FarmersMarketEnchants.WyrmStride, 1);
                    player.damage(player.getDamageSources().wither(), 10);
                    stack.decrement(1);
                }


            }


            return true;
        }
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}

