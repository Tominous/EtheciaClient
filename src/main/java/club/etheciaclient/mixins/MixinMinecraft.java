package club.etheciaclient.mixins;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.event.CloseEvent;
import club.etheciaclient.event.InitializationEvent;
import club.etheciaclient.event.PreInitializationEvent;
import club.etheciaclient.event.TickEvent;
import club.etheciaclient.event.join.JoinSingleplayerEvent;
import club.etheciaclient.mods.AbstractMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "startGame", at = @At("HEAD"))
    private void preinit(CallbackInfo ci) throws Throwable {
        AbstractMod.init();
        EtheciaClient.INSTANCE = new EtheciaClient();
        EtheciaClient.EVENT_BUS.register(EtheciaClient.INSTANCE);
        EtheciaClient.EVENT_BUS.post(new PreInitializationEvent());
    }
    @Inject(method = "startGame", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        EtheciaClient.EVENT_BUS.post(new InitializationEvent());
    }
    @Inject(method = "launchIntegratedServer", at = @At("HEAD"))
    private void launchIntegratedServer(String folderName, String worldName, WorldSettings worldSettingsIn, CallbackInfo ci) {
        EtheciaClient.EVENT_BUS.post(new JoinSingleplayerEvent(worldName));
    }
    @Inject(
            method = "runTick",
            at = @At("HEAD")
    )
    private void tick(CallbackInfo ci) {
        EtheciaClient.EVENT_BUS.post(new TickEvent());
    }
    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo ci) {
        EtheciaClient.EVENT_BUS.post(new CloseEvent());
    }
}
