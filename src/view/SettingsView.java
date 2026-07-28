package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import model.SettingsModel;

public class SettingsView {

    private String[] options = {"Volume", "Resolution", "Gamma", "Back to Menu"};
    private int selectedIndex = 0;

    public void render(Graphics2D g, SettingsModel settings) {
        // Background
        g.setColor(new Color(16, 62, 161, 200));
        g.fillRect(0, 0, 800, 600);

        // Titel
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.WHITE);
        g.drawString("SETTINGS", 310, 90);

        // Menu
        g.setFont(new Font("Arial", Font.PLAIN, 24));

        for (int i = 0; i < options.length; i++) {
            int y = 180 + (i * 50);

            // Selected item yellow, others white
            if (i == selectedIndex) {
                g.setColor(Color.YELLOW);
                g.drawString("> " + options[i], 200, y);
            } else {
                g.setColor(Color.WHITE);
                g.drawString("  " + options[i], 200, y);
            }

            // Display values from SettingsModel
            if (settings != null) {
                g.setColor(Color.LIGHT_GRAY);
                
                if (i == 0) {
                    g.drawString("< " + settings.getVolume() + "% >", 480, y);
                } else if (i == 1) {
                    g.drawString("< " + settings.getResolution() + " >", 480, y);
                } else if (i == 2) {
                    g.drawString("< " + settings.getGamma() + " >", 480, y);
                }
            }
        }

        // Control instructions
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.GRAY);
        g.drawString("[W/S] Navigate   |   [A/D] Change Value   |   [ESC] Back", 190, 520);
    }

    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
}