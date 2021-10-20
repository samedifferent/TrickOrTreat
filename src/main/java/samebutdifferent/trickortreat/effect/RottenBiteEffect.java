package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class RottenBiteEffect extends Effect {
    public RottenBiteEffect() {
        super(EffectType.BENEFICIAL, 13762304);
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(ModEffects.ROTTEN_BITE.get())) {
            if (target instanceof VillagerEntity) {
                VillagerEntity villager = (VillagerEntity) target;
                if (!villager.level.isClientSide) {
                    ServerWorld level = (ServerWorld) villager.level;
                    ZombieVillagerEntity zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
                    zombievillager.finalizeSpawn(level, level.getCurrentDifficultyAt(zombievillager.blockPosition()), SpawnReason.CONVERSION, new ZombieEntity.GroupData(false, true), null);
                    zombievillager.setVillagerData(villager.getVillagerData());
                    zombievillager.setGossips(villager.getGossips().store(NBTDynamicOps.INSTANCE).getValue());
                    zombievillager.setTradeOffers(villager.getOffers().createTag());
                    zombievillager.setVillagerXp(villager.getVillagerXp());
                    player.level.playSound(null, zombievillager.getX(), zombievillager.getY(), zombievillager.getZ(), SoundEvents.ZOMBIE_VILLAGER_HURT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                }
            }
        }
    }
}
