package samebutdifferent.trickortreat.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModItems;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class CandyItem extends Item {
    public CandyItem() {
        super(new Item.Properties().stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().build()));
    }

    @SubscribeEvent
    static void onEat(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving() instanceof Player player) {
            ItemStack stack = event.getItem();
            Level level = player.level;
            if (stack.is(ModItems.FIZZLERS.get())) {
                level.explode(player, player.getX(), player.getY(), player.getZ(), 6.0F, Explosion.BlockInteraction.NONE);
                player.getCooldowns().addCooldown(stack.getItem(), 100);
                Vec3 movement = player.getDeltaMovement();
                player.setDeltaMovement(movement.x, 2.0D, movement.z);
                player.hasImpulse = true;
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 4, false, false, false));
            }
            if (stack.is(ModItems.PEARL_POP.get())) {
                player.getCooldowns().addCooldown(stack.getItem(), 100);
                BlockPos pos = getBlockAimingAt(player, 10);
                player.teleportTo(pos.getX(), pos.getY() + 1, pos.getZ());
                for(int i = 0; i < 32; ++i) {
                    level.addParticle(ParticleTypes.PORTAL, player.getX(), player.getY() + level.random.nextDouble() * 2.0D, player.getZ(), level.random.nextGaussian(), 0.0D, level.random.nextGaussian());
                }
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            }
        }
    }

    // Thank you to Tslat for letting me use this method
    public static BlockPos getBlockAimingAt(Player player, double distance) {
        Vec3 startVec = new Vec3(player.getX(), player.getY() + (double)player.getEyeHeight(), player.getZ());
        float cosYaw = Mth.cos(-player.getYRot() * 0.017453292F - (float)Math.PI);
        float sinYaw = Mth.sin(-player.getYRot() * 0.017453292F - (float)Math.PI);
        float cosPitch = -Mth.cos(-player.getXRot() * 0.017453292F);
        float sinPitch = Mth.sin(-player.getXRot() * 0.017453292F);
        float angleX = sinYaw * cosPitch;
        float angleZ = cosYaw * cosPitch;
        Vec3 endVec = startVec.add((double)angleX * distance, (double)sinPitch * distance, (double)angleZ * distance);
        BlockHitResult ray = player.level.clip(new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, null));
        return ray.getBlockPos();
    }
}
