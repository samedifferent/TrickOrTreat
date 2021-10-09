package samebutdifferent.trickortreat.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModSoundEvents;

public class WaterboltEffect extends MobEffect {
    public WaterboltEffect() {
        super(MobEffectCategory.BENEFICIAL, 43443);
    }

    @Override
    public void applyEffectTick(LivingEntity player, int pAmplifier) {
        if (player.isUnderWater() && player.isSwimming()) {
            float yRot = player.getYRot();
            float xRot = player.getXRot();
            float f1 = -Mth.sin(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
            float f2 = -Mth.sin(xRot * ((float)Math.PI / 180F));
            float f3 = Mth.cos(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 0.5F;
            f1 = f1 * (f5 / f4);
            f2 = f2 * (f5 / f4);
            f3 = f3 * (f5 / f4);
            player.push(f1, f2, f3);
            player.startAutoSpinAttack(20);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
