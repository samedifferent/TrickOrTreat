package samebutdifferent.trickortreat.registry;

import net.minecraft.util.Identifier;
import samebutdifferent.trickortreat.TrickOrTreatModInit;

/**
 * @author Jamalam360
 */
public interface RegistryClass {
    void register(String modId);

    default Identifier idOf(String id) {
        return new Identifier(TrickOrTreatModInit.MOD_ID, id);
    }
}
