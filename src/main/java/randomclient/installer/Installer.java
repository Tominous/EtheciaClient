package randomclient.installer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.io.IOException;

public class Installer extends JFrame {

    private static final int width = 600;
    private static final int height = 260;

    Installer() {
        super.frameInit();

        setSize(width, height);
        initComponents();
        setUndecorated(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("RandomClient Installer");

        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {

        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, MainInstaller.class.getResourceAsStream("assets/randomclient/font/Roboto-Regular.ttf")).deriveFont(15);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            font = Font.getFont("Arial");
            e.printStackTrace();
        }

        JPanel panel = new JPanel();

        panel.setBounds(0, 0, width, 30);

        JButton testButton = new JButton("test");
        testButton.setUI(new BasicButtonUI());
        testButton.setFocusPainted(false);
        testButton.setFont(font);
        testButton.setBorderPainted(false);
        testButton.setForeground(new Color(60, 60, 60));
    }
}
