package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import model.GameStateModel;
import view.GameView;
import view.InventoryView;

public class GameController {

    private GameView view;
    private GameStateModel model;    
    private InventoryView inventoryView; // Das Inventar-Fenster
    
    private boolean isDialogActive = false;
    private boolean isInventoryActive = false;

    // Wir bleiben beim gewohnten 2-Parameter-Konstruktor!
    public GameController(GameView view, GameStateModel model) {
        this.view = view;
        this.model = model;       
        
        this.inventoryView = new InventoryView();
        this.inventoryView.setVisible(false);

        initGame();
        setupControllerInput();
    }
    
    private void initGame() {
        view.updateCoordinates(model.getTileX(), model.getTileY());
        view.updateXP(model.getScore());
        view.setDialogActive(isDialogActive);
    }

    private void setupControllerInput() {
        
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
}