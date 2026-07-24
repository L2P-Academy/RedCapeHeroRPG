// Christoph (Dozent)
package view;

import java.awt.BorderLayout;

import javax.swing.*;

public class MenuView extends JFrame {
	// UI attributes
	private JButton newGameBtn, loadGameBtn, settingsBtn, highScoreBtn, exitBtn;
	private JLabel titleLbl;
	private BackGroundPanel backGroundPnl;

	// paths
	private String backGroundPath = "/backgrounds/Red_Cape_Hero_Menu_BG.png";

	public MenuView() {
		// setUndecorated(true); // randlos
		setTitle("Red Cape Hero - Hauptmenü");
		setAlwaysOnTop(true); // Fokus setzen
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Vollbild
		
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(backGroundPath));
		backGroundPnl = new BackGroundPanel(bgIcon.getImage());
		backGroundPnl.setLayout(new BorderLayout());

		// TODO: fill window with UI elements! (for Monday, 27.07.26)
		getContentPane().add(backGroundPnl);
		setLocationRelativeTo(null); // zentriert
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X-funktioniert
		setVisible(true); // sichtbar machen
	}
}
