package model;

// Denis

public class EnemyModel extends NPCModel {

	// Attributes
	private int maxHealth;
	private int currentHealth;

	private int damage;
	private int speed;

	private int minCoins;
	private int maxCoins;

	// Constructor
	public EnemyModel(int id, String name, int positionX, int positionY,
			int maxHealth, int damage, int speed, int minCoins, int maxCoins) {

		super(id, name, "", positionX, positionY);

		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;

		this.damage = damage;
		this.speed = speed;

		this.minCoins = minCoins;
		this.maxCoins = maxCoins;
	}

	// Max Health
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	// Current Health
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	// Damage
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	// Speed
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// Min Coins
	public int getMinCoins() {
		return minCoins;
	}

	public void setMinCoins(int minCoins) {
		this.minCoins = minCoins;
	}

	// Max Coins
	public int getMaxCoins() {
		return maxCoins;
	}

	public void setMaxCoins(int maxCoins) {
		this.maxCoins = maxCoins;
	}
}