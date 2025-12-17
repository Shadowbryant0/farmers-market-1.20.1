package net.shadow.farmersmarket.item.custom.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.util.FarmersmarketUtil;

import java.util.UUID;

import static net.minecraft.item.ToolMaterials.IRON;

public class FarmerScytheItem extends FarmersScytheClass{
    public FarmerScytheItem(Item.Settings settings){
        super(IRON, 3, -2.4F, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2.4, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 3, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_REACH_MODIFIER_ID, "Weapon modifier", (double)-0.2F, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon modifier", (double)1F, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    protected static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("76a8dee3-3e7e-4e11-ba46-a19b0c724567");
    protected static final UUID REACH_MODIFIER_ID = UUID.fromString("a31c8afc-a716-425d-89cd-0d373380e6e7");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        return super.postHit(stack, target, attacker);
    }
}

