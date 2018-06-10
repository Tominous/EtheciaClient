package randomclient.mixins;

import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import randomclient.RandomClient;
import randomclient.event.join.JoinServerEvent;

@Mixin(GuiConnecting.class)
public class MixinGuiConnecting {
    @Inject(method = "connect", at = @At("HEAD"))
    private void connect(String ip, int port, CallbackInfo ci) {
        RandomClient.EVENT_BUS.post(new JoinServerEvent(ip, port));
    }
}
