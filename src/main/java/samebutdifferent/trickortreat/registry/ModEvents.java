package samebutdifferent.trickortreat.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    static void onEat(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving() instanceof Player) {
            ItemStack stack = event.getItem();
            Player player = (Player) event.getEntityLiving();
            Level level = player.level;
            if (stack.is(ModItems.FIZZLERS.get())) {
                level.explode(player, player.getX(), player.getY(), player.getZ(), 3.0F, Explosion.BlockInteraction.BREAK);
            }
            if (stack.is(ModItems.PEARL_POP.get())) {
                Vec3 posVec = player.position();
                Vec3 lookVec = player.getLookAngle().scale(10.0F);
                double x = posVec.x + lookVec.x;;
                double y = posVec.y + lookVec.y;;
                double z = posVec.z + lookVec.z;;
                player.teleportTo(x, y, z);
                player.fallDistance = 0.0F;
                for(int i = 0; i < 32; ++i) {
                    level.addParticle(ParticleTypes.PORTAL, player.getX(), player.getY() + level.random.nextDouble() * 2.0D, player.getZ(), level.random.nextGaussian(), 0.0D, level.random.nextGaussian());
                }
                level.playSound(null, x, y, z, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    static void onHit(AttackEntityEvent event) {
        Player player = event.getPlayer();
        Entity target = event.getTarget();
        if (player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            target.setSecondsOnFire(3);
        }
    }
}
