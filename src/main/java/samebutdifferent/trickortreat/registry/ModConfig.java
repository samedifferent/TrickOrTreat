package samebutdifferent.trickortreat.registry;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import samebutdifferent.trickortreat.TrickOrTreatModInit;

@Config(name = TrickOrTreatModInit.MOD_ID)
public class ModConfig implements ConfigData {
    //TODO: Port Config to Auto/Cloth Config

    //@ConfigEntry.Gui.Tooltip
    public boolean onlyHalloween = true;
    @ConfigEntry.Gui.Tooltip
    public double goodieBagDropChance = 1.0;

    /*static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        ONLY_HALLOWEEN = COMMON_BUILDER.comment("Should goodie bags only drop during Halloween month? (October 1 to November 1)")
                .define("onlyHalloween", true);
        GOODIE_BAG_DROP_CHANCE = COMMON_BUILDER.comment("Multiply the chance that goodie bags drop")
                .defineInRange("goodieBagDropChance", 1.0, 0.1, 4.0);
    }*/
}
