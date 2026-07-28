// Daniel

package view;

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class
public class InventoryView extends JFrame {

	private static final int ROWS = 2;
	private static final int COLS = 5;
	private JButton[][] slots = new JButton[ROWS][COLS];

	public InventoryView() {

		setTitle("Inventar");
		setSize(600, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		inventoryPanel.setLayout(new GridLayout(ROWS, COLS, 10, 10));

		createSlots(inventoryPanel);

		add(inventoryPanel);

		setVisible(true);
	}

	private void createSlots(JPanel panel) {

		// Test-Items
		String[] items = {"Faust", "Axt", "Schwert", "", "", "Trank", "", "", "", ""};

		int index = 0;

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {

				JButton slot = new JButton(items[index]);
				index++;

				slot.setFocusPainted(false);
				slot.setBackground(new Color(0, 0, 0));
				slot.setForeground(new Color(235, 100, 5));
				slot.setFont(new Font("Calibri", Font.BOLD, 16));

				Color normal = slot.getBackground();
				Color hover = new Color(237, 2, 0);

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

				slot.addActionListener(e -> {
					String item = slot.getText();

					if (!item.isBlank()) {
						System.out.println("Gewähltes Item: " + item);
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

	// TEST
	public static void main(String[] args) {

		SwingUtilities.invokeLater(InventoryView::new);

	}
}
