package net.shadow.farmersmarket.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface BlockStateLambda {
    default int setSummonerUuid(BlockState state, int light){
        return light;
    }
}