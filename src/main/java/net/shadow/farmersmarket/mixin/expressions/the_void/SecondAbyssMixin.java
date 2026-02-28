package net.shadow.farmersmarket.mixin.expressions.the_void;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.expressions.divinity.Firstdivinity_flight;
import net.shadow.farmersmarket.entity.SecondAbyss;
import net.shadow.farmersmarket.entity.ai.FollowSummonerGoal;
import net.shadow.farmersmarket.entity.ai.SummonerTargetGoal;
import net.shadow.farmersmarket.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

@Mixin(MobEntity.class)
    public abstract class SecondAbyssMixin extends LivingEntity implements SecondAbyss {

        @Unique
        private UUID summonerUuid;

        protected SecondAbyssMixin(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public @Nullable UUID getSummonerUuid() {
            return this.summonerUuid;
        }

        @Override
        public void setSummonerUuid(@Nullable UUID uuid) {
            this.summonerUuid = uuid;
            if (uuid != null && !this.getWorld().isClient) {
                this.addSummonerGoals();
            }
        }

        @Override
        public @Nullable LivingEntity getSummoner() {
            if (this.summonerUuid == null) {
                return null;
            }
            return this.getWorld().getPlayerByUuid(this.summonerUuid);
        }
    @Inject(method = "dropLoot", at = @At("HEAD"), cancellable = true)
    private void lootDrop(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        if (this.summonerUuid != null) {
            ci.cancel();
        }
    }
        @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
        private void writeSummonerData(NbtCompound nbt, CallbackInfo ci) {
            if (this.summonerUuid != null) {
                nbt.putUuid("SummonerOwner", this.summonerUuid);
            }
        }

        @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
        private void readSummonerData(NbtCompound nbt, CallbackInfo ci) {
            if (nbt.contains("SummonerOwner")) {
                this.summonerUuid = nbt.getUuid("SummonerOwner");
                // Re-initialize goals if we just loaded the UUID and the world is not client
                if (!this.getWorld().isClient) {
                    this.addSummonerGoals();
                }
            }
        }

        // Inject at return of initGoals to create goals
        // Note: initGoals is empty in MobEntity but subclasses call super.initGoals()
        // or override it.
        // However, mixing there might be too early if we don't have UUID yet.
        // Instead, we will manually add goals when UUID is set or read.

        @Shadow
        protected net.minecraft.entity.ai.goal.GoalSelector goalSelector;
        @Shadow
        protected net.minecraft.entity.ai.goal.GoalSelector targetSelector;

        @Unique
        private void addSummonerGoals() {
            MobEntity mob = (MobEntity) (Object) this;
            // Add priority goals (higher priority than average wandering)
            // Priority 2 for following (below swimmimg/panic)
            this.goalSelector.add(2, new FollowSummonerGoal(mob, 1.2, 10.0F, 2.0F));
            // Add target goal
            this.targetSelector.add(1, new SummonerTargetGoal(mob));
            if (mob instanceof net.minecraft.entity.mob.PathAwareEntity pathAware) {
                this.targetSelector.add(2, new net.minecraft.entity.ai.goal.RevengeGoal(pathAware));
            }
        }

        @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
        private void animeWitchery$preventTargetingOwner(LivingEntity target, CallbackInfo ci) {
            if (target != null && this.summonerUuid != null && target.getUuid().equals(this.summonerUuid)) {
                ci.cancel();
            }
        }
    }