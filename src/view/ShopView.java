// Daniel

package view;

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import model.ItemModel;
import model.ItemRepository;
import model.WeaponModel;
import model.PotionModel;
import controller.AnimationController;

// Class
public class ShopView extends JFrame {

	private static final int SHOP_WIDTH = 600;

	private ItemRepository itemRepository = new ItemRepository();

	private static final Color BG_COLOR = new Color(0, 0, 0);
	private static final Color TEXT_COLOR = new Color(189, 2, 0);
	private static final Color HOVER_COLOR = new Color(237, 158, 12);

	public ShopView() {
		this(null);
	}

	public ShopView(JFrame parent) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Shop");
		setUndecorated(true);

		getContentPane().setBackground(BG_COLOR);
		setLayout(new BorderLayout());

		// Überschrift
		JLabel title = new JLabel("SHOP", SwingConstants.CENTER);
		title.setFont(AnimationController.loadDungeonFont(48f));
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
			categoryLabel.setFont(AnimationController.loadDungeonFont(32f));
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

		Font textFont = AnimationController.loadDungeonFont(32f);

		JLabel nameLabel = new JLabel("Item: " + item.getName());
		nameLabel.setFont(textFont);
		nameLabel.setForeground(TEXT_COLOR);

		JLabel descriptionLabel = new JLabel("Beschreibung: " + item.getDescription());
		descriptionLabel.setFont(textFont);
		descriptionLabel.setForeground(TEXT_COLOR);

		JLabel valueLabel = new JLabel("Wert: " + item.getValue());
		valueLabel.setFont(textFont);
		valueLabel.setForeground(TEXT_COLOR);

		row.add(nameLabel);
		row.add(descriptionLabel);
		row.add(valueLabel);

		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));

		Color normal = row.getBackground();

		// Hover-Effect
		row.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				row.setBackground(HOVER_COLOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				row.setBackground(normal);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Gewähltes Item: " + item.getName());
				System.out.println("Beschreibung: " + item.getDescription());
				System.out.println("Wert: " + item.getValue());
				dispose();
			}
		});

		return row;
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(ShopView::new);
//	}
}