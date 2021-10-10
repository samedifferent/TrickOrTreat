package samebutdifferent.trickortreat.effect;

import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class RottenBiteEffect extends MobEffect {
    public RottenBiteEffect() {
        super(MobEffectCategory.BENEFICIAL, 13762304);
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        Player player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(ModEffects.ROTTEN_BITE.get())) {
            if (target instanceof Villager villager) {
                if (!villager.level.isClientSide) {
                    ServerLevel level = (ServerLevel) villager.level;
                    ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
                    zombievillager.finalizeSpawn(level, level.getCurrentDifficultyAt(zombievillager.blockPosition()), MobSpawnType.CONVERSION, new Zombie.ZombieGroupData(false, true), null);
                    zombievillager.setVillagerData(villager.getVillagerData());
                    zombievillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE).getValue());
                    zombievillager.setTradeOffers(villager.getOffers().createTag());
                    zombievillager.setVillagerXp(villager.getVillagerXp());
                    player.level.playSound(null, zombievillager.getX(), zombievillager.getY(), zombievillager.getZ(), SoundEvents.ZOMBIE_VILLAGER_HURT, SoundSource.HOSTILE, 1.0F, 1.0F);
                }
            }
        }
    }
}
