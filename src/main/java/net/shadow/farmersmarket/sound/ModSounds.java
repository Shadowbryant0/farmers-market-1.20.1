package net.shadow.farmersmarket.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;

public class ModSounds {

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(FarmersMarket.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        FarmersMarket.LOGGER.info("registering sounds for " + FarmersMarket.MOD_ID);
    }
}
