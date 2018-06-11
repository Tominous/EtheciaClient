package club.etheciaclient.mixins;

import club.etheciaclient.gui.GuiEtheciaSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameMenu.class)
public class MixinPauseGui extends GuiScreen {
    @Inject(method = "initGui", at = @At("RETURN"))
    private void initGui(CallbackInfo ci) {
        GuiButton oldButton = this.buttonList.remove(3);
        GuiButton newButton = new GuiButton(10, oldButton.xPosition, oldButton.yPosition, oldButton.getButtonWidth(), 20, "Ethecia Settings");
        this.buttonList.add(newButton);
    }
    @Inject(method = "actionPerformed", at = @At("HEAD"))
    private void actionPerformed(GuiButton button, CallbackInfo ci) {
        if (button.id == 10 && Minecraft.getMinecraft().theWorld.isRemote)
            Minecraft.getMinecraft().displayGuiScreen(new GuiEtheciaSettings());
    }
}
