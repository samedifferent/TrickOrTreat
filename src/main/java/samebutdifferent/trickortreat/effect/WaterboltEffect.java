package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.MathHelper;

public class WaterboltEffect extends StatusEffect {
    public WaterboltEffect() {
        super(StatusEffectCategory.BENEFICIAL, 43443);
    }

    @Override
    public void applyUpdateEffect(LivingEntity player, int amplifier) {
        if (player.isSwimming() && player.isSwimming()) {
            float yRot = player.getYaw();
            float xRot = player.getPitch();
            float f1 = -MathHelper.sin(yRot * ((float) Math.PI / 180F)) * MathHelper.cos(xRot * ((float) Math.PI / 180F));
            float f2 = -MathHelper.sin(xRot * ((float) Math.PI / 180F));
            float f3 = MathHelper.cos(yRot * ((float) Math.PI / 180F)) * MathHelper.cos(xRot * ((float) Math.PI / 180F));
            float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 0.5F;
            f1 = f1 * (f5 / f4);
            f2 = f2 * (f5 / f4);
            f3 = f3 * (f5 / f4);
            player.addVelocity(f1, f2, f3);
            player.setRiptideTicks(20);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
