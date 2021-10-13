package samebutdifferent.trickortreat;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.trickortreat.registry.ModConfig;
import samebutdifferent.trickortreat.registry.ModEffects;
import samebutdifferent.trickortreat.registry.ModItems;
import samebutdifferent.trickortreat.registry.ModSoundEvents;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TrickOrTreatModInit implements ModInitializer {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "trickortreat";
    public static final CreativeModeTab TAB = new CreativeModeTab(TrickOrTreat.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.JACK_O_LANTERN);
        }
    };

    public TrickOrTreat() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_CONFIG);

        ModEffects.MOB_EFFECTS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSoundEvents.SOUND_EVENTS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isHalloween() {
        LocalDate localdate = LocalDate.now();
        int day = localdate.get(ChronoField.DAY_OF_MONTH);
        int month = localdate.get(ChronoField.MONTH_OF_YEAR);
        return month == 10 && day >= 1 || month == 11 && day <= 1;
    }
}
