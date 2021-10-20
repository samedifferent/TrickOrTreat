package samebutdifferent.trickortreat.registry;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.effect.*;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, TrickOrTreat.MOD_ID);

    public static final RegistryObject<Effect> FIREFINGER = EFFECTS.register("firefinger", FirefingerEffect::new);
    public static final RegistryObject<Effect> SCARY = EFFECTS.register("scary", ScaryEffect::new);
    public static final RegistryObject<Effect> BOUNCY = EFFECTS.register("bouncy", BouncyEffect::new);
    public static final RegistryObject<Effect> LIFE_LEECH = EFFECTS.register("life_leech", LifeLeechEffect::new);
    public static final RegistryObject<Effect> BONE_BREAKING = EFFECTS.register("bone_breaking", BoneBreakingEffect::new);
    public static final RegistryObject<Effect> PARALYZED = EFFECTS.register("paralyzed", ParalyzedEffect::new);
    public static final RegistryObject<Effect> CLIMBING = EFFECTS.register("climbing", ClimbingEffect::new);
    public static final RegistryObject<Effect> WATERBOLT = EFFECTS.register("waterbolt", WaterboltEffect::new);
    public static final RegistryObject<Effect> ROTTEN_BITE = EFFECTS.register("rotten_bite", RottenBiteEffect::new);
}
