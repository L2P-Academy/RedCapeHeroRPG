package view;
//(Jens)

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.List;
import model.ClassModel;
import repository.ClassRepository;

public class CharacterCreationView extends JDialog {
	
	public CharacterCreationView() {
		setTitle("Charakter Erstellung"); // Titel
		setSize(600, 500); //Menügröße
		setModal(true); // Verhindert das ein anderes Fenster geöffnet wird
		setLocationRelativeTo(null); //zentriert das Fenster relativ zum Hauptfenster
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Damit das Fenster auch wieder zugeht
		
		JLabel nameLabel = new JLabel ("Charakter Name:"); // Neues Feld Mit Charakter Name
		JTextField nameField = new JTextField(20); // Namensfeld
		add (nameLabel);
		add (nameField);
		List <ClassModel> classList = ClassRepository.getClasslist();
	}
}