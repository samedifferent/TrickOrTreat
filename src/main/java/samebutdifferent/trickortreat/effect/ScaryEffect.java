package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ScaryEffect extends Effect {
    public ScaryEffect() {
        super(EffectType.BENEFICIAL, 35);
    }

    @SubscribeEvent
    static void onLivingSetAttackTarget(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof MonsterEntity) {
            MonsterEntity monster = (MonsterEntity) event.getEntity();
            AvoidEntityGoal<PlayerEntity> goal = new AvoidEntityGoal<>(monster, PlayerEntity.class, (entity) -> entity.hasEffect(ModEffects.SCARY.get()), 8.0F, 1.8D, 1.8D, EntityPredicates.NO_CREATIVE_OR_SPECTATOR::test);
            monster.goalSelector.addGoal(1, goal);
        }
    }
}
