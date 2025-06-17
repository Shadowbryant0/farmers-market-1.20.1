package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CrackedTotem  extends Item {
    public CrackedTotem(Settings settings) {
        super(settings);
    }
    private static final int COOLDOWN_TICKS = 600;

    //hi dinooo >:3
        //you thought you would find smth here
        //haha, get FOOLED
        // <3 thanks for the hard work of datamining my mod
        //STAY OUT OF MY RECIPE FOLDER THOSE ARE PRIVATE!!!
        //luna can check the not cosmetic stuff
        //just stay out of corrupted flesh, veinpiercer, mainsword
    //THAT WAS PRE CODE LMAO THERE ARE THINGS NOW

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (user.getHealth() <=6) {
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            user.removeStatusEffectInternal(StatusEffects.POISON);
            user.removeStatusEffectInternal(StatusEffects.WEAKNESS);
            user.removeStatusEffectInternal(StatusEffects.DARKNESS);
            user.removeStatusEffectInternal(StatusEffects.SLOWNESS);
            user.removeStatusEffectInternal(StatusEffects.BLINDNESS);
            user.removeStatusEffectInternal(StatusEffects.LEVITATION);
            user.removeStatusEffectInternal(StatusEffects.WITHER);
            user.removeStatusEffectInternal(StatusEffects.GLOWING);
            user.removeStatusEffectInternal(StatusEffects.NAUSEA);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 160, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 280, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2, 0));
            {
                user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            }

            return TypedActionResult.consume(itemStack);


        }
        return TypedActionResult.fail(itemStack);
    }

}
