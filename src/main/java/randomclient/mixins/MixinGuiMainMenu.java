package randomclient.mixins;

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
        Minecraft.getMinecraft().displayGuiScreen(new randomclient.gui.GuiMainMenu());
    }
    /**
     * @author
     */
    @Overwrite
    public void drawScreen(int a, int b, float c) {
    }
}
