package samebutdifferent.trickortreat.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samebutdifferent.trickortreat.TrickOrTreatModInit;
import samebutdifferent.trickortreat.registry.ModConfig;
import samebutdifferent.trickortreat.registry.ModEffects;
import samebutdifferent.trickortreat.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamalam360
 */

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
            at = @At("HEAD"),
            method = "damage"
    )
    public void damageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        //region Life Leech
        if (source.getAttacker() instanceof PlayerEntity player && player.hasStatusEffect(ModEffects.LIFE_LEECH)) {
            float healthStealAmount = Math.min(6, amount / 4);
            if (healthStealAmount >= 1) {
                player.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.0F, 1.0F);
                player.heal(healthStealAmount);
            }
        }
        //endregion
    }

    @Inject(
            at = @At("HEAD"),
            method = "dropLoot"
    )
    public void dropLootMixin(DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        List<ItemStack> dropStacks = new ArrayList<>();

        if (TrickOrTreatModInit.isHalloween() || !config.onlyHalloween) {
            if (!source.isFromFalling() && !source.isFire() && !source.isMagic()) {
                Entity entity = ((LivingEntity) (Object) this);

                if (entity.world.random.nextFloat() <= 0.25F * config.goodieBagDropChance) {
                    if (entity instanceof BlazeEntity) {
                        dropStacks.add(new ItemStack(ModItems.BLAZE_GOODIE_BAG));
                    }

                    if (entity instanceof CreeperEntity) {
                        dropStacks.add(new ItemStack(ModItems.CREEPER_GOODIE_BAG));
                    }

                    if (entity instanceof DrownedEntity) {
                        dropStacks.add(new ItemStack(ModItems.DROWNED_GOODIE_BAG));
                    }

                    if (entity instanceof EndermanEntity) {
                        dropStacks.add(new ItemStack(ModItems.ENDERMAN_GOODIE_BAG));
                    }

                    if (entity instanceof GhastEntity) {
                        dropStacks.add(new ItemStack(ModItems.GHAST_GOODIE_BAG));
                    }

                    if (entity instanceof GuardianEntity) {
                        dropStacks.add(new ItemStack(ModItems.GUARDIAN_GOODIE_BAG));
                    }

                    if (entity instanceof PhantomEntity) {
                        dropStacks.add(new ItemStack(ModItems.PHANTOM_GOODIE_BAG));
                    }

                    if (entity instanceof SkeletonEntity) {
                        dropStacks.add(new ItemStack(ModItems.SKELETON_GOODIE_BAG));
                    }

                    if (entity instanceof SlimeEntity) {
                        dropStacks.add(new ItemStack(ModItems.SLIME_GOODIE_BAG));
                    }

                    if (entity instanceof SpiderEntity) {
                        dropStacks.add(new ItemStack(ModItems.SPIDER_GOODIE_BAG));
                    }

                    if (entity instanceof ZombieEntity) {
                        dropStacks.add(new ItemStack(ModItems.ZOMBIE_GOODIE_BAG));
                    }
                }
            }
        }

        for (ItemStack drop : dropStacks) {
            ((LivingEntity) (Object) this).dropStack(drop);
        }
    }
}
