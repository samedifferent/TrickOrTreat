package samebutdifferent.trickortreat.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class FirefingerEffect extends Effect {
    public FirefingerEffect() {
        super(EffectType.BENEFICIAL, 16721929);
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(ModEffects.FIREFINGER.get())) {
            target.setSecondsOnFire(4);
        }
    }
}
