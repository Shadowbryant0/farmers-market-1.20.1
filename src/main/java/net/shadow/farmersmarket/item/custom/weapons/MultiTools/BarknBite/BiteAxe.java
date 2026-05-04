package net.shadow.farmersmarket.item.custom.weapons.MultiTools.BarknBite;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.entity.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

public class BiteAxe extends AxeItem {

    public static boolean CHOP;

    private static boolean CHOPTOGG;
    public BiteAxe(Settings settings) {
        super(WeaponMaterials.BARKNBITE, 4, -3F, settings);
        CHOP = false;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if(user.isSneaking()){
            user.setCurrentHand(hand); // Begin charging
            CHOPTOGG = false;
        }
        if(!CHOPTOGG && !user.isSneaking()) {
            CHOPTOGG = true;
            CHOP = !CHOP;

            stack.getOrCreateNbt().putBoolean("chop", CHOP);
            user.sendMessage(Text.literal("Chop mode:" + CHOP), true);
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

    }
}
