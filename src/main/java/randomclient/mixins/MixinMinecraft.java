package randomclient.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import randomclient.RandomClient;
import randomclient.event.InitializationEvent;
import randomclient.event.PreInitializationEvent;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "startGame", at = @At("HEAD"))
    private void preinit(CallbackInfo ci) {
        new RandomClient(); // Initialize
        RandomClient.EVENT_BUS.post(new PreInitializationEvent());
    }
    @Inject(method = "startGame", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        RandomClient.EVENT_BUS.post(new InitializationEvent());
    }
}
