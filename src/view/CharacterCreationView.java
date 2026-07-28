package view;
// Jens
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controller.AnimationController;
import model.ClassModel;
import model.ClassRepository;

import java.awt.Font;

public class CharacterCreationView extends JFrame {
    private JTextField nameField; // Namensfeld
    private JList<ClassModel> classList; // Klassenliste

    private JTextArea descriptionArea; // Bereich für die Beschreibung
    private JLabel strengthLabel; // Tag für Stärke
    private JLabel enduranceLabel; // Tag für Ausdauer
    private JLabel damageLabel; // Tag für Schaden

    private JButton createButton; // Knopf fürs erstellen
    private JButton cancelButton; // Knopf fürs abbrechen

	public CharacterCreationView() { // Fenster geht hier los
		setVisible(true);
		setTitle("Charaktererstellung"); // Titel des Fensters
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Fenstergröße
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Damit es auch zu geht
        setLayout(new BorderLayout(15, 15));
        Font gameFont = AnimationController.loadDungeonFont(40f);
        Color red = new Color(139, 0, 0);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Kopfzeile für die Erstellung
        JLabel nameLabel = new JLabel("Charaktername");
        nameLabel.setFont(gameFont); // Schrift für das Label
        topPanel.add(nameLabel);
        nameField = new JTextField(20); // Das Namensfeld
        nameField.setFont(gameFont); // Schrift für das Eingabefeld
        nameField.setForeground(red);
        topPanel.add(nameField);

        add(topPanel, BorderLayout.NORTH); // Panel oben
        
        // Klassenclownstuff
        classList = new JList <>( // Zieht sich unsere Classlist
        		ClassRepository.getClasslist().toArray(new ClassModel [0])); // und macht ein Array draus
        classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Auswahl mit einer einzelauswahl
        classList.setFont(gameFont); // Schrift für die Liste setzen
        classList.setForeground(red);
        JScrollPane classScroll = new JScrollPane(classList); //
        classScroll.setPreferredSize(new java.awt.Dimension(200, 200));
        add(classScroll, BorderLayout.WEST);
        
        // Charakterbeschrebung
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10)); // Grundrahmen für Beschreibungspanel
        
        descriptionArea = new JTextArea(); // Neues Textfeld
        descriptionArea.setEditable(false); // nicht editierbar
        descriptionArea.setLineWrap(true); // Zeilenumbruch zulassen
        descriptionArea.setWrapStyleWord(true); // Zeilumbruch auf Wortebene
        descriptionArea.setFont(gameFont); // Schrift für die Beschreibung setzen
        descriptionArea.setForeground(red);
        centerPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER); // Ort der Beschreibung mitte
        
        // Stats
        JPanel statsPanel = new JPanel(new GridLayout(3, 1)); // Layout für die Stats 3 Stats daher 3 

        strengthLabel = new JLabel("Strength: "); // Stärkelebel
        strengthLabel.setFont(gameFont);
        strengthLabel.setForeground(Color.RED);
        enduranceLabel = new JLabel("Endurance: "); // Ausdauerlabel
        enduranceLabel.setFont(gameFont);
        enduranceLabel.setForeground(Color.RED);
        damageLabel = new JLabel("Damage: "); // Schadenslabel
        damageLabel.setFont(gameFont);
        damageLabel.setForeground(Color.RED);
        

        statsPanel.add(strengthLabel); // Hinzufügen des Labels
        statsPanel.add(enduranceLabel); // Hinzufügen des Labels
        statsPanel.add(damageLabel); // Hinzufügen des Labels

        centerPanel.add(statsPanel, BorderLayout.SOUTH); // Panel unten

        add(centerPanel, BorderLayout.CENTER); // Center of Centerpanel
        
        // Buttons unten
        JPanel bottomPanel = new JPanel(); // Panel für die Button

        createButton = new JButton("Charakter Erstellen"); // Button fürs Erstellen
        AnimationController.beautifyButton(createButton);
        cancelButton = new JButton("Abbrechen"); // Button fürs Abbrechen
        AnimationController.beautifyButton(cancelButton);

        bottomPanel.add(createButton); // Btn erzeugen
        bottomPanel.add(cancelButton); // Btn erzeugen

        add(bottomPanel, BorderLayout.SOUTH); // Orientierung unten
        
        
        // Update bei Klassenwechsel
        
        classList.addListSelectionListener(e -> { // Passt auf das was passiert
            if (!e.getValueIsAdjusting()) { 
                ClassModel selected = classList.getSelectedValue(); // Nimmt die Classlist
                if (selected != null) {
                    descriptionArea.setText(selected.getDescription());
                    strengthLabel.setText(
                            "Strength Bonus: +" + selected.getBonusStrength());
                    enduranceLabel.setText(
                            "Endurance Bonus: +" + selected.getBonusEndurance());
                    damageLabel.setText(
                            "Damage Bonus: +" + selected.getBonusDamage());
                }
            }
        });
        
        if (!ClassRepository.getClasslist().isEmpty()) {
            classList.setSelectedIndex(0);
        }
        
        createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // Button für Erstellen damit das spiel aufgeht
				new GameView();
				dispose();
			}
		});

        // Abbrechen Knopf bricht ab
        cancelButton.addActionListener(e -> { // Knopf
            dispose(); // bricht ab
            new MenuView(); // macht Hauptmenu auf
        });
        
		}
	}