package net.shadow.farmersmarket.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.components.world.BiteChopComponent;

public class ModWorldComponents implements WorldComponentInitializer{
        public static final ComponentKey<BiteChopComponent> LUMBERJACK = ComponentRegistry.getOrCreate(new Identifier(FarmersMarket.MOD_ID, "bitechop"), BiteChopComponent.class);

        @Override
        public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
            registry.register(LUMBERJACK, BiteChopComponent::new);
        }
}
