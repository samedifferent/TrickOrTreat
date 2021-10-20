package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;

public class WaterboltEffect extends Effect {
    public WaterboltEffect() {
        super(EffectType.BENEFICIAL, 43443);
    }

    @Override
    public void applyEffectTick(LivingEntity player, int pAmplifier) {
        if (player.isUnderWater() && player.isSwimming()) {
            float yRot = player.yRot;
            float xRot = player.xRot;
            float f1 = -MathHelper.sin(yRot * ((float)Math.PI / 180F)) * MathHelper.cos(xRot * ((float)Math.PI / 180F));
            float f2 = -MathHelper.sin(xRot * ((float)Math.PI / 180F));
            float f3 = MathHelper.cos(yRot * ((float)Math.PI / 180F)) * MathHelper.cos(xRot * ((float)Math.PI / 180F));
            float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
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
