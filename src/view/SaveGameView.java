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

public class SaveGameView extends JFrame {
	
	private JLabel titleLabel; // Label für das Fenster
    private JTable savegameTable; // Tabelle für die Spielstände
    private JButton saveGameBtn, loadGameBtn, cancelBtn; // Button für die 3 Actionen
    private DefaultTableModel tableModel; //
    
    public SaveGameView() { // 
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
    }
    
    private void initComponents() {
    	Font gameFont = AnimationController.loadDungeonFont(32f); // Font in groß
    	Font smallFont = AnimationController.loadDungeonFont(24f); // Font in klein
    	
    	// Oberer Teil
    	JPanel mainPanel = new JPanel (new BorderLayout(10, 10));
    	mainPanel.setBackground(Color.BLACK);
    	mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 3));
    	
    	// Label mit Fenstername
    	titleLabel = new JLabel ("Spielstände verwalten");
    	titleLabel.setForeground(Color.RED);
    	titleLabel.setFont(gameFont);
    	titleLabel.setHorizontalAlignment(JLabel.CENTER);
    	titleLabel.setPreferredSize(new Dimension(0, 60));
    	// Tabelle mit den Speicherständen
    	
    	tableModel = new DefaultTableModel(new Object[] {"Name", "Level", "XP", "Position"},0); // Werte die für den Spielstand angegeben werden
    	savegameTable = new JTable(tableModel);
    	savegameTable.setBackground(Color.DARK_GRAY);
    	savegameTable.setForeground(Color.WHITE);
    	savegameTable.setFont(smallFont);
    	savegameTable.getTableHeader().setBackground(Color.gray);
    	savegameTable.getTableHeader().setForeground(Color.WHITE);
    	savegameTable.getTableHeader().setFont(smallFont);
    	savegameTable.setRowHeight(40);
    	savegameTable.setSelectionBackground(Color.RED);
    	savegameTable.setSelectionForeground(Color.WHITE);
    	
    	JScrollPane scrollPane = new JScrollPane(savegameTable);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
    	
    	// Knöpfe unten
    	JPanel buttonPanel = new JPanel (new GridLayout(1, 3, 15, 0));
    	buttonPanel.setBackground(Color.BLACK);
    	buttonPanel.setPreferredSize(new Dimension(0, 80));
    	
    	saveGameBtn = createButton("Spiel speichern", smallFont);
    	AnimationController.beautifyButton(saveGameBtn);
    	loadGameBtn = createButton("Spiel laden", smallFont);
    	AnimationController.beautifyButton(loadGameBtn);
    	cancelBtn = createButton("Abbrechen", smallFont);
    	AnimationController.beautifyButton(cancelBtn);
    	
    	buttonPanel.add(saveGameBtn);
        buttonPanel.add(loadGameBtn);
        buttonPanel.add(cancelBtn);
    	
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        
    }
        
        private JButton createButton(String string, Font smallFont) {
		// TODO Auto-generated method stub
		return null;
	}
		public void addSavegameRow(String name, int level, int xp, String position) {
            tableModel.addRow(new Object[]{name, level, xp, position});

    }
        public int getSelectedSavegameIndex() {
            return savegameTable.getSelectedRow();
            
    }
        
        // Action Listener für Buttons
        public void addSaveGameListener(ActionListener listener) {
        	saveGameBtn.addActionListener(listener);
        }

        public void addLoadGameListener(ActionListener listener) {
        	loadGameBtn.addActionListener(listener);
        }

        public void addCancelListener(ActionListener listener) {
        	cancelBtn.addActionListener(listener);
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
