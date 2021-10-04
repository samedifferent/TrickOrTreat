package samebutdifferent.trickortreat;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import samebutdifferent.trickortreat.registry.ModItems;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Mod(TrickOrTreat.MOD_ID)
public class TrickOrTreat {

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

        ModItems.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isHalloween() {
        LocalDate localdate = LocalDate.now();
        int day = localdate.get(ChronoField.DAY_OF_MONTH);
        int month = localdate.get(ChronoField.MONTH_OF_YEAR);
        return month == 10 && day >= 1 || month == 11 && day <= 1;
    }
}
