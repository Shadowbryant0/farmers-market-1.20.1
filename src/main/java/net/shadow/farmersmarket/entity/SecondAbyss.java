package net.shadow.farmersmarket.entity;

import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface SecondAbyss {
    @Nullable
    UUID getSummonerUuid();

    void setSummonerUuid(@Nullable UUID uuid);
    LivingEntity isSummoned();
    @Nullable
    LivingEntity getSummoner();
}