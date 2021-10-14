package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class ClimbingEffect extends StatusEffect {
    protected int ambientSoundTime;

    public ClimbingEffect() {
        super(StatusEffectCategory.BENEFICIAL, 3801088);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.horizontalCollision) {
            Vec3d initialVec = entity.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, 0.2D, initialVec.z);
            entity.setVelocity(climbVec.x * 0.91D, climbVec.y * 0.98D, climbVec.z * 0.91D);
        }
        if (entity.getRandom().nextInt(1000) < this.ambientSoundTime++) {
            this.ambientSoundTime = -40;
            entity.playSound(SoundEvents.ENTITY_SPIDER_AMBIENT, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
