package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AnimationController; 

public class SettingsView extends JFrame {

    // Menu options 
    private String[] options = {"Lautstärke", "Auflösung", "Helligkeit", "Zurück zum Hauptmenü"};
    private int selectedIndex = 0;
    
    // Color palette
    private static final Color BG_COLOR = new Color(235, 235, 235);
    private static final Color BUTTON_RED = new Color(189, 2, 0);
    private static final Color HOVER_COLOR = new Color(237, 158, 12); 

    public SettingsView() {
        setTitle("Red Cape Hero - Settings");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window on screen
        setUndecorated(true);         // Borderless frame like ShopView
        setAlwaysOnTop(true);        // Keep window on top
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Menu navigation
        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background
                g2d.setColor(BG_COLOR);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Outer red border
                g2d.setColor(BUTTON_RED);
                g2d.setStroke(new BasicStroke(6));
                g2d.drawRect(3, 3, getWidth() - 6, getHeight() - 6);

                // Title
                Font titleFont = AnimationController.loadDungeonFont(48f);
                g2d.setFont(titleFont);
                g2d.setColor(BUTTON_RED);
                FontMetrics fmTitle = g2d.getFontMetrics();
                int titleX = (getWidth() - fmTitle.stringWidth("EINSTELLUNGEN")) / 2;
                g2d.drawString("EINSTELLUNGEN", titleX, 100);

                // Menu Buttons
                Font itemFont = AnimationController.loadDungeonFont(32f);
                g2d.setFont(itemFont);
                FontMetrics fmItem = g2d.getFontMetrics();

                int buttonWidth = 400;
                int buttonHeight = 60;
                int startX = (getWidth() - buttonWidth) / 2;

                for (int i = 0; i < options.length; i++) {
                    int buttonY = 160 + (i * 70);

                    if (i == selectedIndex) {
                        g2d.setColor(HOVER_COLOR);
                    } else {
                        g2d.setColor(BUTTON_RED);
                    }
                    g2d.fillRect(startX, buttonY, buttonWidth, buttonHeight);

                    g2d.setColor(HOVER_COLOR);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(startX, buttonY, buttonWidth, buttonHeight);

                    g2d.setColor(Color.BLACK);
                    int textX = startX + (buttonWidth - fmItem.stringWidth(options[i])) / 2;
                    int textY = buttonY + ((buttonHeight - fmItem.getHeight()) / 2) + fmItem.getAscent();

                    g2d.drawString(options[i], textX, textY);
                }
            }
        };
        setContentPane(drawPanel);
        // KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                    selectedIndex--;
                    if (selectedIndex < 0) {
                        selectedIndex = options.length - 1;
                    }
                    drawPanel.repaint();
                }

                if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                    selectedIndex++;
                    if (selectedIndex >= options.length) {
                        selectedIndex = 0;
                    }
                    drawPanel.repaint();
                }              
                if (keyCode == KeyEvent.VK_ENTER) {
                    SettingsView.this.selectOption();
                }              
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }
        });

        // MouseListener
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();                
                int buttonWidth = 400;
                int startX = (getWidth() - buttonWidth) / 2;

                for (int i = 0; i < options.length; i++) {
                    int buttonY = 160 + (i * 70);                    
                    Rectangle bounds = new Rectangle(startX, buttonY, buttonWidth, 60);

                    if (bounds.contains(mouseX, mouseY)) {
                        if (selectedIndex != i) {
                            selectedIndex = i;
                            drawPanel.repaint();
                        }
                        break;
                    }
                } 
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                selectOption();
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        setFocusable(true);
        setVisible(true);
    } 

    // Handles the active menu selection
    private void selectOption() {
        switch (selectedIndex) {
            case 0:
                System.out.println("Lautstärke ausgewählt!");
                break;
            case 1:
                System.out.println("Auflösung ausgewählt!");
                break;
            case 2:
                System.out.println("Helligkeit ausgewählt!");
                break;
            case 3:
                dispose();
                break;
        }
    }

}