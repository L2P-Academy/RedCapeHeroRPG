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
		
	// Angriff mit einer Waffe
	public void attack() {
		if (durability > 0) {
			System.out.println("Angriff mit" + getName() + "! Schaden:" + damage);
			durability--;
			
	} else {
			System.out.println (getName()+ "kann nicht mehr benutz werden.");
			}
		
	}
	
	//Information der Waffe als String
	@Override
	public String toString() {
		return super.toString() +
				"\nSchaden: " + damage +
				"\nHaltbarkeit: " + durability +
				"\nMindestlevel: " + minimumLvl;
	}
	
}
