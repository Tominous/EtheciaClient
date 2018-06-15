package club.etheciaclient.handlers.keybinds;

import net.minecraft.client.settings.KeyBinding;

public class Keybind extends KeyBinding {

    private int key;
    private boolean pressed;

    public Keybind(String description, int keyCode) {
        super(description, keyCode, "Ethecia Keybinds");
        this.key = keyCode;
    }

    @Override
    public int getKeyCode() {
        return this.key;
    }

    @Override
    public void setKeyCode(int keyC) {
        this.key = keyC;

        super.setKeyCode(key);
    }

    public boolean wasPressed() {
        return this.pressed;
    }

    public void onPress() {
        // We want these to be changed
    }

    public void onRelease() {
        // We want these to be changed
    }
}
