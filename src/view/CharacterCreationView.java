package view;

//(Jens)

import javax.swing.JDialog;

public class CharacterCreationView extends JDialog {
	
	public CharacterCreationView() {
		setTitle("Charakter Erstellung"); // Titel
		setSize(600, 500); //Menügröße
		setModal(true); // Verhindert das ein anderes Fenster geöffnet wird
		setLocationRelativeTo(null);
		setVisible(true);
	}

}