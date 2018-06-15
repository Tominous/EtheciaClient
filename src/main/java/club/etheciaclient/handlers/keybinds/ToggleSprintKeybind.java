package club.etheciaclient.handlers.keybinds;

import club.etheciaclient.mixins.MixinKeybind;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class ToggleSprintKeybind extends Keybind {

    private boolean toggled;

    public ToggleSprintKeybind() {
        super("Toggle Sprint", Keyboard.KEY_J);
    }

    @Override
    public void onPress() {
        if (toggled) {
            toggled = false;
            ((MixinKeybind) Minecraft.getMinecraft().gameSettings.keyBindSprint).setPressed(false);
        } else {
            toggled = true;
            ((MixinKeybind) Minecraft.getMinecraft().gameSettings.keyBindSprint).setPressed(true);
        }
    }
}
