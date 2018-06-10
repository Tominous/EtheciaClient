package club.etheciaclient.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class MainMenuGui extends GuiScreen {

    public ResourceLocation mbackground = new ResourceLocation("textures/images/background1.png");

    public MainMenuGui() {

    }

    public void initGui() {
        this.buttonList.add(new GuiButton(100, this.width / 3, this.height - 50, "Ethecia Settings"));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
