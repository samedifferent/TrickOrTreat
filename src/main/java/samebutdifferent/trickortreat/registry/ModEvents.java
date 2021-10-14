package samebutdifferent.trickortreat.registry;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import samebutdifferent.trickortreat.mixin.MobEntityAccessor;

public class ModEvents implements RegistryClass {
    //TODO: Port this to a mixin!
    /*@SubscribeEvent
    static void onMobDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;
        if (TrickOrTreat.isHalloween() || !ModConfig.ONLY_HALLOWEEN.get()) {
            if (!event.getSource().isFall() && !event.getSource().isFire() && !event.getSource().isMagic()) {
                if (level.random.nextFloat() <= 0.25F * ModConfig.GOODIE_BAG_DROP_CHANCE.get().floatValue()) {
                    if (entity instanceof Blaze)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.BLAZE_GOODIE_BAG.get())));
                    if (entity instanceof Creeper)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.CREEPER_GOODIE_BAG.get())));
                    if (entity instanceof Drowned)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.DROWNED_GOODIE_BAG.get())));
                    if (entity instanceof EnderMan)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.ENDERMAN_GOODIE_BAG.get())));
                    if (entity instanceof Ghast)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.GHAST_GOODIE_BAG.get())));
                    if (entity instanceof Guardian)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.GUARDIAN_GOODIE_BAG.get())));
                    if (entity instanceof Phantom)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.PHANTOM_GOODIE_BAG.get())));
                    if (entity instanceof Skeleton)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SKELETON_GOODIE_BAG.get())));
                    if (entity instanceof Slime)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SLIME_GOODIE_BAG.get())));
                    if (entity instanceof Spider)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SPIDER_GOODIE_BAG.get())));
                    if (entity instanceof Zombie)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.ZOMBIE_GOODIE_BAG.get())));
                }
            }
        }
    }*/

    @SuppressWarnings("ReferenceToMixin")
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

        ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
            if (entity instanceof Monster) {
                FleeEntityGoal<PlayerEntity> goal = new FleeEntityGoal<>((PathAwareEntity) entity, PlayerEntity.class, (livingEntity) -> livingEntity.hasStatusEffect(ModEffects.SCARY), 8.0F, 1.8D, 1.8D, (livingEntity -> true));
                ((MobEntityAccessor) entity).getGoalSelector().add(1, goal);
            }
        }));
    }
}
