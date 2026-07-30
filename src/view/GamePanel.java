package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.NPCModel;
import model.NPCRepository;

public class GamePanel extends JPanel {
	// map & tile size
	private static final int TILE_SIZE = 64;
	private static final int MAP_ROWS = 1000;
	private static final int MAP_COLS = 1500;
	
	// tile probability for procedural generation
	private static final double DIRT_PROB = 0.01;
	private static final double TREE_PROB = 0.1;
	private static final double FLOWERS_PROB = 0.05;
	private static final double STONE_PROB = 0.05;
	private static final double CORRUPTED_GRASS_PROB = 0.02;
	
	private static final long WORLD_SEED = 42L;
	
	// player movement/sprites
    private ImageIcon iconUp, iconDown, iconLeft, iconRight;
    private final Map<String, ImageIcon> iconCache = new HashMap<>();
    private Map<NPCModel, JLabel> npcLabels = new HashMap<>();
	
	// all existing tiles & textures
	private TileType[][] tiles;
	
	private CameraView camera;
	private Timer cameraTimer;
	private JLabel playerSpriteLabel;
	private JLabel blackSmithSpriteLabel;
	
	private int playerRow, playerCol;
	
	private final Map<TileType, BufferedImage> textures =
			new EnumMap<>(TileType.class);
	
	// paths for all tiles
	public enum TileType {
		GRASS("/world/grass.png"), 
		CORRUPTED_GRASS("/world/corrupted_grass.png"), 
		MOWED_GRASS("/world/mowed_grass.png"), 
		DIRT("/world/dirt.png"), 
		FLOWERS("/world/flowers.png"), 
		STONE("/world/stone.png"), 
		TREE("/world/tree.png");
		
		private final String texturePath;

		TileType(String texturePath) {
			this.texturePath = texturePath;
		}
		
		public String getTexturePath() {
			return texturePath;
		}
	}
	
	// constructor
	public GamePanel() {
		setLayout(null);
		setBackground(Color.BLACK);
		setOpaque(true);
		
	    this.camera = new CameraView(
	            MAP_COLS * TILE_SIZE,
	            MAP_ROWS * TILE_SIZE
	    );
	    
		loadPlayerSprites();		
		loadTextures();
		createProceduralMap();
		loadNpcSprites();
		
	    startCameraTimer();

	    setPreferredSize(new Dimension(
	            MAP_COLS * TILE_SIZE,
	            MAP_ROWS * TILE_SIZE
	    ));
	}
	
	private void startCameraTimer() {
		cameraTimer = new Timer(16, event -> {
			camera.update();
			updateScreenPositions(); // Aktualisiert Spieler UND NPCs passend zur Kamera
			repaint();
		});		
		cameraTimer.start();
	}
		
	private void loadNpcSprites() {
		if (!NPCRepository.getNpcList().isEmpty()) {
			NPCModel blackSmithNpc = NPCRepository.getNpcList().get(0);
			
			String path = "/sprites_gifs/npc/Blacksmith/Down/idle.png";
			URL urlDownIdle = getClass().getResource(path);
			
			System.out.println("Lade NPC: " + blackSmithNpc.getName() + " | Pfad: " + path + " | URL gefunden: " + (urlDownIdle != null));
			
			if (urlDownIdle != null) {
				// Hier nutzen wir jetzt direkt den Skalierungsfaktor (gleiche Größe wie der Spieler: TILE_SIZE * 0.7)
				ImageIcon iconDownIdle = getScaledIcon(urlDownIdle, (int) (TILE_SIZE * 0.7), false);
				
				blackSmithSpriteLabel = new JLabel(iconDownIdle);
				blackSmithSpriteLabel.setSize(TILE_SIZE, TILE_SIZE);
				blackSmithSpriteLabel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
				blackSmithSpriteLabel.setOpaque(false);
				blackSmithSpriteLabel.setHorizontalAlignment(JLabel.CENTER);
				blackSmithSpriteLabel.setVerticalAlignment(JLabel.CENTER);
				
				int screenX = (int) Math.round((blackSmithNpc.getPositionX() * TILE_SIZE) - camera.getX());
				int screenY = (int) Math.round((blackSmithNpc.getPositionY() * TILE_SIZE) - camera.getY());
				blackSmithSpriteLabel.setLocation(screenX, screenY);
				
				add(blackSmithSpriteLabel);
				npcLabels.put(blackSmithNpc, blackSmithSpriteLabel);
			} else {
				System.err.println("ACHTUNG: Schmied-Sprite-Pfad ungültig: " + path);
			}
		}
	}

