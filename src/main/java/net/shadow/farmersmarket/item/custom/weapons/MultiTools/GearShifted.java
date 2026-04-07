package net.shadow.farmersmarket.item.custom.weapons.MultiTools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.SweepingBase.SweeplessSword;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

import java.util.Map;
import java.util.stream.Collectors;

public class GearShifted extends SweeplessSword {

    private static final int COOLDOWN_TICKS = 40;
    public GearShifted(Settings settings) {
        super(WeaponMaterials.GEARSHIFT, 2, -2.4F, settings);
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
            return UseAction.NONE;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        WeaponChargeComponent.UseFLASH();
        {
            user.getItemCooldownManager().set(this, (COOLDOWN_TICKS - (5*WeaponChargeComponent.FLASHCHAIN)));
        }
        if(user.isSneaking()){
            user.setCurrentHand(hand); // Begin charging
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        ItemStack newstack = new ItemStack(ModItems.GEARSHIFT);
        if(stack.hasEnchantments()) {
            Map<Enchantment, Integer> map =  EnchantmentHelper.get(stack).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            EnchantmentHelper.set(map, newstack);



            stack.decrement(1);
            newstack.setDamage(stack.getDamage());
            return newstack;

        }
        return newstack;
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        if(WeaponChargeComponent.BLACKFLASH>0) return true;
        return super.isItemBarVisible(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        if(WeaponChargeComponent.BLACKFLASH>0) {
            return Math.round((float) WeaponChargeComponent.BLACKFLASH / WeaponChargeComponent.BLACKFLASH_TIMER_MAX * 13); // full bar = max charge
        }
        return super.getItemBarStep(stack);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills

        if(WeaponChargeComponent.BLACKFLASH>0) {

            int red = (int) (220);
            int blue = (int) (30);
            int green = (int) (170);
            return (red << 16) | (green << 8) | blue; // RGB mix
        }
        return super.getItemBarColor(stack);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(WeaponChargeComponent.BLACKFLASH>0){
            this.playInsertSound(attacker);
            this.spawnParticals(target);
        }
        return super.postHit(stack, target, attacker);
    }
    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1F + (1.5f * WeaponChargeComponent.FLASHCHAIN), 1);
    }
    private void spawnParticals(LivingEntity entity){
        World world = entity.getWorld();
        if(!(world instanceof ServerWorld)) return;
        Vec3d pos = entity.getPos();
        ((ServerWorld) world).spawnParticles(ParticleTypes.DRAGON_BREATH, pos.x, pos.y+2, pos.z, 5, world.random.nextDouble(), world.random.nextDouble(), world.random.nextDouble(), 0);
    }
}
