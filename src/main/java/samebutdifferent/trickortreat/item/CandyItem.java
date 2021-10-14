package samebutdifferent.trickortreat.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import samebutdifferent.trickortreat.TrickOrTreatModInit;
import samebutdifferent.trickortreat.registry.ModItems;

import java.util.List;

public class CandyItem extends Item {
    public CandyItem(Item.Settings settings) {
        super(settings);
    }

    public CandyItem() {
        super(new Item.Settings().rarity(Rarity.EPIC).maxCount(16).group(TrickOrTreatModInit.TAB).food(new FoodComponent.Builder().snack().alwaysEdible().build()));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText(this.getTranslationKey() + ".tooltip"));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (stack.isOf(ModItems.FIZZLERS)) {
                world.createExplosion(player, player.getX(), player.getY(), player.getZ(), 6.0F, Explosion.DestructionType.NONE);
                player.getItemCooldownManager().set(stack.getItem(), 100);
                Vec3d movement = player.getVelocity();
                player.setVelocity(movement.x, 2.0D, movement.z);
                player.velocityDirty = true;
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 4, false, false, false));
            }
            if (stack.isOf(ModItems.PEARL_POP)) {
                player.getItemCooldownManager().set(stack.getItem(), 100);
                BlockPos pos = getBlockAimingAt(player, 10);
                player.requestTeleport(pos.getX(), pos.getY() + 1, pos.getZ());
                for (int i = 0; i < 32; ++i) {
                    world.addParticle(ParticleTypes.PORTAL, player.getX(), player.getY() + world.random.nextDouble() * 2.0D, player.getZ(), world.random.nextGaussian(), 0.0D, world.random.nextGaussian());
                }
                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            }
            if (stack.isOf(ModItems.EYECE_CREAM)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0));
                if (!world.isClient) {
                    ((ServerPlayerEntity) player).networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, 1.0F));
                }
            }
        }

        return super.finishUsing(stack, world, user);
    }

    // Thank you to Tslat for letting me use this method (ported to yarn mappings for fabric version)
    public static BlockPos getBlockAimingAt(PlayerEntity player, double distance) {
        Vec3d startVec = new Vec3d(player.getX(), player.getY() + (double) player.getStandingEyeHeight(), player.getZ());
        float cosYaw = MathHelper.cos(-player.getYaw() * 0.017453292F - (float) Math.PI);
        float sinYaw = MathHelper.sin(-player.getYaw() * 0.017453292F - (float) Math.PI);
        float cosPitch = -MathHelper.cos(-player.getPitch() * 0.017453292F);
        float sinPitch = MathHelper.sin(-player.getPitch() * 0.017453292F);
        float angleX = sinYaw * cosPitch;
        float angleZ = cosYaw * cosPitch;
        Vec3d endVec = startVec.add((double) angleX * distance, (double) sinPitch * distance, (double) angleZ * distance);
        BlockHitResult ray = player.world.raycast(new RaycastContext(startVec, endVec, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY, player));
        return ray.getBlockPos();
    }
}
