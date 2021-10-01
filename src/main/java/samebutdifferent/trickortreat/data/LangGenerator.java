package samebutdifferent.trickortreat.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fmllegacy.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModItems;

public class LangGenerator extends LanguageProvider {
    public LangGenerator(DataGenerator gen, String locale) {
        super(gen, TrickOrTreat.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject item : ModItems.ITEMS.getEntries()) {
            String name = WordUtils.capitalize(item.getId().getPath().replace("_", " "));
            addItem(item, name);
        }
    }
}
