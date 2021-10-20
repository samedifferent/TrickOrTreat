package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class BouncyEffect extends Effect {
    public BouncyEffect() {
        super(EffectType.BENEFICIAL, 3140701);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isOnGround()) {
            Vector3d movement = entity.getDeltaMovement();
            entity.setDeltaMovement(movement.x, 1.5D, movement.z);
            entity.hasImpulse = true;
            entity.playSound(SoundEvents.SLIME_JUMP, 1.0F, 1.0F);
            for(int j = 0; j < 8; ++j) {
                float f = entity.level.random.nextFloat() * ((float)Math.PI * 2F);
                float f1 = entity.level.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * 0.5F * f1;
                float f3 = MathHelper.cos(f) * 0.5F * f1;
                entity.level.addParticle(ParticleTypes.ITEM_SLIME, entity.getX() + (double)f2, entity.getY(), entity.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }
        }
        entity.fallDistance = 0.0F;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
