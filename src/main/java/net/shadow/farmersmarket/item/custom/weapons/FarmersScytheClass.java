package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;

public class FarmersScytheClass  extends MiningToolItem {


    public FarmersScytheClass(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(attackDamage, attackSpeed, material,BlockTags.CROPS, settings);
    }


}


