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


}
