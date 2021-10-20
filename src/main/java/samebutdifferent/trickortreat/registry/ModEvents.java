package samebutdifferent.trickortreat.registry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    static void onMobDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        World level = entity.level;
        if (TrickOrTreat.isHalloween() || !ModConfig.ONLY_HALLOWEEN.get()) {
            if (!(event.getSource() == DamageSource.FALL) && !event.getSource().isFire() && !event.getSource().isMagic()) {
                if (level.random.nextFloat() <= 0.25F * ModConfig.GOODIE_BAG_DROP_CHANCE.get().floatValue()) {
                    if (entity instanceof BlazeEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.BLAZE_GOODIE_BAG.get())));
                    if (entity instanceof CreeperEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.CREEPER_GOODIE_BAG.get())));
                    if (entity instanceof DrownedEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.DROWNED_GOODIE_BAG.get())));
                    if (entity instanceof EndermanEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.ENDERMAN_GOODIE_BAG.get())));
                    if (entity instanceof GhastEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.GHAST_GOODIE_BAG.get())));
                    if (entity instanceof GuardianEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.GUARDIAN_GOODIE_BAG.get())));
                    if (entity instanceof PhantomEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.PHANTOM_GOODIE_BAG.get())));
                    if (entity instanceof SkeletonEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SKELETON_GOODIE_BAG.get())));
                    if (entity instanceof SlimeEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SLIME_GOODIE_BAG.get())));
                    if (entity instanceof SpiderEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.SPIDER_GOODIE_BAG.get())));
                    if (entity instanceof ZombieEntity)
                        event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.ZOMBIE_GOODIE_BAG.get())));
                }
            }
        }
    }
}
