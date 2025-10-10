package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import static net.minecraft.item.ToolMaterials.IRON;

public class FarmerScytheItem extends FarmersScytheClass{
    public FarmerScytheItem(Item.Settings settings){super(IRON, 3, -2.4F, settings);}

    final float SWEEP_DAMAGE = 4;
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        FarmersmarketUtil.sweepingEdge(target, attacker, SWEEP_DAMAGE, true);
        return super.postHit(stack, target, attacker);
    }
}

