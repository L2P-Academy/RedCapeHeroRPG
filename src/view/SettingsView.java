package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

import model.SettingsModel;

public class SettingsView extends JFrame {

    private String[] options = {"Volume", "Resolution", "Gamma", "Back to Menu"};
    private int selectedIndex = 0;

    // Constructor
    public SettingsView() {
        setTitle("Red Cape Hero - Settings");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window on screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setVisible(true); // Make window visible
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background
        g2d.setColor(new Color(16, 62, 161));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Titel
        g2d.setFont(new Font("Calibri", Font.BOLD, 36));
        g2d.setColor(Color.WHITE);
        g2d.drawString("SETTINGS", 310, 100);

        // Menu
        g2d.setFont(new Font("Calibri", Font.PLAIN, 24));
        for (int i = 0; i < options.length; i++) {
            int y = 200 + (i * 60);

            if (i == selectedIndex) {
                g2d.setColor(new Color(37, 232, 7)); 
                g2d.drawString("> " + options[i], 250, y);
            } else {
                g2d.setColor(Color.WHITE);
                g2d.drawString("  " + options[i], 250, y);
            }
        }
    }

    // Zum Testen
    public static void main(String[] args) {
        new SettingsView();
    }
}