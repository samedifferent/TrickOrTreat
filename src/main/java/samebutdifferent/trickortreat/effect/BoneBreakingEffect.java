package samebutdifferent.trickortreat.effect;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class BoneBreakingEffect extends MobEffect {
    public BoneBreakingEffect() {
        super(MobEffectCategory.BENEFICIAL, 16777203);
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        Player player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(ModEffects.BONE_BREAKING.get())) {
            if (target instanceof LivingEntity entity && !entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                entity.addEffect(new MobEffectInstance(ModEffects.IMMOBILIZED.get(), 60, 4));
                if (!(entity instanceof AbstractSkeleton)) {
                    player.playSound(SoundEvents.SKELETON_HURT, 1.0F, 1.0F);
                }
            }
        }
    }
}
