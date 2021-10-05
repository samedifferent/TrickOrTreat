package samebutdifferent.trickortreat.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class BouncyEffect extends MobEffect {
    public BouncyEffect() {
        super(MobEffectCategory.BENEFICIAL, 3140701);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isOnGround()) {
            Vec3 movement = entity.getDeltaMovement();
            entity.setDeltaMovement(movement.x, 1.5D, movement.z);
            entity.hasImpulse = true;
        }
        entity.fallDistance = 0.0F;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
