package samebutdifferent.trickortreat.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber
public class ScaryEffect extends MobEffect {
    public ScaryEffect() {
        super(MobEffectCategory.BENEFICIAL, 35);
    }

    @SubscribeEvent
    static void onLivingSetAttackTarget(LivingSetAttackTargetEvent event) {
        if (event.getEntityLiving() instanceof Monster monster) {
            monster.goalSelector.addGoal(3, new AvoidEntityGoal<>(monster, Player.class, (entity) -> entity.hasEffect(ModEffects.SCARY.get()), 10.0F, 1.0D, 1.2D, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test));
        }
    }
}