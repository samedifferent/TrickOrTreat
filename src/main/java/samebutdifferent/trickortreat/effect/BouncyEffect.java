package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BouncyEffect extends StatusEffect {
    public BouncyEffect() {
        super(StatusEffectCategory.BENEFICIAL, 3140701);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isOnGround()) {
            Vec3d movement = entity.getVelocity();
            entity.setVelocity(movement.x, 1.5D, movement.z);
            entity.velocityDirty = true;
            entity.playSound(SoundEvents.ENTITY_SLIME_JUMP, 1.0F, 1.0F);
            for (int j = 0; j < 8; ++j) {
                float f = entity.world.random.nextFloat() * ((float) Math.PI * 2F);
                float f1 = entity.world.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * 0.5F * f1;
                float f3 = MathHelper.cos(f) * 0.5F * f1;
                entity.world.addParticle(ParticleTypes.ITEM_SLIME, entity.getX() + (double) f2, entity.getY(), entity.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
            }
        }
        entity.fallDistance = 0.0F;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
