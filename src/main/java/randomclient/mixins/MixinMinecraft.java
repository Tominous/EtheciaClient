package randomclient.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import randomclient.RandomClient;
import randomclient.event.InitializationEvent;
import randomclient.event.PreInitializationEvent;
import randomclient.event.join.JoinSingleplayerEvent;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "startGame", at = @At("HEAD"))
    private void preinit(CallbackInfo ci) {
        RandomClient.INSTANCE = new RandomClient();
        RandomClient.EVENT_BUS.register(RandomClient.INSTANCE);
        RandomClient.EVENT_BUS.post(new PreInitializationEvent());
    }
    @Inject(method = "startGame", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        RandomClient.EVENT_BUS.post(new InitializationEvent());
    }
    @Inject(method = "launchIntegratedServer", at = @At("HEAD"))
    private void launchIntegratedServer(String folderName, String worldName, WorldSettings worldSettingsIn, CallbackInfo ci) {
        RandomClient.EVENT_BUS.post(new JoinSingleplayerEvent(worldName));
    }
}
