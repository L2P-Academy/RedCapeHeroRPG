package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.GameStateModel;
import model.PlayerModel;
import view.BackGroundPanel;
import view.GamePanel;
import view.GameView;
import view.InventoryView;
import view.ShopView;
import view.MenuView;
import view.SettingsView;
import view.SaveGameView;

public class GameController {

    private GameView view;
    private GamePanel gamePanel;
    private GameStateModel modelG;   
    private	PlayerModel modelP;
    private InventoryView inventoryView; // Das Inventar-Fenster
    private ShopView shopView;
    private JDialog pauseDialog;
    
    private boolean isDialogActive = false;
    private boolean isInventoryActive = false;
    private boolean isShopActive = false;
    private boolean isPauseActive = false;

    // Wir bleiben beim gewohnten 2-Parameter-Konstruktor!
    public GameController(GameView view, GameStateModel modelG) {
        this.view = view;
        this.gamePanel = view.getGameField();
        this.modelG = modelG; 
        this.modelP = modelG.getPlayerModel();
        
        this.inventoryView = new InventoryView();
        this.inventoryView.setVisible(false);
        
        this.shopView = new ShopView();
        this.shopView.setVisible(false);

        initGame();
        initPauseMenu();
        setupControllerInput();
    }
    
    private void initGame() {
    	int playerX = modelP.getPlayerPosX();
    	int playerY = modelP.getPlayerPosY();
    	
        view.updateCoordinates(modelP.getPlayerPosX(), modelP.getPlayerPosY());
        view.updateXP(modelP.getCurrentXp());
        view.setDialogActive(isDialogActive);
        
        javax.swing.SwingUtilities.invokeLater(() -> {
        	gamePanel.setPlayerTilePosition(playerX, playerY);
        });
    }

    private void setupControllerInput() {
    	
    	// W = Nach oben (Y wird kleiner)
        view.addKeyBinding(KeyStroke.getKeyStroke("W"), "moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gamePanel.updatePlayerDirection("W");
                movePlayer(0, -1);
            }
        });

        // S = Nach unten (Y wird größer)
        view.addKeyBinding(KeyStroke.getKeyStroke("S"), "moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gamePanel.updatePlayerDirection("S");
                movePlayer(0, 1);
            }
        });

        // A = Nach links (X wird kleiner)
        view.addKeyBinding(KeyStroke.getKeyStroke("A"), "moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gamePanel.updatePlayerDirection("A");
                movePlayer(-1, 0);
            }
        });

        // D = Nach rechts (X wird größer)
        view.addKeyBinding(KeyStroke.getKeyStroke("D"), "moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gamePanel.updatePlayerDirection("D");
                movePlayer(1, 0);
            }
        });
        
        // Taste "E" für den Dialog
        view.addKeyBinding(KeyStroke.getKeyStroke("E"), "toggleDialog", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDialogActive = !isDialogActive; 
                view.setDialogActive(isDialogActive);  
                System.out.println("Dialog-Status: " + isDialogActive);
            }
        });
        
        // Taste "I" für das Inventar
        view.addKeyBinding(KeyStroke.getKeyStroke("I"), "toggleInventory", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isInventoryActive = !isInventoryActive;                

                inventoryView.setVisible(isInventoryActive);
                if (isInventoryActive) {
                    inventoryView.toFront();
                }
                
                System.out.println("Inventar-Status: " + isInventoryActive);
            }
        });
        // Taste "B" für den Shop
        view.addKeyBinding(KeyStroke.getKeyStroke("B"), "toggleShop", new AbstractAction() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		isShopActive = !isShopActive;                
        		
        		shopView.setVisible(isShopActive);
        		if (isShopActive) {
        			shopView.toFront();
        		}
        		
        		System.out.println("Inventar-Status: " + isShopActive);
        	}
        });
        
        // pause menu
     // pause menu
        view.addKeyBinding(KeyStroke.getKeyStroke("ESCAPE"), "togglePauseMenu", new AbstractAction() {
             @Override
             public void actionPerformed(ActionEvent e) {
                isPauseActive = !isPauseActive;
                
                pauseDialog.setVisible(isPauseActive);
                if (isPauseActive) {
                    pauseDialog.toFront();
                }
             }
        });
    }
    

    private void initPauseMenu() {
        // "false" statt "true", damit es das Hauptfenster nicht blockiert
        pauseDialog = new JDialog(view, "PAUSE", false);
        pauseDialog.setSize(500, 850);
        pauseDialog.setLocationRelativeTo(view);
        pauseDialog.setUndecorated(true);
        pauseDialog.setFocusableWindowState(false); // <-- Verhindert den Fokus-Klau!

        // background
        JPanel bgPanel = new JPanel();
        bgPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        pauseDialog.setContentPane(bgPanel);

        // pause menu components
        JPanel contentPnl = new JPanel();
        contentPnl.setOpaque(false);
        contentPnl.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        contentPnl.setLayout(new BorderLayout(0, 20));

        JPanel menuPnl = new JPanel(new GridLayout(6, 1));
        menuPnl.setOpaque(false);

        JButton resumeBtn = new JButton("Fortsetzen");
        AnimationController.beautifyButton(resumeBtn);
        resumeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundController.playBtnSound();
                isPauseActive = false; // Status updaten
                pauseDialog.setVisible(false); // Verstecken statt dispose()
            }
        });

        JButton saveBtn = new JButton("Speichern");
        AnimationController.beautifyButton(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundController.playBtnSound();
                isPauseActive = false;
                pauseDialog.setVisible(false);
                
                javax.swing.SwingUtilities.invokeLater(() -> {
                    SaveGameView saveView = new SaveGameView(modelG);
                    saveView.setVisible(true);
                    saveView.toFront();
                    saveView.requestFocus();
                });
            }
        });

        JButton settingsBtn = new JButton("Einstellungen");
        AnimationController.beautifyButton(settingsBtn);
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundController.playBtnSound();
                isPauseActive = false;
                pauseDialog.setVisible(false);
                new SettingsView();
            }
        });

        JButton returnBtn = new JButton("Hauptmenü");
        AnimationController.beautifyButton(returnBtn);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundController.playBtnSound();
                pauseDialog.setVisible(false);
                new MenuView();
                view.dispose();						
            }
        });

        JButton exitBtn = new JButton("Spiel Beenden");
        AnimationController.beautifyButton(exitBtn);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundController.playBtnSound();
                System.exit(0);
            }
        });

        JLabel titleLbl = new JLabel("PAUSE");
        titleLbl.setForeground(Color.RED);
        titleLbl.setFont(AnimationController.loadDungeonFont(82f));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        menuPnl.add(resumeBtn);
        menuPnl.add(saveBtn);
        menuPnl.add(settingsBtn);
        menuPnl.add(returnBtn);
        menuPnl.add(exitBtn);

        contentPnl.add(titleLbl, BorderLayout.NORTH);
        contentPnl.add(menuPnl, BorderLayout.CENTER);

        bgPanel.add(contentPnl, BorderLayout.CENTER);
        
    }
    
    
    private void movePlayer(int deltaX, int deltaY) {
    	
        int newX = modelP.getPlayerPosX() + deltaX;
        int newY = modelP.getPlayerPosY() + deltaY;
        
        modelP.setPlayerPosX(newX);
        modelP.setPlayerPosY(newY);

        gamePanel.setPlayerTilePosition(newX, newY);
        view.updateCoordinates(newX, newY);
    }
    
}