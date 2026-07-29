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
		setBackground(new Color(0, 0, 0, 0));
		getContentPane().setBackground(new Color(0, 0, 0, 0));

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
		inventoryPanel.setOpaque(false);

		// Überschrift "Inventar"
		Font titleFont = AnimationController.loadDungeonFont(32f);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(new LineBorder(Color.RED, 3), "INVENTAR",
				TitledBorder.CENTER, TitledBorder.TOP, titleFont, new Color(189, 2, 0));

		inventoryPanel.setBorder(
				BorderFactory.createCompoundBorder(titledBorder, BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		createSlots(inventoryPanel);

		add(inventoryPanel);
		add(createCloseButtonPanel(), BorderLayout.SOUTH);

		setVisible(true);
	}

	private JPanel createCloseButtonPanel() {

		JPanel closePanel = new JPanel();
		closePanel.setOpaque(false);
		closePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

		JButton closeButton = new JButton("Schließen");
		AnimationController.beautifyButton(closeButton);
		closeButton.setFont(AnimationController.loadDungeonFont(28f));
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.addActionListener(e -> dispose());

		closePanel.add(closeButton);

		return closePanel;
	}

	private void createSlots(JPanel panel) {

		// Items from Repository
		List<ItemModel> items = itemRepository.getItems();

		int index = 0;

		Color normalBorderColor = new Color(189, 2, 0);
		Color hoverBorderColor = new Color(237, 158, 12);

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {

				ItemModel currentItem = (index < items.size()) ? items.get(index) : null;

				JButton slot = new JButton();
				index++;

				slot.setFocusPainted(false);
				slot.setBackground(new Color(0, 0, 0));
				slot.setForeground(new Color(189, 2, 0));
				slot.setFont(AnimationController.loadDungeonFont(24f));

				Dimension slotSize = new Dimension(ICON_SIZE, ICON_SIZE);
				slot.setPreferredSize(slotSize);
				slot.setMinimumSize(slotSize);
				slot.setMaximumSize(slotSize);

				LineBorder normalBorder = new LineBorder(normalBorderColor, 2);
				LineBorder hoverBorder = new LineBorder(hoverBorderColor, 2);
				slot.setBorder(normalBorder);

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

				// Hover-Effect
				slot.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						slot.setBorder(hoverBorder);
						slot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						slot.setBorder(normalBorder);
						slot.setCursor(Cursor.getDefaultCursor());
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(InventoryView::new);
	}
}
