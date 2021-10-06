package samebutdifferent.trickortreat.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ClimbingEffect extends MobEffect {
    public ClimbingEffect() {
        super(MobEffectCategory.BENEFICIAL, 3801088);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
