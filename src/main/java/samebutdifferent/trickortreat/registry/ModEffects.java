package samebutdifferent.trickortreat.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;
import samebutdifferent.trickortreat.effect.*;

@SuppressWarnings("SpellCheckingInspection")
public class ModEffects implements RegistryClass {
    public static final StatusEffect FIREFINGER = FirefingerEffect::new;
    public static final StatusEffect SCARY = ScaryEffect::new;
    public static final StatusEffect BOUNCY = BouncyEffect::new;
    public static final StatusEffect LIFE_LEECH = LifeLeechEffect::new;
    public static final StatusEffect BONE_BREAKING = BoneBreakingEffect::new;
    public static final StatusEffect PARALYZED = ParalyzedEffect::new;
    public static final StatusEffect CLIMBING = ClimbingEffect::new;
    public static final StatusEffect WATERBOLT = WaterboltEffect::new;
    public static final StatusEffect ROTTEN_BITE = RottenBiteEffect::new;

    @Override
    public void register(String modId) {
        Registry.register(Registry.STATUS_EFFECT, idOf(""), FIREFINGER);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), SCARY);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), BOUNCY);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), LIFE_LEECH);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), BONE_BREAKING);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), PARALYZED);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), CLIMBING);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), WATERBOLT);
        Registry.register(Registry.STATUS_EFFECT, idOf(""), ROTTEN_BITE);
    }
}
