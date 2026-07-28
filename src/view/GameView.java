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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GameView extends JFrame{
    
    private JPanel gameField, dialogBoxPanel, miniMap, XpHudPanel;
    private JLabel time, coordinates, playerName, level;
    private JProgressBar healthPoints, abilityPoints, xperiencePoints;
    private JTextField dialogBoxField;
    
    //constructor
    public GameView(){
        initWindow();
        initComponents();
    }
    
    //Erstellung des Fensters
    private void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setAlwaysOnTop(true);
        //Anpassung am den Settings noch überarbeiten sobald Settings fertig sind 
        setSize(1920, 1080);
        
    };
    
    private void initComponents() {
        gameField = new GameFieldPanel();
        dialogBoxPanel = new DialogBoxPanel();
        XpHudPanel = new XpHudPanel();
        
	        add(XpHudPanel, BorderLayout.NORTH);
	        add(gameField, BorderLayout.CENTER);
	        add(dialogBoxPanel, BorderLayout.SOUTH);
	        
        setVisible(true);
    };
   
    //XP Leiste mit Level Anzeige als Label
    public class XpHudPanel extends JPanel {
        public XpHudPanel() {
        	super(new BorderLayout(15, 0));            
            setOpaque(false);            

	            xperiencePoints = new JProgressBar(0, 100);
	            xperiencePoints.setValue(25); 
	            xperiencePoints.setStringPainted(true);           
	            xperiencePoints.setOpaque(false);
	            xperiencePoints.setBackground(new Color(0, 0, 0, 0));

            JLabel xpLabel = new JLabel("XP:"); 
            xpLabel.setForeground(Color.BLACK);
            
            JLabel lvlLabel = new JLabel("LVL: 100"); 
            lvlLabel.setForeground(Color.BLACK);
            
            add(xperiencePoints, BorderLayout.CENTER);
            add(xpLabel, BorderLayout.WEST);
            add(lvlLabel, BorderLayout.EAST);
        }
    }
    
	    //Erstellung des Spielfelds
	    public class GameFieldPanel extends JPanel{
			public GameFieldPanel() {
				super();
				setBackground(Color.BLACK);
				
			}  		    	
	    };
	        
	    public class DialogBoxPanel extends JPanel{
			public DialogBoxPanel() {
				super(new FlowLayout(FlowLayout.LEFT, 15, 10));         	           
	            setPreferredSize(new Dimension(0, 150));
	            setBackground(Color.LIGHT_GRAY);
	            
	            JLabel dialogBoxLabel = new JLabel("Dialog:");
	            dialogBoxLabel.setForeground(Color.BLACK);             
	            add(dialogBoxLabel);
			}	    	
	    };
	    
	    public class dialogBoxFields extends JTextField{
			public dialogBoxFields() {
				super();
				
			}
	    	
	    };       


    // Main ist nur zum testen da
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GameView());        

    }
}