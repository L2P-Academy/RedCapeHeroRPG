package view;

// Jens

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.AnimationController;
import model.GameStateModel;

public class SaveGameView extends JFrame {
	
	private JLabel titleLabel; // Label für das Fenster
    private JTable savegameTable; // Tabelle für die Spielstände
    private JButton saveGameBtn, loadGameBtn, cancelBtn; // Button für die 3 Actionen
    private DefaultTableModel tableModel; //
    private GameStateModel currentGameState; // aktueller Spielstand
    
    public SaveGameView() { // 
    	this.currentGameState = null;
    	initWindow();
        initComponents();
    }
    
    public SaveGameView(GameStateModel currentGameState) { // 
    	this.currentGameState = currentGameState;
    	initWindow();
        initComponents();
    }
    
    //Save Game Fenster
    private void initWindow() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Spielstände verwalten");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
    	Font titleFont = AnimationController.loadDungeonFont(32f);
        Font buttonFont = AnimationController.loadDungeonFont(22f);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.BLACK);

        titleLabel = new JLabel("Spielstände verwalten", JLabel.CENTER); // Label für das Fenster
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(titleFont);

        String[] columns = {"Spielername", "Letztes Speichern", "Score"};
        tableModel = new DefaultTableModel(columns, 0);
        savegameTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(savegameTable);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        saveGameBtn = new JButton("Spiel speichern");
        saveGameBtn.setFont(buttonFont);
        AnimationController.beautifyButton(saveGameBtn);

        loadGameBtn = new JButton("Spiel laden");
        loadGameBtn.setFont(buttonFont);
        AnimationController.beautifyButton(loadGameBtn);

        cancelBtn = new JButton("Abbrechen");
        cancelBtn.setFont(buttonFont);
        AnimationController.beautifyButton(cancelBtn);
        cancelBtn.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(saveGameBtn);
        buttonPanel.add(loadGameBtn);
        buttonPanel.add(cancelBtn);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }   
        //Getter und Setterkram
        
		public JLabel getTitleLabel() {
			return titleLabel;
		}
		public void setTitleLabel(JLabel titleLabel) {
			this.titleLabel = titleLabel;
		}
		public JTable getSavegameTable() {
			return savegameTable;
		}
		public void setSavegameTable(JTable savegameTable) {
			this.savegameTable = savegameTable;
		}
		public JButton getSaveGameBtn() {
			return saveGameBtn;
		}
		public void setSaveGameBtn(JButton saveGameBtn) {
			this.saveGameBtn = saveGameBtn;
		}
		public JButton getLoadGameBtn() {
			return loadGameBtn;
		}
		public void setLoadGameBtn(JButton loadGameBtn) {
			this.loadGameBtn = loadGameBtn;
		}
		public JButton getCancelBtn() {
			return cancelBtn;
		}
		public void setCancelBtn(JButton cancelBtn) {
			this.cancelBtn = cancelBtn;
		}
		public DefaultTableModel getTableModel() {
			return tableModel;
		}
		public void setTableModel(DefaultTableModel tableModel) {
			this.tableModel = tableModel;
		}
        
        
       
}
