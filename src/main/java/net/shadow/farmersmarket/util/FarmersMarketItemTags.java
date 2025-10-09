package net.shadow.farmersmarket.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FarmersMarketItemTags {


    public static final TagKey<Item> BRACE_EXCEPTION_1 = of("farmersmarket:bracing_ignore_anime");

    private FarmersMarketItemTags() {
    }

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(id));
    }

}
