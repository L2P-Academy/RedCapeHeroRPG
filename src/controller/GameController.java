package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.GameStateModel;
import model.NPCModel;
import model.NPCRepository;
import model.PlayerModel;
import view.GamePanel;
import view.GameView;
import view.InventoryView;
import view.MenuView;
import view.SaveGameView;
import view.SettingsView;
import view.ShopView;

public class GameController {

	private GameView view;
	private GamePanel gamePanel;
	private GameStateModel modelG;
	private PlayerModel modelP;
	private InventoryView inventoryView;
	private ShopView shopView;
	private JDialog pauseDialog;

	private boolean isDialogActive = false;
	private boolean isInventoryActive = false;
	private boolean isShopActive = false;
	private boolean isPauseActive = false;

	// movement timings
	private static final int MOVEMENT_DELAY_MS = 200;
	private Timer movementTimer;

	private boolean moveUp, moveDown, moveLeft, moveRight;

	public GameController(GameView view, GameStateModel modelG) {
		this.view = view;
		this.gamePanel = view.getGameField();
		this.modelG = modelG;
		this.modelP = modelG.getPlayerModel();

		this.inventoryView = new InventoryView();
		this.inventoryView.setVisible(false);
		this.inventoryView.setOnCloseListener(() -> isInventoryActive = false);
		
		this.shopView = new ShopView();
		this.shopView.setVisible(false);
		this.shopView.setOnCloseListener(() -> isShopActive = false);

		initGame();
		initPauseMenu();
		setupControllerInput();
		initMovementTimer();
	}

	private void initMovementTimer() {
		movementTimer = new Timer(MOVEMENT_DELAY_MS, event -> updateMovement());
		movementTimer.setInitialDelay(10);
		movementTimer.start();
	}

	private void updateMovement() {
		if (isPauseActive || isInventoryActive || isShopActive || isDialogActive) {
			return;
		}
		int deltaX = 0;
		int deltaY = 0;

		if (moveUp && !moveDown) {
			deltaY = -1;
		} else if (moveDown && !moveUp) {
			deltaY = 1;
		}

		if (moveLeft && !moveRight) {
			deltaX = -1;
		} else if (moveRight && !moveLeft) {
			deltaX = 1;
		}

		if (deltaX != 0 || deltaY != 0) {
			movePlayer(deltaX, deltaY);
		}
	}

	private void initGame() {
		int playerX = modelP.getPlayerPosX();
		int playerY = modelP.getPlayerPosY();

		view.updateCoordinates(modelP.getPlayerPosX(), modelP.getPlayerPosY());
		view.updateXP(modelP.getCurrentXp());
		view.setDialogActive(isDialogActive);

		javax.swing.SwingUtilities.invokeLater(() -> {
			gamePanel.setPlayerTilePosition(playerX, playerY);
		});
	}

	private NPCModel getNearbyNPC() {
		int px = modelP.getPlayerPosX();
		int py = modelP.getPlayerPosY();

		for (NPCModel npc : NPCRepository.getNpcList()) {
			int diffX = Math.abs(px - npc.getPositionX());
			int diffY = Math.abs(py - npc.getPositionY());

			if (diffX <= 1 && diffY <= 1) {
				return npc;
			}
		}
		return null;
	}

