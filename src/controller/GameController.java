 package controller;
//Dominik TheEndles
import model.GameStateModel;
import view.GameView;


public class GameController  {

    private GameView view;
    private GameStateModel model;    

    public GameController (GameView view, GameStateModel model) {
        this.view = view;
        this.model = model;       

    };

} 