package model;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class PlayerModel implements Serializable {
	
	// Dieser Stempel sorgt für Sicherheit bei Programm-Updates
    private static final long serialVersionUID = 1L;
	
	//Postition des Spielers
	private int playerPosX;
	private int playerPosY;
	
	//Leben und Level
	private int maxHealth;
	private int currentHealth;
	private int currentLvl;
    private int currentXp;
    
    //Statuswerte
    private int strength;
    private int endurance;
    private int baseDmg;
    private int baseSpeed;
    private int baseArmor;
    
    //Die Referenz auf das ClassModel von Albert
    private ClassModel playerClass; 
    
    //Die Liste für das Inventar (Items von Baris)
    private List<ItemModel> inventoryList;
    
    
    // Konstruktor
    public PlayerModel() {
        this.playerPosX = 0;
        this.playerPosY = 0;
        
        this.maxHealth = 100;
        this.currentHealth = 100; 
        this.currentLvl = 1;      
        this.currentXp = 0;      
        
        this.strength = 10;
        this.endurance = 10;
        this.baseDmg = 5;
        this.baseSpeed = 1;
        this.baseArmor = 0;
        
        // Wir initialisieren eine leere Liste (einen leeren Rucksack)
        this.inventoryList = new ArrayList<>();
    }
    
 // Getter und Setter

    // Position X
    public int getPlayerPosX() {
        return this.playerPosX;
    }
    public void setPlayerPosX(int playerPosX) {
        this.playerPosX = playerPosX;
    }

    // Position Y
    public int getPlayerPosY() {
        return this.playerPosY;
    }
    public void setPlayerPosY(int playerPosY) {
        this.playerPosY = playerPosY;
    }

    // Max Health
    public int getMaxHealth() {
        return this.maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    // Current Health
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    // Current Level
    public int getCurrentLvl() {
        return this.currentLvl;
    }
    public void setCurrentLvl(int currentLvl) {
        this.currentLvl = currentLvl;
    }

    // Current XP
    public int getCurrentXp() {
        return this.currentXp;
    }
    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    // Strength
    public int getStrength() {
        return this.strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    // Endurance
    public int getEndurance() {
        return this.endurance;
    }
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    // Base Damage
    public int getBaseDmg() {
        return this.baseDmg;
    }
    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    // Base Speed
    public int getBaseSpeed() {
        return this.baseSpeed;
    }
    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    // Base Armor
    public int getBaseArmor() {
        return this.baseArmor;
    }
    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }
    
    
 // Getter und Setter für die Klasse
    public ClassModel getPlayerClass() {
        return this.playerClass;
    }
    public void setPlayerClass(ClassModel playerClass) {
        this.playerClass = playerClass;
    }

    // Getter für das komplette Inventar
    public List<ItemModel> getInventoryList() {
        return this.inventoryList;
    }

    // Praktische Helfer-Methoden für das Inventar
    // Fügt ein Item zur Liste hinzu
    public void addItemToInventory(ItemModel item) {
        this.inventoryList.add(item);
    }

    // Entfernt ein Item aus der Liste
    public void removeItemFromInventory(ItemModel item) {
        this.inventoryList.remove(item);
    }
}