	private void setupControllerInput() {

		view.addKeyBinding(KeyStroke.getKeyStroke("pressed W"), "moveUpPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveUp = true;
				gamePanel.updatePlayerDirection("W");
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("released W"), "moveUpReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveUp = false;
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("pressed S"), "moveDownPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown = true;
				gamePanel.updatePlayerDirection("S");
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("released S"), "moveDownReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown = false;
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("pressed A"), "moveLeftPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveLeft = true;
				gamePanel.updatePlayerDirection("A");
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("released A"), "moveLeftReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveLeft = false;
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("pressed D"), "moveRightPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveRight = true;
				gamePanel.updatePlayerDirection("D");
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("released D"), "moveRightReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveRight = false;
			}
		});

		// Taste "E" für den Dialog (Dynamisch basierend auf Nähe)
		view.addKeyBinding(KeyStroke.getKeyStroke("E"), "toggleDialog", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPauseActive || isInventoryActive || isShopActive)
					return;

				if (!isDialogActive) {
					NPCModel nearNpc = getNearbyNPC();

					if (nearNpc != null) {
						isDialogActive = true;

						// Blickrichtung bestimmen
						int px = modelP.getPlayerPosX();
						int py = modelP.getPlayerPosY();
						int nx = nearNpc.getPositionX();
						int ny = nearNpc.getPositionY();

						String turnDirection = "Down"; // Standard

						if (px < nx) {
							turnDirection = "Left";
						} else if (px > nx) {
							turnDirection = "Right";
						} else if (py < ny) {
							turnDirection = "Up";
						} else if (py > ny) {
							turnDirection = "Down";
						}

						// NPC drehen
						gamePanel.turnNpc(nearNpc, turnDirection);

						// 1. Text und NPC-Daten aus dem Repository holen
						String npcName = nearNpc.getName();
						String dialogueText = nearNpc.getDialog();

						// 2. Passenden Bildpfad für das NPC-Porträt/Sprite ermitteln (z.B. passend zum
						// Ordner)
						String folderName = (npcName.equalsIgnoreCase("Schmied")
								|| npcName.equalsIgnoreCase("Blacksmith")) ? "Blacksmith" : npcName;
						String spritePath = "/sprites_gifs/npc/" + folderName + "/" + turnDirection + "/idle.png";

						// 3. An die GameView (bzw. deine Dialogbox) übergeben
						view.showNpcDialog(npcName, dialogueText, spritePath);
						view.setDialogActive(true);
					}
				} else {
					isDialogActive = false;
					view.hideNpcDialog(); // Methode zum Schließen der Box in der View
					view.setDialogActive(false);
				}
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("I"), "toggleInventory", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isInventoryActive = !isInventoryActive;

				inventoryView.setVisible(isInventoryActive);
				if (isInventoryActive) {
					inventoryView.toFront();
				}
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("B"), "toggleShop", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isShopActive = !isShopActive;

				shopView.setVisible(isShopActive);
				if (isShopActive) {
					shopView.toFront();
				}
			}
		});

		view.addKeyBinding(KeyStroke.getKeyStroke("ESCAPE"), "togglePauseMenu", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPauseActive = !isPauseActive;

				pauseDialog.setVisible(isPauseActive);
				if (isPauseActive) {
					pauseDialog.toFront();
				}
			}
		});
	}

	private void initPauseMenu() {
		pauseDialog = new JDialog(view, "PAUSE", false);
		pauseDialog.setSize(500, 850);
		pauseDialog.setLocationRelativeTo(view);
		pauseDialog.setUndecorated(true);
		pauseDialog.setFocusableWindowState(false);

		JPanel bgPanel = new JPanel();
		bgPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
		pauseDialog.setContentPane(bgPanel);

		JPanel contentPnl = new JPanel();
		contentPnl.setOpaque(false);
		contentPnl.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		contentPnl.setLayout(new BorderLayout(0, 20));

		JPanel menuPnl = new JPanel(new GridLayout(6, 1));
		menuPnl.setOpaque(false);

		JButton resumeBtn = new JButton("Fortsetzen");
		AnimationController.beautifyButton(resumeBtn);
		resumeBtn.addActionListener(e -> {
			SoundController.playBtnSound();
			isPauseActive = false;
			pauseDialog.setVisible(false);
		});

		JButton saveBtn = new JButton("Speichern");
		AnimationController.beautifyButton(saveBtn);
		saveBtn.addActionListener(e -> {
			SoundController.playBtnSound();
			isPauseActive = false;
			pauseDialog.setVisible(false);

			javax.swing.SwingUtilities.invokeLater(() -> {
				SaveGameView saveView = new SaveGameView(modelG);
				saveView.setVisible(true);
				saveView.toFront();
				saveView.requestFocus();
			});
		});

		JButton settingsBtn = new JButton("Einstellungen");
		AnimationController.beautifyButton(settingsBtn);
		settingsBtn.addActionListener(e -> {
			SoundController.playBtnSound();
			isPauseActive = false;
			pauseDialog.setVisible(false);
			new SettingsView();
		});

		JButton returnBtn = new JButton("Hauptmenü");
		AnimationController.beautifyButton(returnBtn);
		returnBtn.addActionListener(e -> {
			SoundController.playBtnSound();
			pauseDialog.setVisible(false);
			new MenuView();
			view.dispose();
		});

		JButton exitBtn = new JButton("Spiel Beenden");
		AnimationController.beautifyButton(exitBtn);
		exitBtn.addActionListener(e -> {
			SoundController.playBtnSound();
			System.exit(0);
		});

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
	}

	private void movePlayer(int deltaX, int deltaY) {
		int newX = modelP.getPlayerPosX() + deltaX;
		int newY = modelP.getPlayerPosY() + deltaY;

		modelP.setPlayerPosX(newX);
		modelP.setPlayerPosY(newY);

		gamePanel.setPlayerTilePosition(newX, newY);
		view.updateCoordinates(newX, newY);
	}
}