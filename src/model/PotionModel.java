package model;


//Modell eines Tranks
public class PotionModel extends ItemModel {

	//Attribute
	private int statChange;
	private String effectType;
	private int durationSeconds;
	
	//Erstellt einen neuen Trank
	public PotionModel (int id, String name, String description, int value, String texturePath, int rarityLvl, int statChange, String effectType, int durationSeconds) {
		
		super(id, name, description, value, texturePath, rarityLvl);
		
		this.statChange = statChange;
		this.effectType = effectType;
		this.durationSeconds = durationSeconds;
				
	}
	
	//Getter
	public int getStatChange () {
		return statChange;
	}
	
	public String getEffectType () {
		return effectType;
	}
	
	public int getDurationSeconds () {
		return durationSeconds;
	}
	
	//Setter
	public void setStatChange(int statChange) {
		this.statChange = statChange;
	}
	
	public void setEffectType (String effectType) {
		this.effectType = effectType;
	}
	
	public void setDurationSeconds (int durationSeconds) {
		this.durationSeconds = durationSeconds;
	}
	
	
}
