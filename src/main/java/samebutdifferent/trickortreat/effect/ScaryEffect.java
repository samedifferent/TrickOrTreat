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
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ScaryEffect extends MobEffect {
    public ScaryEffect() {
        super(MobEffectCategory.BENEFICIAL, 35);
    }

    @SubscribeEvent
    static void onLivingSetAttackTarget(LivingSetAttackTargetEvent event) {
        if (event.getEntityLiving() instanceof Monster monster) {
            monster.goalSelector.addGoal(1, new AvoidEntityGoal<>(monster, Player.class, (entity) -> entity.hasEffect(ModEffects.SCARY.get()), 8.0F, 1.8D, 1.8D, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test));
        }
    }
}
