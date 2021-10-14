package samebutdifferent.trickortreat.registry;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import samebutdifferent.trickortreat.TrickOrTreatModInit;

@Config(name = TrickOrTreatModInit.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean onlyHalloween = true;
    @ConfigEntry.Gui.Tooltip(count = 2)
    public double goodieBagDropChance = 1.0;
}
