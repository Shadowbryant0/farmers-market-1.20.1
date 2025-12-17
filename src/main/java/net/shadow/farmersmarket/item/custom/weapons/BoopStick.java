package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.shadow.farmersmarket.item.materials.ExcalatrowlMats;

public class BoopStick extends AxeItem {
    public BoopStick(Settings settings) {
        super(ExcalatrowlMats.INSTANCE, 3, -2.5F, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.decrement(1);
        target.kill();
        return super.postHit(stack, target, attacker);
    }
}
