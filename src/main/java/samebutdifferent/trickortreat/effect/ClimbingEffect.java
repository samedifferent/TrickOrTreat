package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ClimbingEffect extends Effect {
    protected int ambientSoundTime;

    public ClimbingEffect() {
        super(EffectType.BENEFICIAL, 3801088);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.horizontalCollision) {
            Vector3d initialVec = entity.getDeltaMovement();
            Vector3d climbVec = new Vector3d(initialVec.x, 0.2D, initialVec.z);
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
