package samebutdifferent.trickortreat.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import samebutdifferent.trickortreat.registry.ModEffects;

/**
 * @author Jamalam360
 */

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @ModifyArg(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal = 1),
            index = 0
    )
    public Packet<?> modifyServerPlayPacket(Packet<?> in) {
        if (((ClientPlayerEntity) (Object) this).hasStatusEffect(ModEffects.PARALYZED)) {
            return new PlayerInputC2SPacket(0, 0, false, false);
        } else {
            return in;
        }
    }
}
