package samebutdifferent.trickortreat.mixin;

import net.minecraft.entity.ai.goal.GoalSelector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Jamalam360
 */

@Mixin(MobEntityAccessor.class)
public interface MobEntityAccessor {
    @Accessor
    GoalSelector getGoalSelector();
}
