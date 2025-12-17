package net.shadow.farmersmarket;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.block.ModBlocks;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.event.ForgingEnchantEvent;
import net.shadow.farmersmarket.event.ScytheHarvestEvent;
import net.shadow.farmersmarket.item.ModItemGroups;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.sound.ModSounds;
import net.shadow.farmersmarket.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FarmersMarket implements ModInitializer {
	public static final String MOD_ID = "farmersmarket";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String MOD_ID2 = "farmersmarket";

    public static final EntityAttribute ENDERMAN = make("enderman", 0.0, 0, 4);
    public static final EntityAttribute BLAZE = make("blaze", 0.0, 0, 4);
    public static final EntityAttribute PHANTOM = make("phantom", 0.0, 0, 4);


    private static EntityAttribute make(final String name, final double base, final double min, final double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID2 + '.' + name, base, min, max).setTracked(true);
    }
	@Override
	public void onInitialize() {

		LOGGER.info("Hello NERDS! <3");
		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		//ModEffects.registerEffects();
		ScytheHarvestEvent.register();
		MidnightConfig.init("farmersmarket", ModConfigs.class);
		ForgingEnchantEvent.register();
		FarmersMarketEnchants.registerModEnchantments();
		FuelRegistry.INSTANCE.add(ModItems.FIRE_STARTER, 7200);

        Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID2, "enderman"), ENDERMAN);
        Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID2, "blaze"), BLAZE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(MOD_ID2, "phantom"), PHANTOM);

	}
}
//written by willow rose, much thanks