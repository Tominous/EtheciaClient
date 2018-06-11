package club.etheciaclient.mixins;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.event.RenderEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
    @Inject(method = "updateCameraAndRender", at = @At("HEAD"))
    private void updateCameraAndRender(float f, long l, CallbackInfo ci) {
        EtheciaClient.EVENT_BUS.post(new RenderEvent());
    }
}
