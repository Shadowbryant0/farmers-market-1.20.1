package net.shadow.farmersmarket.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.components.Armor.AdaptabilityComponent;
import net.shadow.farmersmarket.components.Weapons.*;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<BowChargeComponent> BOW_CHARGE = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "arrows"), BowChargeComponent.class);
    public static final ComponentKey<ParryComponent> PARRY = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "parry"), ParryComponent.class);
    public static final ComponentKey<TeleportRandomlyComponent> ENDERMAN = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "enderman"), TeleportRandomlyComponent.class);
    public static final ComponentKey<BlazeIdolComponent> BLAZE = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "blaze"), BlazeIdolComponent.class);
    public static final ComponentKey<RainDamageComponent> DROWN = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "drown"), RainDamageComponent.class);
    public static final ComponentKey<AdaptabilityComponent> ADAPT = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "adapt"), AdaptabilityComponent.class);
    public static final ComponentKey<WeaponChargeComponent> CHARGE = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "charge"), WeaponChargeComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(PlayerEntity.class, BOW_CHARGE, player ->  new BowChargeComponent(player));
        registry.registerFor(PlayerEntity.class, PARRY, player ->  new ParryComponent(player));
        registry.registerFor(PlayerEntity.class, ENDERMAN, player ->  new TeleportRandomlyComponent(player));
        registry.registerFor(PlayerEntity.class, BLAZE, player ->  new BlazeIdolComponent(player));
        registry.registerFor(PlayerEntity.class, DROWN, player ->  new RainDamageComponent(player));
        registry.registerFor(PlayerEntity.class, ADAPT, player ->  new AdaptabilityComponent(player));
        registry.registerFor(PlayerEntity.class, CHARGE, player ->  new WeaponChargeComponent(player));
    }
}
