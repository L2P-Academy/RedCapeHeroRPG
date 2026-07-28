package model;

/*Alle Eigenschaften der Items*/

public class ItemModel {
	
	//Attribute
	protected int id;
	protected String name;
	protected String description;
	protected int value;
	protected String texturePath;
	protected int rarityLvl;

	//Erstellt neue Items
	public ItemModel (int id, String name, String description, int value, String texturePath, int rarityLvl) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.value = value;
		this.texturePath = texturePath;
		this.rarityLvl = rarityLvl;
	}
	
	//Getter
	public int getId () {
		return id;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDescription () {
		return description;
	}
	
	public int getValue () {
		return value;
	}
	
	public String getTexturePath () {
		return texturePath;
	}
	
	public int getRarityLvl () {
		return rarityLvl;
	}
	
	//Setter
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setValue (int value) {
		this.value = value;
	}
	
	public void setTexturePath (String texturePath) {
		this.texturePath = texturePath;
	}
	
	public void setRarityLvl (int rarityLvl) {
		this.rarityLvl = rarityLvl;
	}
	
	
	// Information des Items als String
	@Override
	public String toString() {
		return "ID: " + id +
				"\nName: " + name +
				"\nBeschreibung: " + description +
				"\nWert: " + value +
				"\nSeltenheit: " + rarityLvl;
			}
	
	
	
	
}
