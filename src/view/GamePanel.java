package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	// map & tile size
	private static final int TILE_SIZE = 64;
	private static final int MAP_ROWS = 33;
	private static final int MAP_COLS = 43;
	
	// all existing tiles & textures
	private TileType[][] tiles;
	private JLabel[][] worldLabels = new JLabel[MAP_ROWS][MAP_COLS];
	
	private int playerRow, playerCol;
	
	private final Map<TileType, BufferedImage> textures =
			new EnumMap<>(TileType.class);
	
	// paths for all tiles
	public enum TileType {
		GRASS("/world/grass.png"), 
		BIG_GRASS("/world/big_grass.png"), 
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
//		worldLabels[playerRow][playerCol].add(playerLabel, BorderLayout.CENTER);
		setBackground(Color.BLACK);
		setOpaque(true);
		
		loadTextures();
		createTestMap();
		
		setPreferredSize(new Dimension(MAP_COLS * TILE_SIZE, MAP_ROWS * TILE_SIZE));
	}
	
	private void loadTextures() {
		for (TileType tileType : TileType.values()) {
			
			String path = tileType.getTexturePath();
			
			try (InputStream input = getClass().getResourceAsStream(path)) {
				if (input == null) {
					System.err.println("Textur nicht gefunden bei: " + path
					);
					continue;
				}
				BufferedImage image = ImageIO.read(input);
				
				if (image == null) {
					System.err.println("Kein Textur für Bild gefunden!");
					continue;
				}
				
				textures.put(tileType, image);
			
			} catch (IOException e) {
				System.err.println("IOException beim Laden der Textur, Pfad: +" + path + "\nError-Log:\n");
				e.printStackTrace();
			}
		}
	}

	private void createTestMap() {
		tiles = new TileType[MAP_ROWS][MAP_COLS];

		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				tiles[row][col] = TileType.GRASS;
			}
		}

	}

	// rendering
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				drawTile(g, tiles[row][col], col, row);
			}
		}
	}

	private void drawTile(
	        Graphics g,
	        TileType tileType,
	        int col,
	        int row) {

	    BufferedImage texture = textures.get(tileType);

	    int x = col * TILE_SIZE;
	    int y = row * TILE_SIZE;

	    if (texture != null) {
	        g.drawImage(
	                texture,
	                x,
	                y,
	                TILE_SIZE,
	                TILE_SIZE,
	                null
	        );
	    } else {
	        // Ersatzfarbe, falls die Textur nicht geladen wurde
	        g.setColor(Color.GREEN);
	        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
	    }
	}
	
	// TODO:
//	private void fillTilePanelRandomly() {
//		for (int row = 0; row < ROWS; row++) {
//			for (int col = 0; col < COLS; col++) {
//				TileModel tile = getRandomTile(col, row);
//
//				world[row][col] = tile;
//				tile.setPosX(col);
//				tile.setPosY(row);
//
//				JLabel singleTileLbl = new JLabel();
//				singleTileLbl.setPreferredSize(new Dimension(tileSize, tileSize));
//				singleTileLbl.setLayout(new BorderLayout());
//
//				ImageIcon icon = getScaledIcon(tile.getTexturePath(), tileSize, false);
//				singleTileLbl.setIcon(icon);
//				singleTileLbl.setHorizontalAlignment(SwingConstants.CENTER);
//				singleTileLbl.setVerticalAlignment(SwingConstants.CENTER);
//
//				worldLabels[row][col] = singleTileLbl;
//				tilePnl.add(singleTileLbl);
//			}
//		}
}
