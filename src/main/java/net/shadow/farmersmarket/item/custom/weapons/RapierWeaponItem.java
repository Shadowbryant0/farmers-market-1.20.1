package net.shadow.farmersmarket.item.custom.weapons;


import com.google.common.collect.ImmutableMultimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.materials.RapierMat;
import com.google.common.collect.Multimap;


import java.util.UUID;

public class RapierWeaponItem extends SwordItem {
    private static final String CHARGE_KEY = "charge";
    private static final int MAX_CHARGE = 100;
    private static final int HALF_CHARGE = 50;
    double boost = 1.5;
    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public RapierWeaponItem(Settings settings) {
        super(RapierMat.INSTANCE, 6, -2.2F, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2.2, Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 6, Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)1F, Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", (double)-0.5F, Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {
            Vec3d lookingDirection = user.getRotationVec(1.0f);
            ItemStack stack = user.getStackInHand(hand);
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Riposte, stack) == 0) {
                if (getCharge(stack) >= MAX_CHARGE) {
                    stack.getOrCreateNbt().putInt(CHARGE_KEY, 0);
                    user.setVelocity(
                            lookingDirection.x * boost,
                            lookingDirection.y * boost * 0.6f,
                            lookingDirection.z * boost
                    );
                    user.velocityModified = true;
                    user.setSwimming(false);
                }
                return super.use(world, user, hand);
            } else {
                    if (getCharge(stack) >= HALF_CHARGE) {
                        int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
                        int dash = (int) (50);
                        charge = Math.min(charge - dash, MAX_CHARGE);
                        stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);
                        user.setVelocity(
                                   (lookingDirection.x * boost *.75),
                                (lookingDirection.y * boost *.75) * 0.6f,
                                   (lookingDirection.z * boost *.75)
                        );
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 1));
                        user.velocityModified = true;
                        user.setSwimming(false);
                        return super.use(world, user, hand);


                }
                return super.use(world, user, hand);

            }
        }
        return super.use(world, user, hand);
    }


    public float getAttackDamage() {
        return 6F;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!attacker.getWorld().isClient) {
            int charge = stack.getOrCreateNbt().getInt(CHARGE_KEY);
            int gain = (int) (15);
            charge = Math.min(charge + gain, MAX_CHARGE);
            stack.getOrCreateNbt().putInt(CHARGE_KEY, charge);

        }
        return super.postHit(stack, target, attacker);
    }

    public com.google.common.collect.Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getCharge(stack) > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        int charge = getCharge(stack);
        return Math.round((float) charge / MAX_CHARGE * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        float ratio = (float) getCharge(stack) / MAX_CHARGE;
        int red = (int) (200);
        int blue = (int) (200);
        int green = (int) (200);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(CHARGE_KEY);
    }

}