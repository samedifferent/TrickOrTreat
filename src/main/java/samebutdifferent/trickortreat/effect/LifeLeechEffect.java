package samebutdifferent.trickortreat.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
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
        DamageSource source = event.getSource();
        if (event.getEntityLiving() instanceof Player player && player.hasEffect(ModEffects.LIFE_LEECH.get())) {
            if (source instanceof EntityDamageSource && !(source instanceof IndirectEntityDamageSource) && !((EntityDamageSource) source).isThorns()) {
                float damageAmount = Math.min(event.getAmount(), player.getHealth());
                float healthStealAmount = Math.min(6, damageAmount / 4);
                if (healthStealAmount >= 1) {
                    player.heal(healthStealAmount);
                }
            }
        }
    }
}
