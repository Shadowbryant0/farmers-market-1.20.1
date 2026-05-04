package net.shadow.farmersmarket.item.custom.weapons.MultiTools.BarknBite;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

public class BarkPickaxe extends PickaxeItem {
    public BarkPickaxe(Settings settings) {
        super(WeaponMaterials.BARKNBITE, 1, -2.7F, settings);
    }
}
