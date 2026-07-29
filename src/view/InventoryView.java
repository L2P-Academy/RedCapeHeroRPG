// Daniel

package view;

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import model.ItemModel;
import model.ItemRepository;
import controller.AnimationController;

// Class
public class InventoryView extends JFrame {

	private static final int ROWS = 2;
	private static final int COLS = 5;
	private JButton[][] slots = new JButton[ROWS][COLS];
	private ItemRepository itemRepository = new ItemRepository();

	public InventoryView() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 400);
		setTitle("Inventar");
		setUndecorated(true);
		setVisible(true);
	
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		inventoryPanel.setLayout(new GridLayout(ROWS, COLS, 10, 10));

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
				String label = (currentItem != null) ? currentItem.getName() : "";

				JButton slot = new JButton(label);
				index++;

				slot.setFocusPainted(false);
				slot.setBackground(new Color(189, 2, 0));
				slot.setForeground(new Color(0, 0, 0));
				slot.setFont(AnimationController.loadDungeonFont(24f));

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

	/*
	 * TEST public static void main(String[] args) {
	 * 
	 * SwingUtilities.invokeLater(InventoryView::new); }
	 */
}
