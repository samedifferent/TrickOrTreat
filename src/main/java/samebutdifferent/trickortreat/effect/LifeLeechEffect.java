package samebutdifferent.trickortreat.effect;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class LifeLeechEffect extends MobEffect {
    public LifeLeechEffect() {
        super(MobEffectCategory.BENEFICIAL, 16466895);
    }

    @SubscribeEvent
    static void onDamage(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player && player.hasEffect(ModEffects.LIFE_LEECH.get())) {
            float damageAmount = event.getAmount();
            float healthStealAmount = Math.min(6, damageAmount / 4);
            if (healthStealAmount >= 1) {
                player.playSound(SoundEvents.PHANTOM_AMBIENT, 1.0F, 1.0F);
                player.heal(healthStealAmount);
            }
        }
    }
}
