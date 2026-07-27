package view;
//(Jens)

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.List;
import model.ClassModel;
import model.ClassRepository;

public class CharacterCreationView extends JFrame {

	public CharacterCreationView() {
		setTitle("Charakter Erstellung"); // Titel
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Vollbild
		setLocationRelativeTo(null); // zentriert das Fenster relativ zum Hauptfenster
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Damit das Fenster auch wieder zugeht

		JLabel nameLabel = new JLabel("Charakter Name:"); // Neues Feld Mit Charakter Name
		JTextField nameField = new JTextField(20); // Namensfeld
		add (nameLabel);
		add (nameField);
		List <ClassModel> classList = ClassRepository.getClasslist(); // Holt sich die unsere Klassenliste
		JComboBox <ClassModel> classComboBox =
				new JComboBox<>(classList.toArray(new ClassModel[0])); // Legt ein Array aus der Liste an und erstellt hoffentlich eine Auswahlbox
		add (classComboBox);
    
    ClassModel selectedClass = (ClassModel) classComboBox.getSelectedItem();    
    setVisible(true);
	}
}