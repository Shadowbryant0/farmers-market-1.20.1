package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.shadow.farmersmarket.item.materials.TyrentMat;

public class TyrentsHammerClass extends MiningToolItem {


    public TyrentsHammerClass(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(attackDamage, attackSpeed, TyrentMat.INSTANCE, BlockTags.PICKAXE_MINEABLE, settings);
    }
}
