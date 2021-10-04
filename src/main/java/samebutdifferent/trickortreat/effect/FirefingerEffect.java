package samebutdifferent.trickortreat.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class FirefingerEffect extends MobEffect {
    public FirefingerEffect() {
        super(MobEffectCategory.BENEFICIAL, 16721929);
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        Player player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(ModEffects.FIREFINGER.get())) {
            target.setSecondsOnFire(4);
        }
    }
}
