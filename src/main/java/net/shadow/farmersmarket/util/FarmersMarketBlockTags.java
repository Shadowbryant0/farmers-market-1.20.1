package net.shadow.farmersmarket.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FarmersMarketBlockTags {


    public static final TagKey<Block> SHADOW_REALM_BLOCKS = of("farmersmarket:shadow_block_tag_1");

    private FarmersMarketBlockTags() {
    }

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(id));
    }

}
