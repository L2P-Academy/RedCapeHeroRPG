package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameOverview extends JFrame{
	private String backGroundPath = "/backgrounds/Game Over View.png";
	private BackGroundPanel backGroundPnl;
	
	public GameOverview() {
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(backGroundPath));
		backGroundPnl = new BackGroundPanel(bgIcon.getImage());
		backGroundPnl.setLayout(new BorderLayout());
		setUndecorated(true); // fullscreen - randlos
		setSize(800,600);
		setTitle("Red Cape Hero - GameOver");
		setAlwaysOnTop(true); // Fokus setzen
		add(backGroundPnl);
		setVisible(true);
	}
}