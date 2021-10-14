package samebutdifferent.trickortreat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import samebutdifferent.trickortreat.registry.*;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TrickOrTreatModInit implements ModInitializer {

    public static final String MOD_ID = "trickortreat";

    public static final ItemGroup TAB = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "trickortreat"),
            Items.JACK_O_LANTERN::getDefaultStack
    );

    private static final RegistryClass[] REGISTRIES = new RegistryClass[]{
            new ModEffects(),
            new ModEvents(),
            new ModItems(),
            new ModSoundEvents()
    };

    @Override
    public void onInitialize() {
        for (RegistryClass registry : REGISTRIES) {
            registry.register(MOD_ID);
        }
    }

    public static boolean isHalloween() {
        LocalDate localdate = LocalDate.now();
        int day = localdate.get(ChronoField.DAY_OF_MONTH);
        int month = localdate.get(ChronoField.MONTH_OF_YEAR);
        return month == 10 && day >= 1 || month == 11 && day <= 1;
    }
}
