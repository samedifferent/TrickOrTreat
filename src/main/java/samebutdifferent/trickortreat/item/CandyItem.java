package samebutdifferent.trickortreat.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModItems;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class CandyItem extends Item {
    public CandyItem(Properties properties) {
        super(properties);
    }

    public CandyItem() {
        super(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new Food.Builder().fast().alwaysEat().build()));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.trickortreat." + this.getRegistryName().getPath() + ".tooltip"));
    }

    @SubscribeEvent
    static void onEat(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            ItemStack stack = event.getItem();
            World level = player.level;
            if (stack.getItem().equals(ModItems.FIZZLERS.get())) {
                level.explode(player, player.getX(), player.getY(), player.getZ(), 6.0F, Explosion.Mode.NONE);
                player.getCooldowns().addCooldown(stack.getItem(), 100);
                Vector3d movement = player.getDeltaMovement();
                player.setDeltaMovement(movement.x, 2.0D, movement.z);
                player.hasImpulse = true;
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100, 4, false, false, false));
            }
            if (stack.getItem().equals(ModItems.PEARL_POP.get())) {
                player.getCooldowns().addCooldown(stack.getItem(), 100);
                BlockPos pos = getBlockAimingAt(player, 10);
                player.teleportTo(pos.getX(), pos.getY() + 1, pos.getZ());
                for(int i = 0; i < 32; ++i) {
                    level.addParticle(ParticleTypes.PORTAL, player.getX(), player.getY() + level.random.nextDouble() * 2.0D, player.getZ(), level.random.nextGaussian(), 0.0D, level.random.nextGaussian());
                }
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
            }
            if (stack.getItem().equals(ModItems.EYECE_CREAM.get())) {
                player.addEffect(new EffectInstance(Effects.DIG_SPEED, 600, 1));
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 600, 0));
                if (!level.isClientSide) {
                    ((ServerPlayerEntity)player).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.GUARDIAN_ELDER_EFFECT, 1.0F));
                }
            }
        }
    }

    // Thank you to Tslat for letting me use this method
    public static BlockPos getBlockAimingAt(PlayerEntity player, double distance) {
        Vector3d startVec = new Vector3d(player.getX(), player.getY() + (double)player.getEyeHeight(), player.getZ());
        float cosYaw = MathHelper.cos(-player.yRot * 0.017453292F - (float)Math.PI);
        float sinYaw = MathHelper.sin(-player.yRot * 0.017453292F - (float)Math.PI);
        float cosPitch = -MathHelper.cos(-player.xRot * 0.017453292F);
        float sinPitch = MathHelper.sin(-player.xRot * 0.017453292F);
        float angleX = sinYaw * cosPitch;
        float angleZ = cosYaw * cosPitch;
        Vector3d endVec = startVec.add((double)angleX * distance, (double)sinPitch * distance, (double)angleZ * distance);
        BlockRayTraceResult ray = player.level.clip(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));
        return ray.getBlockPos();
    }
}
