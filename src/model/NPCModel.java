package model;

// Denis
public class NPCModel {

	// attributes
	private int id;
	private String name;
	private String dialog;
	private int positionX;
	private int positionY;
	private int strength;
	private int endurance;
	private int damage;

	// constructor
	public NPCModel(int id, String name, String dialog, int positionX, int positionY,
			int strength, int endurance, int damage) {
		super();
		this.id = id;
		this.name = name;
		this.dialog = dialog;
		this.positionX = positionX;
		this.positionY = positionY;
		this.strength = strength;
		this.endurance = endurance;
		this.damage = damage;
	}

	// getter & setter
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

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}