package samebutdifferent.trickortreat.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import samebutdifferent.trickortreat.TrickOrTreatModInit;

public class ModSoundEvents implements RegistryClass {
    public static final SoundEvent GOODIE_BAG_OPEN = new SoundEvent(new Identifier(TrickOrTreatModInit.MOD_ID, "item.goodie_bag.open"));

    @Override
    public void register(String modId) {
        Registry.register(Registry.SOUND_EVENT, new Identifier(modId, "goodie_bag_open"), GOODIE_BAG_OPEN);
    }
}
