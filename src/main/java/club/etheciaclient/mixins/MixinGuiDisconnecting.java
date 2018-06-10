package club.etheciaclient.mixins;

import club.etheciaclient.EtheciaClient;
import club.etheciaclient.event.LeaveServerEvent;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDisconnected.class)
public class MixinGuiDisconnecting {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(GuiScreen screen,
                      String reasonLocalizationKey,
                      IChatComponent chatComp,
                      CallbackInfo ci) {
        EtheciaClient.EVENT_BUS.post(new LeaveServerEvent());
    }
}