	public void turnNpc(NPCModel npc, String direction) {
        JLabel label = npcLabels.get(npc);
        if (label == null) return;

        String folderName = getNpcFolderName(npc.getName());
        String path = "/sprites_gifs/npc/" + folderName + "/" + getDirectionFolder(direction) + "/idle.png";
        
        URL url = getClass().getResource(path);
        if (url != null) {
            ImageIcon newIcon = getScaledIcon(url, (int) (TILE_SIZE * 0.7), false);
            label.setIcon(newIcon);
        } else {
            System.err.println("Animationsrichtung nicht gefunden für NPC: " + npc.getName() + " unter Pfad: " + path);
        }
    }

	private String getDirectionFolder(String direction) {
	    switch (direction.toUpperCase()) {
	        case "W":
	        case "UP": 
	            return "Up";
	        case "S":
	        case "DOWN": 
	            return "Down";
	        case "A":
	        case "LEFT": 
	            return "Left";
	        case "D":
	        case "RIGHT": 
	            return "Right";
	        default: 
	            return "Down";
	    }
	}

    private String getNpcFolderName(String npcName) {
        if (npcName.equalsIgnoreCase("Schmied") || npcName.equalsIgnoreCase("Blacksmith")) {
            return "Blacksmith";
        }
        return npcName;
    }
	
	private void loadPlayerSprites() {
		URL urlUp = getClass().getResource("/playeranimation/player_walk_up.gif");
        URL urlDown = getClass().getResource("/playeranimation/player_walk_down.gif");
        URL urlLeft = getClass().getResource("/playeranimation/player_walk_left.gif");
        URL urlRight = getClass().getResource("/playeranimation/player_walk_right.gif");
        
        if (urlUp != null && urlDown != null && urlLeft != null && urlRight != null) { 
            iconUp = getScaledIcon(urlUp, (int) (TILE_SIZE*0.7), true);            
            iconDown = getScaledIcon(urlDown, (int) (TILE_SIZE*0.7), true);            
            iconLeft = getScaledIcon(urlLeft, (int) (TILE_SIZE*0.7), true);
            iconRight = getScaledIcon(urlRight, (int) (TILE_SIZE*0.7), true);
         
            playerSpriteLabel = new JLabel(iconDown);
            playerSpriteLabel.setBounds(0, 0, TILE_SIZE, TILE_SIZE);
            playerSpriteLabel.setHorizontalAlignment(JLabel.CENTER);
            playerSpriteLabel.setVerticalAlignment(JLabel.CENTER);
            
            add(playerSpriteLabel);
        } else {
            System.err.println("Fehler: Mindestens ein Spieler-GIF wurde nicht gefunden!");
        } 
	}
	
	private ImageIcon getScaledIcon(URL path, int size, boolean isAnimatedGif) {		
		if (path == null)
			return null;
		
		String key = path.toExternalForm() + "@" + size + (isAnimatedGif ? "#gif" : "#static");
		ImageIcon cached = iconCache.get(key);
		
		if (cached != null) {
			return cached;
		}		

		if (isAnimatedGif) {
			ImageIcon originalIcon = new ImageIcon(path);
			Image scaledImage = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			iconCache.put(key, scaledIcon);			
			return scaledIcon;
		} else {
			try {
				BufferedImage src = ImageIO.read(path);
				BufferedImage scaledImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
				
				Graphics2D g = scaledImage.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
				g.drawImage(src, 0, 0, size, size, null);
				g.dispose();
				
				ImageIcon scaledIcon = new ImageIcon(scaledImage);
				iconCache.put(key, scaledIcon);
				
				return scaledIcon;				
			} catch (IOException e) {				
				System.err.println("Bild konnte nicht skaliert werden!");
				e.printStackTrace();
				return new ImageIcon(path);
			}
		}
	}
	
