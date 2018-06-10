package randomclient.installer;

import javax.swing.*;

public class Installer extends JFrame {

    private static final int width = 600;
    private static final int height = 260;

    public Installer() {
        super.frameInit();
        setSize(width, height);
        setResizable(false);
        setTitle("RandomClient Installer");
    }
}
