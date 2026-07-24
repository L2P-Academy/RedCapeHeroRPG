// Christoph (Dozent)
package view;

import javax.swing.*;

public class MenuView extends JFrame {
	// UI attributes
	private JButton newGameBtn, loadGameBtn, settingsBtn, highScoreBtn, exitBtn;
	private JLabel titleLbl;
	private JPanel backGroundPnl;

	// paths
	private String backGroundPath = "/res/backgrounds/menu_bg.png";

	public MenuView() {
		// setUndecorated(true); // randlos
		setTitle("Red Cape Hero - Hauptmenü");
		setLocationRelativeTo(null); // zentriert
		setAlwaysOnTop(true); // Fokus setzen
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Vollbild
		
		// TODO: fill window with UI elements! (for Monday, 27.07.26)

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X-funktioniert
		setVisible(true); // sichtbar machen
	}
}