	public void setPlayerTilePosition(int playerCol, int playerRow) {
	    this.playerCol = playerCol;
	    this.playerRow = playerRow;

	    double playerCenterWorldX = playerCol * TILE_SIZE + TILE_SIZE / 2.0;
	    double playerCenterWorldY = playerRow * TILE_SIZE + TILE_SIZE / 2.0;

	    camera.centerOn(playerCenterWorldX, playerCenterWorldY, getWidth(), getHeight());
	    repaint();
	}
	
	private void updatePlayerScreenPosition() {
	    if (playerSpriteLabel == null || camera == null) {
	        return;
	    }

	    double playerWorldX = playerCol * TILE_SIZE;
	    double playerWorldY = playerRow * TILE_SIZE;

	    int playerScreenX = (int) Math.round(playerWorldX - camera.getX());
	    int playerScreenY = (int) Math.round(playerWorldY - camera.getY());

	    playerSpriteLabel.setLocation(playerScreenX, playerScreenY);
	}
	
	public void updatePlayerDirection(String direction) {
        if (playerSpriteLabel == null) return;
        switch (direction) {
            case "W":
                if (playerSpriteLabel.getIcon() != iconUp) playerSpriteLabel.setIcon(iconUp);
                break;
            case "S":
                if (playerSpriteLabel.getIcon() != iconDown) playerSpriteLabel.setIcon(iconDown);
                break;
            case "A":
                if (playerSpriteLabel.getIcon() != iconLeft) playerSpriteLabel.setIcon(iconLeft);
                break;
            case "D":
                if (playerSpriteLabel.getIcon() != iconRight) playerSpriteLabel.setIcon(iconRight);
                break;
        }
    }
	
	private void updateScreenPositions() {
	    if (camera == null) return;

	    // 1. Spieler positionieren
	    if (playerSpriteLabel != null) {
	        double playerWorldX = playerCol * TILE_SIZE;
	        double playerWorldY = playerRow * TILE_SIZE;

	        int playerScreenX = (int) Math.round(playerWorldX - camera.getX());
	        int playerScreenY = (int) Math.round(playerWorldY - camera.getY());

	        playerSpriteLabel.setLocation(playerScreenX, playerScreenY);
	    }

	    // 2. NPCs (wie den Schmied) in der Spielwelt anhand ihrer Koordinaten positionieren
	    for (Map.Entry<NPCModel, JLabel> entry : npcLabels.entrySet()) {
	        NPCModel npc = entry.getKey();
	        JLabel label = entry.getValue();

	        int npcScreenX = (int) Math.round((npc.getPositionX() * TILE_SIZE) - camera.getX());
	        int npcScreenY = (int) Math.round((npc.getPositionY() * TILE_SIZE) - camera.getY());

	        label.setLocation(npcScreenX, npcScreenY);
	    }
	}
	
	private void loadTextures() {
		for (TileType tileType : TileType.values()) {
			String path = tileType.getTexturePath();
			
			try (InputStream input = getClass().getResourceAsStream(path)) {
				if (input == null) {
					System.err.println("Textur nicht gefunden bei: " + path);
					continue;
				}
				BufferedImage image = ImageIO.read(input);
				
				if (image == null) {
					System.err.println("Kein Textur für Bild gefunden!");
					continue;
				}
				
				textures.put(tileType, image);
			} catch (IOException e) {
				System.err.println("IOException beim Laden der Textur, Pfad: " + path);
				e.printStackTrace();
			}
		}
	}
	
	private void createProceduralMap() {
	    tiles = new TileType[MAP_ROWS][MAP_COLS];
	    Random random = new Random(WORLD_SEED);

	    for (int row = 0; row < MAP_ROWS; row++) {
	        for (int col = 0; col < MAP_COLS; col++) {
	            if (row > 0 && col > 0 && random.nextDouble() < 0.55) {
	                if (random.nextBoolean()) {
	                    tiles[row][col] = tiles[row - 1][col];
	                } else {
	                    tiles[row][col] = tiles[row][col - 1];
	                }
	            } else {
	                tiles[row][col] = getRandomTile(random);
	            }
	        }
	    }

	    createFixedStartArea();
	}
	
