package view;
//Dominik TheEndles
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import controller.AnimationController;

//Wenigerklassen
public class GameView extends JFrame {

    private JPanel gameField, dialogBoxPanel, xpHudPanel, lifeHudPanel, abilityHudPanel, miniMap;
    private JLabel timeStamp, coordinates, npcPicture ;
    private JProgressBar healthPoints, abilityPoints, xperiencePoints;
    private boolean isDialogActive = false;

    public GameView() {
        initWindow();
        initComponents();
        setDialogActive(isDialogActive); 
        
        setupKeyBindings(); 
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

       
    //Setzt alles zusammen zu einem HUD
    private void initComponents() {
        Font gameFont = AnimationController.loadDungeonFont(42f);
        gameField = new JPanel(new BorderLayout());
        gameField.setBackground(Color.BLACK);
        
	        timeStamp = new JLabel("00:00:00"); // Ist nur Testweise erstmal vorhanden
	        timeStamp.setForeground(Color.WHITE);
	        timeStamp.setFont(gameFont);
	        
	        coordinates = new JLabel("X: 0 || Y: 0"); // Auch hier nur zum anzeige test erstmal 
	        coordinates.setForeground(Color.WHITE);
	        coordinates.setFont(gameFont);
	        
	        miniMap = new JPanel();
	        miniMap.setPreferredSize(new Dimension(200, 200)); // Beispielgröße
	        miniMap.setBackground(Color.DARK_GRAY);
	        miniMap.setOpaque(true);

	        dialogBoxPanel = createDialogBox();	        
	        
		        // Container Erstellung
		        healthPoints = createProgressBar(100, 50, 300, Color.RED);
		        lifeHudPanel = createStatPanel(healthPoints, BorderLayout.WEST);
		        
		        abilityPoints = createProgressBar(100, 50, 300, Color.BLUE);
		        abilityHudPanel = createStatPanel(abilityPoints, BorderLayout.EAST);
		
		        // Container für die Statuswerte
		        JPanel statsContainer = new JPanel(new BorderLayout());
		        statsContainer.setOpaque(false);
		        statsContainer.add(lifeHudPanel, BorderLayout.WEST);
		        statsContainer.add(abilityHudPanel, BorderLayout.EAST);
		
		        // Container mit Statuswerte und Dialogbox
		        JPanel bottomContainer = new JPanel(new BorderLayout());
		        bottomContainer.setOpaque(false);
		        bottomContainer.add(statsContainer, BorderLayout.NORTH);
		        bottomContainer.add(dialogBoxPanel, BorderLayout.SOUTH);	        
	        
		        xpHudPanel = createXpHud(30);    	        
		        
			        // Container für die linke Seite, damit die Zeit ganz oben am Rand klebt
			        JPanel leftSideContainer = new JPanel(new BorderLayout());
			        leftSideContainer.setOpaque(false);
			        leftSideContainer.add(timeStamp, BorderLayout.NORTH);
			
			        // Ein extra Container für die rechte Seite, der Minimap und Koordinaten stapelt
			        JPanel rightSideContainer = new JPanel(new BorderLayout());
			        rightSideContainer.setOpaque(false);
			        rightSideContainer.add(miniMap, BorderLayout.NORTH);
			        rightSideContainer.add(coordinates, BorderLayout.SOUTH);
			        
			        // Erstellt den Container für die obere Leiste (Zeit links, Map rechts)
			        JPanel topStatsContainer = new JPanel(new BorderLayout());
			        topStatsContainer.setOpaque(false);
			        topStatsContainer.add(leftSideContainer, BorderLayout.WEST);
			        topStatsContainer.add(rightSideContainer, BorderLayout.EAST);
			            
			        // Container mit XP Leiste und den Top-Stats
			        JPanel topContainer = new JPanel(new BorderLayout());
			        topContainer.setOpaque(false);
			        topContainer.add(xpHudPanel, BorderLayout.NORTH);
			        topContainer.add(topStatsContainer, BorderLayout.CENTER); 

        // Füge alles dem Spielfeld hinzu
        gameField.add(topContainer, BorderLayout.NORTH);
        gameField.add(bottomContainer, BorderLayout.SOUTH);

        add(gameField, BorderLayout.CENTER);
        setVisible(true);
    }

    //
    
    //Erstellt eine Progressbar
    private JProgressBar createProgressBar(int value, int heigth, int width, Color color) {
        JProgressBar bar = new JProgressBar(0, 100);
	        bar.setValue(value);
	        bar.setStringPainted(true);
	        bar.setOpaque(false);
	        bar.setPreferredSize(new Dimension(width,heigth));
	        bar.setBackground(new Color(0, 0, 0, 0));
	        bar.setForeground(color);
	        bar.setBorderPainted(false);
        return bar;
    }

    //Setzt die Status Panels in der entsprechende Position
    private JPanel createStatPanel(JProgressBar bar, String position) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
	        panel.setOpaque(false);
	        panel.add(bar, position);
        return panel;
    }

    // Erstellt ein XP Hud und nutzt die Progressbar Methode
    private JPanel createXpHud(int value) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        	panel.setOpaque(false);

        xperiencePoints = createProgressBar(value,20, 1720, Color.GREEN);

        JLabel xpLabel = new JLabel("XP:");
        	xpLabel.setForeground(Color.WHITE);

        JLabel lvlLabel = new JLabel("LVL: 100");
        	lvlLabel.setForeground(Color.WHITE);
        
        panel.add(xperiencePoints, BorderLayout.CENTER);
        panel.add(xpLabel, BorderLayout.WEST);
        panel.add(lvlLabel, BorderLayout.EAST);

        return panel;
    }

    //Erstellt die Box für Dialoge mit NPC Bild
    private JPanel createDialogBox() {

        JPanel panel = new JPanel(new BorderLayout(15, 10));
	        panel.setPreferredSize(new Dimension(0, 150));
	        panel.setBackground(Color.LIGHT_GRAY);


        JLabel dialogBoxLabel = new JLabel(" Hier könnte ein Dialogtext stehen, wenn ich dafür bezahöt werden würde.");
        	dialogBoxLabel.setForeground(Color.BLACK);


        npcPicture = new JLabel("NPC BILD", JLabel.CENTER);
        npcPicture.setPreferredSize(new Dimension(130, 130));
        npcPicture.setOpaque(true);
        npcPicture.setBackground(Color.DARK_GRAY);
        npcPicture.setForeground(Color.WHITE);

        panel.add(dialogBoxLabel, BorderLayout.CENTER);
        panel.add(npcPicture, BorderLayout.EAST);
        
        return panel;
    }

 // Metthode nur zum Testen der Dialog Box, erstellung erfolgte von der KI 
    private void setupKeyBindings() {
        // Verbindet die Taste "E" mit dem Befehlswort "toggleDialog"
        gameField.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "toggleDialog");
        
        // Sagt dem Programm, was bei "toggleDialog" passieren soll
        gameField.getActionMap().put("toggleDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kehrt den aktuellen Wert um (aus false wird true, aus true wird false)
                isDialogActive = !isDialogActive; 
                
                // Ruft deine bereits vorhandene Methode auf
                setDialogActive(isDialogActive);  
            }
        });
    }

    // Main ist nur zum testen da
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GameView());        

    }
}