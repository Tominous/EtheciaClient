package club.etheciaclient.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiMainMenu extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        Gui.drawRect(0, 0, Display.getWidth(), Display.getHeight(), Color.DARK_GRAY.getRGB());
    }
}
