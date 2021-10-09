package samebutdifferent.trickortreat.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.effect.*;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TrickOrTreat.MOD_ID);

    public static final RegistryObject<MobEffect> FIREFINGER = MOB_EFFECTS.register("firefinger", FirefingerEffect::new);
    public static final RegistryObject<MobEffect> SCARY = MOB_EFFECTS.register("scary", ScaryEffect::new);
    public static final RegistryObject<MobEffect> BOUNCY = MOB_EFFECTS.register("bouncy", BouncyEffect::new);
    public static final RegistryObject<MobEffect> LIFE_LEECH = MOB_EFFECTS.register("life_leech", LifeLeechEffect::new);
    public static final RegistryObject<MobEffect> BONE_BREAKING = MOB_EFFECTS.register("bone_breaking", BoneBreakingEffect::new);
    public static final RegistryObject<MobEffect> PARALYZED = MOB_EFFECTS.register("paralyzed", ParalyzedEffect::new);
    public static final RegistryObject<MobEffect> CLIMBING = MOB_EFFECTS.register("climbing", ClimbingEffect::new);
    public static final RegistryObject<MobEffect> WATERBOLT = MOB_EFFECTS.register("waterbolt", WaterboltEffect::new);
}
