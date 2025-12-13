package net.shadow.farmersmarket.item.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.shadow.farmersmarket.item.components.Weapons.*;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<BowChargeComponent> BOW_CHARGE = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "charge"), BowChargeComponent.class);
    public static final ComponentKey<ParryComponent> PARRY = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "parry"), ParryComponent.class);
    public static final ComponentKey<TeleportRandomlyComponent> ENDERMAN = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "enderman"), TeleportRandomlyComponent.class);
    public static final ComponentKey<RapierChargeComponent> RAPIER = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "rapier"), RapierChargeComponent.class);
    public static final ComponentKey<GreatSwordChargeComponent> GREAT = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "great"), GreatSwordChargeComponent.class);
    public static final ComponentKey<BloodHoundChargeComponent> BLOOD = ComponentRegistry.getOrCreate(Identifier.of("farmersmarket", "blood"), BloodHoundChargeComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(PlayerEntity.class, BOW_CHARGE, player ->  new BowChargeComponent(player));
        registry.registerFor(PlayerEntity.class, PARRY, player ->  new ParryComponent(player));
        registry.registerFor(PlayerEntity.class, ENDERMAN, player ->  new TeleportRandomlyComponent(player));
        registry.registerFor(PlayerEntity.class, RAPIER, player ->  new RapierChargeComponent(player));
        registry.registerFor(PlayerEntity.class, GREAT, player ->  new GreatSwordChargeComponent(player));
        registry.registerFor(PlayerEntity.class, BLOOD, player ->  new BloodHoundChargeComponent(player));
    }
}
