// Daniel

package view;

// Imports
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

import model.ItemModel;
import model.ItemRepository;

import controller.AnimationController;

// Class
public class InventoryView extends JFrame {

	private static final int ROWS = 2;
	private static final int COLS = 5;
	private static final int ICON_SIZE = 64;
	private JButton[][] slots = new JButton[ROWS][COLS];
	private ItemRepository itemRepository = new ItemRepository();

	public InventoryView() {
		this(null);
	}

	public InventoryView(JFrame parent) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 400);
		setTitle("Inventar");
		setUndecorated(true);

		if (parent != null) {
			Rectangle parentBounds = parent.getBounds();
			int x = parentBounds.x + (parentBounds.width - getWidth()) / 2;
			int y = parentBounds.y + (parentBounds.height - getHeight()) / 2;
			setLocation(x, y);
		} else {
			setLocationRelativeTo(null);
		}

		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setLayout(new GridLayout(ROWS, COLS, 10, 10));

		// Überschrift "Inventar"
		Font titleFont = AnimationController.loadDungeonFont(32f);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(new LineBorder(Color.RED, 3), "INVENTAR",
				TitledBorder.CENTER, TitledBorder.TOP, titleFont, new Color(189, 2, 0));

		inventoryPanel.setBorder(
				BorderFactory.createCompoundBorder(titledBorder, BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		createSlots(inventoryPanel);

		add(inventoryPanel);

		setVisible(true);
	}

	private void createSlots(JPanel panel) {

		// Items from Repository
		List<ItemModel> items = itemRepository.getItems();

		int index = 0;

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {

				ItemModel currentItem = (index < items.size()) ? items.get(index) : null;

				JButton slot = new JButton();
				index++;

				slot.setFocusPainted(false);
				slot.setBackground(new Color(0, 0, 0));
				slot.setForeground(new Color(189, 2, 0));
				slot.setFont(AnimationController.loadDungeonFont(24f));

				if (currentItem != null) {
					ImageIcon icon = loadItemIcon(currentItem.getTexturePath());
					if (icon != null) {
						slot.setIcon(icon);
						slot.setToolTipText(currentItem.getName());
					} else {
						// Fallback, falls PNG nicht gefunden wird
						slot.setText(currentItem.getName());
					}
				}

				Color hover = new Color(237, 158, 12);
				Color normal = slot.getBackground();

				// Hover-Effect
				slot.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						slot.setBackground(hover);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						slot.setBackground(normal);
					}
				});

				final ItemModel item = currentItem;

				slot.addActionListener(e -> {
					if (item != null) {
						System.out.println("Gewähltes Item: " + item.getName());
						System.out.println("Beschreibung: " + item.getDescription());
						System.out.println("Wert: " + item.getValue());
					} else {
						System.out.println("Leerer Slot");
					}
					dispose();
				});

				slots[row][col] = slot;
				panel.add(slot);
			}
		}
	}

	// Items als PNG
	private ImageIcon loadItemIcon(String texturePath) {

		if (texturePath == null || texturePath.isEmpty()) {
			return null;
		}

		URL url = getClass().getResource("/" + texturePath);

		if (url == null) {
			System.err.println("Textur nicht gefunden: " + texturePath);
			return null;
		}

		ImageIcon rawIcon = new ImageIcon(url);
		Image scaledImage = rawIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
}
