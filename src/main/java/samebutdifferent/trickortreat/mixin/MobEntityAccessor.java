package samebutdifferent.trickortreat.mixin;

import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Jamalam360
 */

@Mixin(MobEntity.class)
public interface MobEntityAccessor {
    @Accessor
    GoalSelector getGoalSelector();
}
