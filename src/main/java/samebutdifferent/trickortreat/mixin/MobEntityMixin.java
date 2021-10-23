package samebutdifferent.trickortreat.mixin;

import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import samebutdifferent.trickortreat.registry.ModEffects;

/**
 * @author Jamalam360
 */

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Shadow
    @Final
    protected GoalSelector goalSelector;

    @Inject(
            at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/entity/mob/MobEntity;initGoals()V"),
            method = "<init>"
    )
    private void initMixin(CallbackInfo ci) {
        if (this instanceof Monster) {
            FleeEntityGoal<PlayerEntity> goal = new FleeEntityGoal<>(((PathAwareEntity) (Object) this), PlayerEntity.class, (livingEntity) -> livingEntity.hasStatusEffect(ModEffects.SCARY), 8.0F, 1.8D, 1.8D, (livingEntity -> true));
            goalSelector.add(1, goal);
        }
    }
}
