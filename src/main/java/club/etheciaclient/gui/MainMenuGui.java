package club.etheciaclient.gui;

import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;

public class MainMenuGui extends GuiScreen {

    public ResourceLocation mbackground;

    public MainMenuGui() {
        mbackground = new ResourceLocation("textures/images/background1.png");
    }

    public void initGui() {
        int a = this.height / 4 + 48;

        this.drawMultiSingleButtons(a - 10, 24);
        this.drawQuitOptionsButtons(a);
        this.drawEtheciaButtons();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void drawMultiSingleButtons(int y_1, int y_2) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, y_1, I18n.format("menu.singleplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, y_1 + y_2, I18n.format("menu.multiplayer")));
    }

    public void drawQuitOptionsButtons(int b) {
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, b + 72 + 12 + 24 - 5, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, b + 72 + 12 + 24 - 5, 98, 20, I18n.format("menu.quit")));
    }

    public void drawEtheciaButtons() {
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height - 50, "Ethecia Settings"));
    }

    @Override
    public void actionPerformed(GuiButton button) {

        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 3) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 4) {
            this.mc.shutdown();
        }

        if(button.id == 5) {
            this.mc.displayGuiScreen(new GuiEtheciaSettings());
        }
    }

}
