package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import model.GameStateModel;
import model.PlayerModel;
import view.GameView;
import view.InventoryView;

public class GameController {

    private GameView view;
    private GameStateModel modelG;   
    private	PlayerModel modelP;
    private InventoryView inventoryView; // Das Inventar-Fenster
    
    private boolean isDialogActive = false;
    private boolean isInventoryActive = false;

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
    }
    


    private void movePlayer(int deltaX, int deltaY) {
    	
        int newX = modelP.getPlayerPosX() + deltaX;
        int newY = modelP.getPlayerPosY() + deltaY;
        
        modelP.setPlayerPosX(newX);
        modelP.setPlayerPosY(newY);

        view.updateCoordinates(newX, newY);
    }
    
}