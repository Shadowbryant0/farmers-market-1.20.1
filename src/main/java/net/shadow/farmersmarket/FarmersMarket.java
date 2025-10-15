package net.shadow.farmersmarket;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
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

	}
}
//written by willow rose, much thanks