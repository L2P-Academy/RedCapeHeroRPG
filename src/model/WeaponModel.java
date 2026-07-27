package model;

//Modell einer Waffe.


public class WeaponModel extends ItemModel {

	//Attribute
	private int damage;
	private int durability;
	private int minimumLvl;
	
	
	//Erstellt eine neue Waffe
	public WeaponModel(int id, String name, String description, int value, String texturePath, int rarityLvl, int damage, int durability, int minimumLvl) {
		
		super(id, name, description, value, texturePath, rarityLvl);
		
		this.damage = damage;
		this.durability = durability;
		this.minimumLvl = minimumLvl;
	}
	
	//Getter
	public int getDamage () {
		return damage;
	}
	
	public int getDurability () {
		return durability;
	}
	
	public int getMinimumLvl () {
		return minimumLvl;
	}
	
	//Setter
	public void setDamage (int damage) {
		this.damage = damage;
	}
	
	public void setDurability (int durability) {
		this.durability = durability;
	}
	
	public void setMinimumLvl (int minimumLvl) {
		this.minimumLvl = minimumLvl;
	}
		
	
}
