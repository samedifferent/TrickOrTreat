package samebutdifferent.trickortreat.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.effect.BouncyEffect;
import samebutdifferent.trickortreat.effect.FirefingerEffect;
import samebutdifferent.trickortreat.effect.LifeLeechEffect;
import samebutdifferent.trickortreat.effect.ScaryEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TrickOrTreat.MOD_ID);

    public static final RegistryObject<MobEffect> FIREFINGER = MOB_EFFECTS.register("firefinger", FirefingerEffect::new);
    public static final RegistryObject<MobEffect> SCARY = MOB_EFFECTS.register("scary", ScaryEffect::new);
    public static final RegistryObject<MobEffect> BOUNCY = MOB_EFFECTS.register("bouncy", BouncyEffect::new);
    public static final RegistryObject<MobEffect> LIFE_LEECH = MOB_EFFECTS.register("life_leech", LifeLeechEffect::new);

}
