package samebutdifferent.trickortreat.effect;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ClimbingEffect extends MobEffect {
    protected int ambientSoundTime;

    public ClimbingEffect() {
        super(MobEffectCategory.BENEFICIAL, 3801088);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.horizontalCollision) {
            Vec3 initialVec = entity.getDeltaMovement();
            Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
            entity.setDeltaMovement(climbVec.x * 0.91D, climbVec.y * 0.98D, climbVec.z * 0.91D);
        }
        if (entity.getRandom().nextInt(1000) < this.ambientSoundTime++) {
            this.ambientSoundTime = -40;
            entity.playSound(SoundEvents.SPIDER_AMBIENT, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
