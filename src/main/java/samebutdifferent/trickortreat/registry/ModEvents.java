package samebutdifferent.trickortreat.registry;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import samebutdifferent.trickortreat.TrickOrTreat;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    static void onMobDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;
        if (TrickOrTreat.isHalloween() || !ModConfig.ONLY_HALLOWEEN.get()) {
            if (!event.getSource().isFall() && !event.getSource().isFire() && !event.getSource().isMagic()) {
                if (level.random.nextFloat() <= 0.25F * ModConfig.GOODIE_BAG_DROP_CHANCE.get().floatValue()) {
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
