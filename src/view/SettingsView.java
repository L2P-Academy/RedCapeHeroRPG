package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class SettingsView extends JFrame {

    // Menu options and selection index
    private String[] options = {"Volume", "Resolution", "Gamma", "Back to Menu"};
    private int selectedIndex = 0;

    public SettingsView() {
        setTitle("Red Cape Hero - Settings");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window on screen
        setAlwaysOnTop(true); // Keep window on top
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame

        // KeyListener for menu navigation (Arrow keys + Enter/ESC)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // Navigate UP
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                    selectedIndex--;
                    if (selectedIndex < 0) {
                        selectedIndex = options.length - 1;
                    }
                    repaint(); // Redraw UI
                }

                // Navigate DOWN
                if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                    selectedIndex++;
                    if (selectedIndex >= options.length) {
                        selectedIndex = 0;
                    }
                    repaint(); // Redraw UI
                }              
                if (keyCode == KeyEvent.VK_ENTER) {
                    selectOption(); // Confirm selection with ENTER
                }              
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    dispose(); // Close the settings window
                }
            }
        });
        
        // MouseListener for clicking on menu items
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Check click coordinates for each option bounding box
                for (int i = 0; i < options.length; i++) {
                    int y = 200 + (i * 50);
                    // Define click area for each menu text item
                    Rectangle bounds = new Rectangle(240, y - 25, 200, 35);

                    if (bounds.contains(mouseX, mouseY)) {
                        selectedIndex = i;
                        repaint();
                        selectOption();
                        break;
                    }
                }
            }
        });

        setFocusable(true); // Required to register key events
        setVisible(true); // Display window
    }

    // Handles the active menu selection
    private void selectOption() {
        switch (selectedIndex) {
            case 0: // Volume
            	System.out.println("Volume clicked!");
            	break;
            case 1: // Resolution
            	System.out.println("Resolution clicked!");
                break;
            case 2: // Gamma
            	System.out.println("Gamma clicked!");
                break;
            case 3: // Back to Menu
                dispose(); // Close frame and return to main menu
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // 1. Render background color (Dark Blue)
        g2d.setColor(new Color(16, 62, 161));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 2. Render title
        g2d.setFont(new Font("Calibri", Font.BOLD, 36));
        g2d.setColor(Color.WHITE);
        g2d.drawString("SETTINGS", 310, 100);

        // 3. Render menu items
        g2d.setFont(new Font("Calibri", Font.PLAIN, 24));
        for (int i = 0; i < options.length; i++) {
            int y = 200 + (i * 50);

            if (i == selectedIndex) {
                g2d.setColor(new Color(37, 232, 7)); // Bright green for active option
                g2d.drawString("> " + options[i], 250, y);
            } else {
                g2d.setColor(Color.WHITE);
                g2d.drawString("  " + options[i], 250, y);
            }
        }
    }
}