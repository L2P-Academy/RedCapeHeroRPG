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
import view.GameView;
import view.InventoryView;
import view.MenuView;
import view.SettingsView;
import view.SaveGameView;

public class GameController {

    private GameView view;
    private GameStateModel modelG;   
    private	PlayerModel modelP;
    private InventoryView inventoryView; // Das Inventar-Fenster
    
    private boolean isDialogActive = false;
    private boolean isInventoryActive = false;
    private boolean isPauseActive = false;

    // Wir bleiben beim gewohnten 2-Parameter-Konstruktor!
    public GameController(GameView view, GameStateModel modelG) {
        this.view = view;
        this.modelG = modelG; 
        this.modelP = modelG.getPlayerModel();
        
        this.inventoryView = new InventoryView();
        this.inventoryView.setVisible(false);

        initGame();
        setupControllerInput();
    }
    
    private void initGame() {
        view.updateCoordinates(modelP.getPlayerPosX(), modelP.getPlayerPosY());
        view.updateXP(modelP.getCurrentXp());
        view.setDialogActive(isDialogActive);
    }

    private void setupControllerInput() {
    	
    	// W = Nach oben (Y wird kleiner)
        view.addKeyBinding(KeyStroke.getKeyStroke("W"), "moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.updatePlayerDirection("W");
                movePlayer(0, 1);
            }
        });

        // S = Nach unten (Y wird größer)
        view.addKeyBinding(KeyStroke.getKeyStroke("S"), "moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.updatePlayerDirection("S");
                movePlayer(0, -1);
            }
        });

        // A = Nach links (X wird kleiner)
        view.addKeyBinding(KeyStroke.getKeyStroke("A"), "moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.updatePlayerDirection("A");
                movePlayer(-1, 0);
            }
        });

        // D = Nach rechts (X wird größer)
        view.addKeyBinding(KeyStroke.getKeyStroke("D"), "moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	view.updatePlayerDirection("D");
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
        
        // pause menu
        view.addKeyBinding(KeyStroke.getKeyStroke("ESCAPE"), "togglePauseMenu", new AbstractAction() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
        		isPauseActive = !isPauseActive;
        		
        		JDialog pauseDialog = new JDialog(view, "PAUSE", true);
        		pauseDialog.setSize(500, 850);
        		pauseDialog.setLocationRelativeTo(view);
        		pauseDialog.setUndecorated(true);
        		
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
						pauseDialog.dispose();
					}
				});

        		JButton saveBtn = new JButton("Speichern");
        		AnimationController.beautifyButton(saveBtn);
        		saveBtn.addActionListener(new ActionListener() {

        			@Override
        			public void actionPerformed(ActionEvent e) {
        				SoundController.playBtnSound();
        				pauseDialog.dispose();
        				
        				javax.swing.SwingUtilities.invokeLater(() -> {
        					SaveGameView saveView = new SaveGameView(modelG);
        					saveView.setVisible(true);
        					saveView.toFront();
        					saveView.requestFocus(); // Macht das PauseMenu wieder zu damit Savegame gezeigt werden kann
        				});
        			}
        		});
        		
        		JButton settingsBtn = new JButton("Einstellungen");
        		AnimationController.beautifyButton(settingsBtn);
        		settingsBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						SoundController.playBtnSound();
						new SettingsView();
						pauseDialog.dispose();
					}
				});
        		
        		JButton returnBtn = new JButton("Hauptmenü");
        		AnimationController.beautifyButton(returnBtn);
        		returnBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						SoundController.playBtnSound();
						new MenuView();
						pauseDialog.dispose();
						view.dispose();						
					}
				});
        		
        		JButton exitBtn = new JButton("Spiel Beenden");
        		AnimationController.beautifyButton(exitBtn);
        		
        		exitBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						SoundController.playBtnSound();
						pauseDialog.dispose();
						view.dispose();
					}
				});
        		// TODO: fix this Label (not showing up)
//        		JLabel titleLbl = new JLabel(new ImageIcon("/backgrounds/pause_label.png"));
//        		titleLbl.setPreferredSize(new Dimension(200, 100));
        		
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
        		
        		pauseDialog.setVisible(true);
        	 }
        });
    }
    


    private void movePlayer(int deltaX, int deltaY) {
    	
        int newX = modelP.getPlayerPosX() + deltaX;
        int newY = modelP.getPlayerPosY() + deltaY;
        
        modelP.setPlayerPosX(newX);
        modelP.setPlayerPosY(newY);

        view.updateCoordinates(newX, newY);
    }
    
}