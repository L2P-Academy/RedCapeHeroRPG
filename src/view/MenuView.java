// Christoph (Dozent)
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.AnimationController;
import controller.GameController;
import controller.SoundController;
import model.ClassModel;
import model.GameStateModel;
import model.PlayerModel;

public class MenuView extends JFrame {
	// UI attributes
	private JButton newGameBtn, loadGameBtn, settingsBtn, highScoreBtn, exitBtn;
	private JLabel satireLbl;
	private BackGroundPanel backGroundPnl;
	private JPanel buttonPnl;

	// paths
	private String backGroundPath = "/backgrounds/Red_Cape_Hero_Menu_BG.png";
	private String musicPath = "res/sounds/titlescreen.wav";

	public MenuView() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Vollbild
		setUndecorated(true); // fullscreen - randlos
		setTitle("Red Cape Hero - Hauptmenü");
		setAlwaysOnTop(true); // Fokus setzen
		
		// start playing music
		SoundController.playMusicLoop(musicPath);

		ImageIcon bgIcon = new ImageIcon(getClass().getResource(backGroundPath));
		backGroundPnl = new BackGroundPanel(bgIcon.getImage());
		backGroundPnl.setLayout(new BorderLayout());

		satireLbl = new JLabel("Satire!");
		Font gameFont = AnimationController.loadDungeonFont(72f);
		satireLbl.setFont(gameFont);
		satireLbl.setForeground(Color.RED);

		// buttons
		buttonPnl = new JPanel(new FlowLayout());
		buttonPnl.setOpaque(false);

		newGameBtn = new JButton("Neues Spiel");
		AnimationController.beautifyButton(newGameBtn);
		loadGameBtn = new JButton("Spiel laden");
		AnimationController.beautifyButton(loadGameBtn);
		settingsBtn = new JButton("Einstellungen");
		AnimationController.beautifyButton(settingsBtn);
		highScoreBtn = new JButton("Bestenliste");
		AnimationController.beautifyButton(highScoreBtn);
		exitBtn = new JButton("Spiel Beenden");
		AnimationController.beautifyButton(exitBtn);

		buttonPnl.add(newGameBtn);
		buttonPnl.add(loadGameBtn);
		buttonPnl.add(settingsBtn);
		buttonPnl.add(highScoreBtn);
		buttonPnl.add(exitBtn);

		backGroundPnl.add(satireLbl, BorderLayout.NORTH);
		backGroundPnl.add(buttonPnl, BorderLayout.SOUTH);

		// Actions
		newGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// for testing -> Character Creation
				SoundController.playBtnSound();
				SoundController.stopMusicLoop();
				new CharacterCreationView();
				dispose();
			}
		});

		loadGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// for testing -> GameView
				SoundController.playBtnSound();
				SoundController.stopMusicLoop();				
				
                ClassModel testClass = new ClassModel(
                        1,                            // id
                        "Test-Ritter",                // name
                        "Eine Klasse zum Testen",     // description
                        5,                            // bonusStrength
                        5,                            // bonusEndurance
                        2                             // bonusDamage
                );
                
                PlayerModel testPlayer = new PlayerModel(
                        2,          // playerPosX
                        2,          // playerPosY
                        "TestHero",   // name
                        100,          // maxHealth
                        10,           // strength
                        10,           // endurance
                        5,            // baseDmg
                        2,            // baseArmor
                        testClass     // Alberts ClassModel übergeben!
                );
                
                testPlayer.setCurrentXp(35); 
                
                long currentTime = System.currentTimeMillis();
                GameStateModel testModel = new GameStateModel(
                        "TestHero",                     
                        new java.sql.Date(currentTime), 
                        testPlayer,                     // <-- Unser PlayerModel!
                        35,                             
                        150,                            
                        250,                            
                        null                            
                );
                
                SwingUtilities.invokeLater(() -> {
                    GameView gameView = new GameView();
                    GameController controller = new GameController(gameView, testModel);
                });
				dispose();
			}
		});

		settingsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController.playBtnSound();
				new SettingsView();
			}
		});

		highScoreBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController.playBtnSound();
				new HighScoreView();
			}
		});

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SoundController.playBtnSound();
				SoundController.stopMusicLoop();
				dispose();
			}
		});

		getContentPane().add(backGroundPnl);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X-funktioniert
		setVisible(true); // sichtbar machen
	}
}
