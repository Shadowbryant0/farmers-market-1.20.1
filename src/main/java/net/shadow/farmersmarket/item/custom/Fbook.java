package net.shadow.farmersmarket.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.security.PublicKey;
import java.util.List;

public class Fbook extends BookItem {


    public Fbook(Settings settings) {
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
            if (itemStack.getItem() instanceof AxeItem) {

                    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.PrimalDesires, itemStack) == 0) {
                        this.playInsertSound(player);
                        int b = player.getRandom().nextInt(2);
                        if (b == 1) {
                            itemStack.addEnchantment(FarmersMarketEnchants.Starvation, 1);
                        }
                        if (b == 0){
                            itemStack.addEnchantment(FarmersMarketEnchants.JagerderSchuldigen, 1);
                        }

                        stack.decrement(1);
                        player.damage(player.getDamageSources().wither(),6);
                    }

            }
            if (itemStack.getItem() instanceof SwordItem) {

                    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, itemStack) == 0) {
                        this.playInsertSound(player);
                        itemStack.addEnchantment(FarmersMarketEnchants.Devouring, 1);
                        stack.decrement(1);
                        player.damage(player.getDamageSources().wither(),6);
                    }
                }


            return true;
        }
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}

