package samebutdifferent.trickortreat.effect;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModEffects;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ClimbingEffect extends MobEffect {
    private int ambientSoundTime;

    public ClimbingEffect() {
        super(MobEffectCategory.BENEFICIAL, 3801088);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.horizontalCollision) {
            Vec3 vec3 = entity.getDeltaMovement();
            Vec3 movement = new Vec3(vec3.x, 0.2D, vec3.z);
            entity.setDeltaMovement(movement.x * 0.91D, movement.y * 0.98D, movement.z * 0.91D);
        }
        if (entity.getRandom().nextInt(1000) < this.ambientSoundTime++) {
            this.ambientSoundTime = -40;
            entity.playSound(SoundEvents.SPIDER_AMBIENT, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }


    static void onFovModifier(EntityViewRenderEvent.CameraSetup event) {
        if (event.getInfo().getEntity() instanceof LivingEntity entity && entity.hasEffect(ModEffects.CLIMBING.get())) {

        }
    }
}
