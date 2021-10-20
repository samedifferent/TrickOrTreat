package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class BoneBreakingEffect extends Effect {
    public BoneBreakingEffect() {
        super(EffectType.BENEFICIAL, 16777203);
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(ModEffects.BONE_BREAKING.get())) {
            if (target instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity)target;
                if (!entity.hasEffect(ModEffects.PARALYZED.get())) {
                    entity.addEffect(new EffectInstance(ModEffects.PARALYZED.get(), 60, 4));
                    if (!(entity instanceof AbstractSkeletonEntity)) {
                        player.playSound(SoundEvents.SKELETON_HURT, 1.0F, 1.0F);
                    }
                }
            }
        }
    }
}
