package samebutdifferent.trickortreat.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;
import samebutdifferent.trickortreat.effect.*;

public class ModEffects implements RegistryClass {
    public static final StatusEffect FIREFINGER = new FirefingerEffect();
    public static final StatusEffect SCARY = new ScaryEffect();
    public static final StatusEffect BOUNCY = new BouncyEffect();
    public static final StatusEffect LIFE_LEECH = new LifeLeechEffect();
    public static final StatusEffect BONE_BREAKING = new BoneBreakingEffect();
    public static final StatusEffect PARALYZED = new ParalyzedEffect();
    public static final StatusEffect CLIMBING = new ClimbingEffect();
    public static final StatusEffect WATERBOLT = new WaterboltEffect();
    public static final StatusEffect ROTTEN_BITE = new RottenBiteEffect();

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
