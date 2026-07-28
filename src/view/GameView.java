package view;
//Dominik TheEndles
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

//Wenigerklassen
public class GameView extends JFrame {

    private JPanel gameField, dialogBoxPanel, xpHudPanel, lifeHudPanel, abilityHudPanel;
    private JProgressBar healthPoints, abilityPoints, xperiencePoints;

    public GameView() {
        initWindow();
        initComponents();
        setDialogActive(true);
    }

    private void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setAlwaysOnTop(true);
        setSize(1920, 1080);
    }

    public void setDialogActive(boolean isActive) {
        if (dialogBoxPanel != null) {
            dialogBoxPanel.setVisible(isActive);
            revalidate();
            repaint();
        }
    }

    private void initComponents() {
        gameField = new JPanel(new BorderLayout());
        gameField.setBackground(Color.BLACK);

        dialogBoxPanel = createDialogBox();
        xpHudPanel = createXpHud(30);
        
        healthPoints = createProgressBar(100, Color.RED);
        lifeHudPanel = createStatPanel(healthPoints, BorderLayout.WEST);
        
        abilityPoints = createProgressBar(100, Color.BLUE);
        abilityHudPanel = createStatPanel(abilityPoints, BorderLayout.EAST);

	        JPanel statsContainer = new JPanel(new BorderLayout());
	        statsContainer.setOpaque(false);
	        statsContainer.add(lifeHudPanel, BorderLayout.WEST);
	        statsContainer.add(abilityHudPanel, BorderLayout.EAST);
	
	        JPanel bottomContainer = new JPanel(new BorderLayout());
	        bottomContainer.setOpaque(false);
	        bottomContainer.add(statsContainer, BorderLayout.NORTH);
	        bottomContainer.add(dialogBoxPanel, BorderLayout.SOUTH);

        gameField.add(xpHudPanel, BorderLayout.NORTH);
        gameField.add(bottomContainer, BorderLayout.SOUTH);

        add(gameField, BorderLayout.CENTER);
        setVisible(true);
    }

    private JProgressBar createProgressBar(int value, Color color) {
        JProgressBar bar = new JProgressBar(0, 100);
	        bar.setValue(value);
	        bar.setStringPainted(true);
	        bar.setOpaque(false);
	        bar.setBackground(new Color(0, 0, 0, 0));
	        bar.setForeground(color);
	        bar.setBorderPainted(false);
        return bar;
    }

    private JPanel createStatPanel(JProgressBar bar, String position) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
	        panel.setOpaque(false);
	        panel.add(bar, position);
        return panel;
    }

    private JPanel createXpHud(int value) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        	panel.setOpaque(false);

        xperiencePoints = createProgressBar(value, Color.GREEN);

        JLabel xpLabel = new JLabel("XP:");
        	xpLabel.setForeground(Color.WHITE);

        JLabel lvlLabel = new JLabel("LVL: 100");
        	lvlLabel.setForeground(Color.WHITE);
        
        panel.add(xperiencePoints, BorderLayout.CENTER);
        panel.add(xpLabel, BorderLayout.WEST);
        panel.add(lvlLabel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createDialogBox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
	        panel.setPreferredSize(new Dimension(0, 150));
	        panel.setBackground(Color.LIGHT_GRAY);

        JLabel dialogBoxLabel = new JLabel("Dialog:");
	        dialogBoxLabel.setForeground(Color.BLACK);

        panel.add(dialogBoxLabel);
        return panel;
    }



    // Main ist nur zum testen da
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GameView());        

    }
}