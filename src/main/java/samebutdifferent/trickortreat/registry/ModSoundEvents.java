package samebutdifferent.trickortreat.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.trickortreat.TrickOrTreat;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TrickOrTreat.MOD_ID);

    public static final RegistryObject<SoundEvent> GOODIE_BAG_OPEN = SOUND_EVENTS.register("goodie_bag_open", () -> new SoundEvent(new ResourceLocation(TrickOrTreat.MOD_ID, "item.goodie_bag.open")));
}
