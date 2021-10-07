package samebutdifferent.trickortreat.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ClimbingEffect extends MobEffect {
    public ClimbingEffect() {
        super(MobEffectCategory.BENEFICIAL, 3801088);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.horizontalCollision) {
            Vec3 vec3 = entity.getDeltaMovement();
            Vec3 movement = new Vec3(vec3.x, 0.2D, vec3.z);
            entity.setDeltaMovement(movement.x * 0.91D, movement.y * 0.98D, movement.z * 0.91D);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
