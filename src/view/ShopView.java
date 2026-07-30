// Daniel

package view;

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.ItemModel;
import model.ItemRepository;
import model.PotionModel;
import model.WeaponModel;

import controller.AnimationController;

// Class
public class ShopView extends JFrame {

	private static final int SHOP_WIDTH = 600;

	private ItemRepository itemRepository = new ItemRepository();

	private static final Color BG_COLOR = new Color(0, 0, 0);
	private static final Color TEXT_COLOR = new Color(189, 2, 0);
	private static final Color HOVER_COLOR = new Color(237, 158, 12);
	private static final Color VALUE_COLOR = new Color(255, 215, 0);

	public ShopView() {
		this(null);
	}

	public ShopView(JFrame parent) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Shop");
		setUndecorated(true);
		setFocusableWindowState(false);
		getContentPane().setBackground(BG_COLOR);
		setLayout(new BorderLayout());

		// Überschrift
		JLabel title = new JLabel("SHOP", SwingConstants.CENTER);
		title.setFont(AnimationController.loadDungeonFont(52f));
		title.setForeground(TEXT_COLOR);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
		add(title, BorderLayout.NORTH);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainPanel.setBackground(BG_COLOR);

		buildShopContent(mainPanel);

		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getViewport().setBackground(BG_COLOR);

		add(scrollPane, BorderLayout.CENTER);

		JButton closeButton = new JButton("Schließen");
		AnimationController.beautifyButton(closeButton);
		closeButton.setFont(AnimationController.loadDungeonFont(36f));
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(e -> dispose());

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(BG_COLOR);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		bottomPanel.add(closeButton);

		add(bottomPanel, BorderLayout.SOUTH);

		positionNextToParent(parent);

		setVisible(true);
	}

	private void positionNextToParent(JFrame parent) {

		if (parent != null) {

			Rectangle parentBounds = parent.getBounds();

			int x = parentBounds.x - SHOP_WIDTH;
			int y = parentBounds.y;
			int height = parentBounds.height;

			setBounds(x, y, SHOP_WIDTH, height);

		} else {

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle screenBounds = ge.getMaximumWindowBounds();

			setBounds(screenBounds.x, screenBounds.y, SHOP_WIDTH, screenBounds.height);
		}
	}

	private void buildShopContent(JPanel panel) {

		List<ItemModel> items = itemRepository.getItems();

		// Items nach Kategorie gruppieren
		Map<String, List<ItemModel>> categories = new LinkedHashMap<>();

		for (ItemModel item : items) {

			String category;

			if (item instanceof WeaponModel) {
				category = "WAFFEN:";
			} else if (item instanceof PotionModel) {
				category = "TRÄNKE:";
			} else {
				category = "SONSTIGES:";
			}

			categories.computeIfAbsent(category, k -> new java.util.ArrayList<>()).add(item);
		}

		for (Map.Entry<String, List<ItemModel>> entry : categories.entrySet()) {

			JLabel categoryLabel = new JLabel(entry.getKey());
			Font categoryFont = AnimationController.loadDungeonFont(38f);
			categoryLabel.setFont(categoryFont.deriveFont(Font.BOLD));
			categoryLabel.setForeground(TEXT_COLOR);
			categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(categoryLabel);
			panel.add(Box.createRigidArea(new Dimension(0, 8)));

			for (ItemModel item : entry.getValue()) {
				panel.add(createItemRow(item));
				panel.add(Box.createRigidArea(new Dimension(0, 8)));
			}

			panel.add(Box.createRigidArea(new Dimension(0, 15)));
		}
	}

	private JPanel createItemRow(ItemModel item) {

		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.setBackground(BG_COLOR);
		row.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));

		JLabel iconLabel = new JLabel();
		iconLabel.setPreferredSize(new Dimension(64, 64));
		java.net.URL iconUrl = getClass().getResource("/" + item.getTexturePath());
		if (iconUrl != null) {
			ImageIcon icon = new ImageIcon(iconUrl);
			Image scaled = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			iconLabel.setIcon(new ImageIcon(scaled));
		}
		row.add(iconLabel, BorderLayout.WEST);

		Font textFont = AnimationController.loadDungeonFont(32f);

		String valueColorHex = String.format("#%02x%02x%02x", VALUE_COLOR.getRed(), VALUE_COLOR.getGreen(),
				VALUE_COLOR.getBlue());

		JLabel nameLabel = new JLabel("<html><u><span style='font-size:36pt;color:" + valueColorHex
				+ ";'>Item:</span></u> " + item.getName() + "</html>");
		nameLabel.setFont(textFont);
		nameLabel.setForeground(TEXT_COLOR);

		JLabel descriptionLabel = new JLabel("<html><u><span style='font-size:36pt;color:" + valueColorHex
				+ ";'>Beschreibung:</span></u> " + item.getDescription() + "</html>");
		descriptionLabel.setFont(textFont);
		descriptionLabel.setForeground(TEXT_COLOR);

		JLabel valueLabel = new JLabel("<html><u><span style='font-size:36pt;color:" + valueColorHex
				+ ";'>Wert:</span></u> " + item.getValue() + "</html>");
		valueLabel.setFont(textFont);
		valueLabel.setForeground(TEXT_COLOR);

		JLabel rarityLabel = new JLabel("<html><u><span style='font-size:36pt;color:" + valueColorHex
				+ ";'>Benötigtes Level:</span></u> " + item.getRarityLvl() + "</html>");
		rarityLabel.setFont(textFont);
		rarityLabel.setForeground(TEXT_COLOR);

		row.add(nameLabel);
		row.add(descriptionLabel);
		row.add(valueLabel);
		row.add(rarityLabel);

		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));

		Color normal = row.getBackground();

		// Hover-Effect
		row.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				row.setBackground(HOVER_COLOR);
				row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				row.setBackground(normal);
				row.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Gewähltes Item: " + item.getName());
				System.out.println("Beschreibung: " + item.getDescription());
				System.out.println("Wert: " + item.getValue());
				System.out.println("Benötigtes Level: " + item.getRarityLvl());
				dispose();
			}
		});

		return row;
	}
}