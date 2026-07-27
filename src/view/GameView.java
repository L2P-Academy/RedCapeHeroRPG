 package view;
//Dominik TheEndles
 
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameView {
    
	private JFrame initFrame;
	private JPanel gameField, hud;
	
	//constructor
    public GameView(){
    	initWindow();
    	initComponents();
    }
    
    //Erstellung des Fensters
    private void initWindow() {
    	initFrame = new JFrame("RedCapreHeroRPG");
    	initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	initFrame.setSize(1920, 1080);
    	initFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    };
    
    private void initComponents() {
    	gameField = new GameFieldPanel();
    	hud = new HudPanel();
    	initFrame.add(gameField);
    	initFrame.add(hud);
    	initFrame.setVisible(true);
    };

    
    //Erstellung des Spielfelds
    public class GameFieldPanel extends JPanel{
    	public  GameFieldPanel() {
    		setBackground(Color.BLACK);
		}
    };
    
    //Erstellung der Hud Anzeige
    public class HudPanel extends JPanel{
    	public HudPanel() {

    	}
    	
    };
    
    //Handler für den Input der Tastatur usw.
    public class InputHandler{};
    
    //Updater, damit die Grafik geupdatet werden
    public class GameUpdater{};
          	

    

    // Main ist nur zum testen da
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GameView());        

    }
}
