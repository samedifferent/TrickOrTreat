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
        Registry.register(Registry.STATUS_EFFECT, idOf("firefinger"), FIREFINGER);
        Registry.register(Registry.STATUS_EFFECT, idOf("scary"), SCARY);
        Registry.register(Registry.STATUS_EFFECT, idOf("bouncy"), BOUNCY);
        Registry.register(Registry.STATUS_EFFECT, idOf("life_leach"), LIFE_LEECH);
        Registry.register(Registry.STATUS_EFFECT, idOf("bone_breaking"), BONE_BREAKING);
        Registry.register(Registry.STATUS_EFFECT, idOf("paralyzed"), PARALYZED);
        Registry.register(Registry.STATUS_EFFECT, idOf("climbing"), CLIMBING);
        Registry.register(Registry.STATUS_EFFECT, idOf("waterbolt"), WATERBOLT);
        Registry.register(Registry.STATUS_EFFECT, idOf("rotten_bite"), ROTTEN_BITE);
    }
}
