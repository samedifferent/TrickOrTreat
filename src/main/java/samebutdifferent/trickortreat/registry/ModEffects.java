package samebutdifferent.trickortreat.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.effect.FirefingerEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TrickOrTreat.MOD_ID);

    public static final RegistryObject<MobEffect> FIREFINGER = MOB_EFFECTS.register("firefinger", FirefingerEffect::new);
}
