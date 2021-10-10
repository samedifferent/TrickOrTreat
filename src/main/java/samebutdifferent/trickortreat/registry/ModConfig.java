package samebutdifferent.trickortreat.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import samebutdifferent.trickortreat.TrickOrTreat;

@Mod.EventBusSubscriber(modid = TrickOrTreat.MOD_ID)
public class ModConfig {
    public static ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.BooleanValue ONLY_HALLOWEEN;
    public static final ForgeConfigSpec.DoubleValue GOODIE_BAG_DROP_CHANCE;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        ONLY_HALLOWEEN = COMMON_BUILDER.comment("Should goodie bags only drop during Halloween month? (October 1 to November 1)")
                .define("onlyHalloween", true);
        GOODIE_BAG_DROP_CHANCE = COMMON_BUILDER.comment("Multiply the chance that goodie bags drop")
                .defineInRange("goodieBagDropChance", 1.0, 0.1, 4.0);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}
