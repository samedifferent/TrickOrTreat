package samebutdifferent.trickortreat.registry;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class ModEvents implements RegistryClass {
    @Override
    public void register(String modId) {
        AttackEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
            if (player.hasStatusEffect(ModEffects.BONE_BREAKING)) {
                if (entity instanceof LivingEntity target && !target.hasStatusEffect(ModEffects.PARALYZED)) {
                    target.addStatusEffect(new StatusEffectInstance(ModEffects.PARALYZED, 60, 4));
                    if (!(target instanceof AbstractSkeletonEntity)) {
                        player.playSound(SoundEvents.ENTITY_SKELETON_HURT, 1.0F, 1.0F);
                    }
                }
            }

            if (player.hasStatusEffect(ModEffects.FIREFINGER)) {
                entity.setOnFireFor(4);
            }

            if (player.hasStatusEffect(ModEffects.ROTTEN_BITE)) {
                if (entity instanceof VillagerEntity villager) {
                    if (!villager.world.isClient) {
                        ServerWorld level = (ServerWorld) villager.world;
                        ZombieVillagerEntity zombieVillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
                        zombieVillager.initialize(level, level.getLocalDifficulty(zombieVillager.getBlockPos()), SpawnReason.CONVERSION, new ZombieEntity.ZombieData(false, true), null);
                        zombieVillager.setVillagerData(villager.getVillagerData());
                        zombieVillager.setGossipData(villager.getGossip().serialize(NbtOps.INSTANCE).getValue());
                        zombieVillager.setOfferData(villager.getOffers().toNbt());
                        zombieVillager.setXp(villager.getExperience());
                        player.world.playSound(null, zombieVillager.getX(), zombieVillager.getY(), zombieVillager.getZ(), SoundEvents.ENTITY_ZOMBIE_VILLAGER_HURT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                    }
                }
            }

            return ActionResult.PASS;
        }));
    }
}
