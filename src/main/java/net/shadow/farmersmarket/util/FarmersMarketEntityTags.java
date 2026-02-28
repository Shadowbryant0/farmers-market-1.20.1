package net.shadow.farmersmarket.util;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FarmersMarketEntityTags {


    public static final TagKey<EntityType<?>> ZOMBIES = of("farmersmarket:zombies");

    private FarmersMarketEntityTags() {
    }

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(id));
    }

}
