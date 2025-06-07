package net.shadow.farmersmarket;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.shadow.farmersmarket.block.ModBlocks;
import net.shadow.farmersmarket.effects.ModEffects;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.event.FarmersMarketEvents;
import net.shadow.farmersmarket.item.ModItemGroups;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FarmersMarket implements ModInitializer {
	public static final String MOD_ID = "farmersmarket";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello NERDS! <3");
		ModItems.registerModItems();

		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		//ModEffects.registerEffects();



		FarmersMarketEnchants.registerModEnchantments();

	}
}