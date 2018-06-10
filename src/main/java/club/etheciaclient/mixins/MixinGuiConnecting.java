package club.etheciaclient.mixins;

import club.etheciaclient.RandomClient;
import club.etheciaclient.event.join.JoinServerEvent;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiConnecting.class)
public class MixinGuiConnecting {
    @Inject(method = "connect", at = @At("HEAD"))
    private void connect(String ip, int port, CallbackInfo ci) {
        RandomClient.EVENT_BUS.post(new JoinServerEvent(ip, port));
    }
}
