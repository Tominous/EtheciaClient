package club.etheciaclient.gui;

import club.etheciaclient.EtheciaClient;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiEtheciaSettings extends GuiScreen {

    private EtheciaClient client;

    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height / 4 + 24, "Toggle Discord Rich Presence"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 4 + 48, "Enable E Override (Restart Client)"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if(button.id == 0) {
            if(client.RPC) {
                client.RPC = false;
            } else {
                client.RPC = true;
            }
        }

        if (button.id == 1) {
            EtheciaClient.EVERYTHING_IS_A_VOWEL = true;
        }
    }

}
