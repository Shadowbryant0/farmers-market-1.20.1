package net.shadow.farmersmarket.util;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public interface FarmersMarketDamageTagsCustom {

    TagKey<DamageType> KINETIC = of("farmersmarket:is_kinetic");

    private static TagKey<DamageType> of(String id) {
        return TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(id));
    }

}
