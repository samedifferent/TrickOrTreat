package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class LifeLeechEffect extends Effect {
    public LifeLeechEffect() {
        super(EffectType.BENEFICIAL, 16466895);
    }

    @SubscribeEvent
    static void onDamage(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (player.hasEffect(ModEffects.LIFE_LEECH.get())) {
                float damageAmount = event.getAmount();
                float healthStealAmount = Math.min(6, damageAmount / 4);
                if (healthStealAmount >= 1) {
                    event.getEntityLiving().playSound(SoundEvents.PHANTOM_BITE, 1.0F, 1.0F);
                    player.heal(healthStealAmount);
                }
            }
        }
    }
}
