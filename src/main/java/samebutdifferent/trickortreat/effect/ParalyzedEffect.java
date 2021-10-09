package samebutdifferent.trickortreat.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ParalyzedEffect extends MobEffect {
    public ParalyzedEffect() {
        super(MobEffectCategory.HARMFUL, 16777038);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.setDeltaMovement(Vec3.ZERO);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onInputUpdate(InputUpdateEvent event) {
        Input input = event.getMovementInput();
        if (event.getPlayer().hasEffect(ModEffects.PARALYZED.get())) {
            input.up = false;
            input.down = false;
            input.left = false;
            input.right = false;
            input.forwardImpulse = 0;
            input.leftImpulse = 0;
            input.jumping = false;
            input.shiftKeyDown = false;
        }
    }
}
