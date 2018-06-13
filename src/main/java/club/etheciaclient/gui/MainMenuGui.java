package club.etheciaclient.gui;

import club.etheciaclient.EtheciaClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import org.apache.logging.log4j.Level;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainMenuGui extends GuiScreen {

    private ResourceLocation mbackground;

    public MainMenuGui() {
        mbackground = new ResourceLocation("textures/images/background1.png");
    }

    public void initGui() {
        int a = this.height / 4 + 48;

        this.drawMinecraftButtons(a - 10, 24, a);
        this.drawEtheciaButtons();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        this.renderBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawMinecraftButtons(int y_1, int y_2, int b) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, y_1, I18n.format("menu.singleplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, y_1 + y_2, I18n.format("menu.multiplayer")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, b + 72 + 12 + 24 - 5, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, b + 72 + 12 + 24 - 5, 98, 20, I18n.format("menu.quit")));
    }


    private void drawEtheciaButtons() {
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height - 50, "Ethecia Settings"));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height - 25, "Ethecia Website"));
    }

    @Override
    public void actionPerformed(GuiButton button) {

        // Singleplayer
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        // Multiplayer
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        // MC Settings
        if (button.id == 3) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        // Exit
        if (button.id == 4) {
            this.mc.shutdown();
        }

        // Ethecia Settings
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiEtheciaSettings());
        }

        if (button.id == 6) {
            try {
                Desktop.getDesktop().browse(new URI("https://etheciaclient.club"));
            } catch (URISyntaxException e) {
                EtheciaClient.LOGGER.log(Level.ERROR, "Unable to open website + (" + e.getMessage() + ")");
            } catch (IOException e) {
                EtheciaClient.LOGGER.log(Level.ERROR, "Unable to open website (" + e.getMessage() + ")");
            }
        }
    }

    private void renderBackground() {
        ScaledResolution sr = new ScaledResolution(this.mc);
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(mbackground);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(0.0D, (double) sr.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
        worldrenderer.pos((double) sr.getScaledWidth(), (double) sr.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos((double) sr.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
