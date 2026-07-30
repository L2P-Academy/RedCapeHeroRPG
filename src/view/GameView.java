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
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.net.URL;

import controller.AnimationController;

//Wenigerklassen
public class GameView extends JFrame {

    private JPanel dialogBoxPanel, xpHudPanel, lifeHudPanel, abilityHudPanel, miniMap;
    private GamePanel gameField;
    private JLabel coordinates, npcPicture, playerSpriteLabel;
    public JLabel timeStamp;
    private JProgressBar healthPoints, abilityPoints, xperiencePoints;
    private boolean isDialogActive = false;
    private Timer gameTimer;
    private int elapsedSeconds;

    public GameView() {
        initWindow();
        initComponents();        
        startGameTimer();
        setDialogActive(isDialogActive);
    }
    
    
    private void initWindow() {
    	setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setAlwaysOnTop(true); // Vorrübergehend auskommenbtiert umd das Inventar zu testen
        setSize(1920, 1080);
    }
    
	public void startGameTimer() {
		gameTimer = new Timer(1000, e -> {
			elapsedSeconds++;
			int hours = elapsedSeconds / 3600;
			int minutes = elapsedSeconds / 60;
			int seconds = elapsedSeconds % 60;
			timeStamp.setText(String.format("%02d:%02d:%02d",hours, minutes, seconds));
		});
		gameTimer.start();
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
        gameField = new GamePanel();
        
	        timeStamp = new JLabel("00:00:00"); // Ist nur Testweise erstmal vorhanden
	        timeStamp.setForeground(Color.WHITE);
	        timeStamp.setFont(gameFont);
	        
	        coordinates = new JLabel();
	        coordinates.setForeground(Color.WHITE);
	        coordinates.setFont(gameFont);
	        coordinates.setHorizontalAlignment(JLabel.CENTER);
	        
	        miniMap = new JPanel();
	        miniMap.setPreferredSize(new Dimension(200, 200)); // Beispielgröße
	        miniMap.setBackground(Color.DARK_GRAY);
	        miniMap.setOpaque(true);

	        dialogBoxPanel = createDialogBox();	        
	        
		        // Container Erstellung
		        healthPoints = createProgressBar(100, 50, 300, Color.RED);
		        lifeHudPanel = createStatPanel(healthPoints, BorderLayout.WEST);
		        
		        abilityPoints = createProgressBar(100, 50, 300, Color.GREEN);
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
	        
		        xpHudPanel = createXpHud(100);    	        
		        
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

			        	//Container um verschiedene Layer zu erstellen
				        JPanel hudLayer = new JPanel(new BorderLayout());
				        hudLayer.setOpaque(false);
				        hudLayer.add(topContainer, BorderLayout.NORTH);
				        hudLayer.add(bottomContainer, BorderLayout.SOUTH);
	
				        //Ist dafür da damit man sich frei bewegen kann
				        gameField.setLayout(null);
	
				
				        JPanel masterContainer = new JPanel() {
                            @Override
                            public boolean isOptimizedDrawingEnabled() {
                                return false; 
                            }
                        };
                        masterContainer.setLayout(new javax.swing.OverlayLayout(masterContainer));    
                        masterContainer.add(hudLayer);
                        masterContainer.add(gameField);
                        
			        setLayout(new BorderLayout());
			        add(masterContainer, BorderLayout.CENTER);
			        
			        setVisible(true);
    }

 
    /**
     * Erstellt und konfiguriert eine ProgressBar.
     * 
     * @param value  der initiale Wert, der in der ProgressBar angezeigt werden soll
     * @param height die Höhe der ProgressBar
     * @param width  die Breite der ProgressBar
     * @param color  die Vordergrundfarbe der ProgressBar
     * @return die fertig konfigurierte JProgressBar-Instanz
     * @author Dominik
     */
        
    private JProgressBar createProgressBar(int value, int height, int width, Color color) {
        JProgressBar bar = new JProgressBar(0, 100);
        Font gameFont = AnimationController.loadDungeonFont(36f);
	        bar.setValue(value);
	        bar.setStringPainted(true);
	        bar.setOpaque(false);
	        bar.setPreferredSize(new Dimension(width,height));
	        bar.setBackground(new Color(0, 0, 0, 0));
	        bar.setForeground(color);
	        bar.setBorderPainted(false);
	        bar.setFont(gameFont);
	        
		        updateProgressBarText(bar);		        
		        bar.addChangeListener(event -> {
		        	JProgressBar source = (JProgressBar) event.getSource();
			        updateProgressBarText(source);
		        });
	        
        return bar;
    }

    private void updateProgressBarText(JProgressBar bar) {
		bar.setString(bar.getValue() + " %");		
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
    	Font gameFont = AnimationController.loadDungeonFont(24f);
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        	panel.setOpaque(false);

        xperiencePoints = createProgressBar(value,20, 1720, Color.GREEN);

        JLabel xpLabel = new JLabel("XP:");
        	xpLabel.setForeground(Color.WHITE);
        	xpLabel.setFont(gameFont);


        JLabel lvlLabel = new JLabel("LVL: 100");
        	lvlLabel.setForeground(Color.WHITE);
        	lvlLabel.setFont(gameFont);
        
        panel.add(xperiencePoints, BorderLayout.CENTER);
        panel.add(xpLabel, BorderLayout.WEST);
        panel.add(lvlLabel, BorderLayout.EAST);

        return panel;
    }

    //Erstellt die Box für Dialoge mit NPC Bild
    private JPanel createDialogBox() {
    	Font gameFont = AnimationController.loadDungeonFont(36f);
        JPanel panel = new JPanel(new BorderLayout(15, 10));
	        panel.setPreferredSize(new Dimension(0, 150));
	        panel.setBackground(Color.LIGHT_GRAY);


        JLabel dialogBoxLabel = new JLabel(" Hier steht ein Text zum testen");
        	dialogBoxLabel.setForeground(Color.BLACK);
        	dialogBoxLabel.setFont(gameFont);


        npcPicture = new JLabel("NPC BILD", JLabel.CENTER);
        npcPicture.setPreferredSize(new Dimension(200, 200));
        npcPicture.setOpaque(true);
        npcPicture.setBackground(Color.DARK_GRAY);
        npcPicture.setForeground(Color.WHITE);

        panel.add(dialogBoxLabel, BorderLayout.CENTER);
        panel.add(npcPicture, BorderLayout.EAST);
        
        return panel;
    }

    
 // Aktualisiert die Koordinaten-Anzeige
    public void updateCoordinates(int x, int y) {
        if (coordinates != null) {
            coordinates.setText("X: " + x + " || Y: " + y);
            if (playerSpriteLabel != null) {
                int tileSize = 64;
                int pixelX = x * tileSize;
                int pixelY = y * tileSize;
                
                playerSpriteLabel.setLocation(pixelX, pixelY);
            }
        }
    }

    // Aktualisiert die XP-Leiste (Wir nutzen hier Testweise den Score)
    public void updateXP(int xp) {
        if (xperiencePoints != null) {
            xperiencePoints.setValue(xp);
        }
    }   
        
    // Erlaubt es dem Controller, Tastenbefehle an das Spielfeld zu binden
    public void addKeyBinding(KeyStroke keyStroke, String actionName, AbstractAction action) {
        gameField.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionName);
        gameField.getActionMap().put(actionName, action);
    }


	public GamePanel getGameField() {
		return gameField;
	}


	public void setGameField(GamePanel gameField) {
		this.gameField = gameField;
	}    
}