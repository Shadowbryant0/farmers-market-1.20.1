package net.shadow.farmersmarket;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FarmersMarket implements ModInitializer {
	public static final String MOD_ID = "farmersmarket";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello NERDS! <3");
	}
}