	private void createFixedStartArea() {
	    int startCol = MAP_COLS / 2;
	    int startRow = MAP_ROWS / 2;

	    for (int row = startRow - 3; row <= startRow + 3; row++) {
	        for (int col = startCol - 4; col <= startCol + 4; col++) {
	            if (isInsideMap(col, row)) {
	                tiles[row][col] = TileType.MOWED_GRASS;
	            }
	        }
	    }

	    for (int col = startCol; col < MAP_COLS; col++) {
	        setTileIfInside(col, startRow, TileType.DIRT);
	        setTileIfInside(col, startRow + 1, TileType.DIRT);
	    }

	    setTileIfInside(startCol - 3, startRow - 2, TileType.FLOWERS);
	    setTileIfInside(startCol + 3, startRow - 2, TileType.FLOWERS);
	    setTileIfInside(startCol - 4, startRow, TileType.TREE);
	    setTileIfInside(startCol + 4, startRow - 2, TileType.TREE);
	    setTileIfInside(startCol - 2, startRow + 2, TileType.STONE);
	}
	
	private boolean isInsideMap(int col, int row) {
	    return col >= 0 && col < MAP_COLS && row >= 0 && row < MAP_ROWS;
	}

	private void setTileIfInside(int col, int row, TileType tileType) {
	    if (isInsideMap(col, row)) {
	        tiles[row][col] = tileType;
	    }
	}
	
	private TileType getRandomTile(Random random) {
	    double value = random.nextDouble();
	    double cumulativeChance = 0.0;

	    cumulativeChance += TREE_PROB;
	    if (value < cumulativeChance) return TileType.TREE;

	    cumulativeChance += STONE_PROB;
	    if (value < cumulativeChance) return TileType.STONE;

	    cumulativeChance += DIRT_PROB;
	    if (value < cumulativeChance) return TileType.DIRT;

	    cumulativeChance += FLOWERS_PROB;
	    if (value < cumulativeChance) return TileType.FLOWERS;

	    cumulativeChance += CORRUPTED_GRASS_PROB;
	    if (value < cumulativeChance) return TileType.CORRUPTED_GRASS;

	    return TileType.GRASS;
	}
	
	public void moveCamera(int deltaX, int deltaY) {
	    camera.move(deltaX, deltaY, getWidth(), getHeight());
	    updateScreenPositions();
	    repaint();
	}
	
	public void centerCameraOn(int worldX, int worldY) {
	    camera.centerOn(worldX, worldY, getWidth(), getHeight());
	    updateScreenPositions();
	    repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    if (tiles == null || camera == null) {
	        return;
	    }

	    int startCol = (int) (camera.getX() / TILE_SIZE);
	    int startRow = (int) (camera.getY() / TILE_SIZE);

	    int endCol = startCol + getWidth() / TILE_SIZE + 2;
	    int endRow = startRow + getHeight() / TILE_SIZE + 2;

	    startCol = Math.max(0, startCol);
	    startRow = Math.max(0, startRow);

	    endCol = Math.min(MAP_COLS, endCol);
	    endRow = Math.min(MAP_ROWS, endRow);

	    for (int row = startRow; row < endRow; row++) {
	        for (int col = startCol; col < endCol; col++) {
	            drawTile(g, tiles[row][col], col, row);
	        }
	    }
	}

	private void drawTile(Graphics g, TileType tileType, int col, int row) {
	    BufferedImage texture = textures.get(tileType);

	    double worldX = col * TILE_SIZE;
	    double worldY = row * TILE_SIZE;

	    int screenX = (int) Math.round(worldX - camera.getX());
	    int screenY = (int) Math.round(worldY - camera.getY());

	    if (texture != null) {
	        g.drawImage(texture, screenX, screenY, TILE_SIZE, TILE_SIZE, null);
	    } else {
	        g.setColor(Color.GREEN);
	        g.fillRect(screenX, screenY, TILE_SIZE, TILE_SIZE);
	    }
	}
}