package samebutdifferent.trickortreat.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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
                player.fallDistance = 0.0F;
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

    @SubscribeEvent
    static void onMobDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;
        if (TrickOrTreat.isHalloween() || !ModConfig.ONLY_HALLOWEEN.get()) {
            if (!event.getSource().isFall() && !event.getSource().isFire() && !event.getSource().isMagic() && !event.getSource().isExplosion()) {
                if (level.random.nextFloat() <= 0.1F * ModConfig.GOODIE_BAG_DROP_CHANCE.get().floatValue()) {
                    if (entity instanceof Blaze)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.BLAZE_GOODIE_BAG.get())));
                    if (entity instanceof Creeper)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.CREEPER_GOODIE_BAG.get())));
                    if (entity instanceof Drowned)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.DROWNED_GOODIE_BAG.get())));
                    if (entity instanceof EnderMan)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.ENDERMAN_GOODIE_BAG.get())));
                    if (entity instanceof Ghast)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.GHAST_GOODIE_BAG.get())));
                    if (entity instanceof Guardian)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.GUARDIAN_GOODIE_BAG.get())));
                    if (entity instanceof Phantom)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.PHANTOM_GOODIE_BAG.get())));
                    if (entity instanceof Skeleton)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SKELETON_GOODIE_BAG.get())));
                    if (entity instanceof Slime)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SLIME_GOODIE_BAG.get())));
                    if (entity instanceof Spider)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SPIDER_GOODIE_BAG.get())));
                    if (entity instanceof Zombie)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.ZOMBIE_GOODIE_BAG.get())));
                }
            }
        }
    }
}
