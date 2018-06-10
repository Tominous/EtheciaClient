package club.etheciaclient.mixins;

import club.etheciaclient.gui.MainMenuGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu {
    /**
     * @author
     */
    @Overwrite
    public void initGui() {
        Minecraft.getMinecraft().displayGuiScreen(new MainMenuGui());
    }
    /**
     * @author
     */
    @Overwrite
    public void drawScreen(int a, int b, float c) {
    }
}
