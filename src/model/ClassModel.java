package model;

//Albert (Student)
public class ClassModel {
	// Attributes
	private int id;
	private String name;
	private String description;
	private int bonusStrength;
	private int bonusEndurance;
	private int bonusDamage;

	// constructor
	public ClassModel(int id, String name, String description, int bonusStrength, int bonusEndurance, int bonusDamage) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.bonusStrength = bonusStrength;
		this.bonusEndurance = bonusEndurance;
		this.bonusDamage = bonusDamage;
	}

	// Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBonusStrength() {
		return bonusStrength;
	}

	public void setBonusStrength(int bonusStrength) {
		this.bonusStrength = bonusStrength;
	}

	public int getBonusEndurance() {
		return bonusEndurance;
	}

	public void setBonusEndurance(int bonusEndurance) {
		this.bonusEndurance = bonusEndurance;
	}

	public int getBonusDamage() {
		return bonusDamage;
	}

	public void setBonusDamage(int bonusDamage) {
		this.bonusDamage = bonusDamage;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
