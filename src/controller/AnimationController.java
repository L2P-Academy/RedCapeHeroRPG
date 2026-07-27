package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.Border;

public class AnimationController {
	/**
	 * Sets the Background, Foreground, Font & Border of a Button. Adds a
	 * MouseListener for Coloring.
	 * 
	 * @param The target-Button for modification
	 * @author Christoph
	 */
	public static void beautifyButton(JButton button) {
		button.setFocusPainted(false);
		button.setBackground(new Color(189, 2, 0));
		button.setForeground(Color.BLACK);
		button.setFont(loadDungeonFont(64f));

		// Rounded Corners
		Border border = BorderFactory.createLineBorder(new Color(237, 158, 12), 2);
		Border roundedBorder = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 20, 10, 20));
		button.setBorder(
				BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		// color change when MouseOver is happening
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(237, 158, 12));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(189, 2, 0));
			}
		});
	}

	/**
	 * Beautifies a table with Font, size, back-/foreground
	 * 
	 * @param desired table for better UI in game-style
	 */
	public static void beautifyTable(JTable table) {
		table.setFont(new Font("Calibri", 0, 24));
		table.setRowHeight(45);
		table.getTableHeader().setFont(new Font("Calibri", 0, 18));
		table.getTableHeader().setBackground(Color.DARK_GRAY);
		table.getTableHeader().setForeground(Color.WHITE);
		table.setForeground(Color.BLACK);
	}
	
	// Fontloader for gamefont
	public static Font loadDungeonFont(float size) {
		try {
			InputStream is = AnimationController.class.getResourceAsStream("/fonts/dungeon_font.TTF");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			return font;
		} catch (Exception e) {
			e.printStackTrace();
			return new Font("Monospaced", Font.PLAIN, (int) size);
		}
	}
}
