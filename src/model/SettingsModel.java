package model;
	// Recayi Giousoufoglou (Student.ComCave.Duisburg)

public class SettingsModel {
	private int volume, gamma;
	private String resolution;
	
	//Constructor with parameters
	public SettingsModel(int volume, int gamma, String resolution) {
		this.volume = volume;
		this.gamma = gamma;
		this.resolution = resolution;
	}
	
	
	// getter & Setter gamma
	public int getVolume() {
		return volume;	
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getGamma() {
		return gamma;
	}
	public void setGamma(int gamma) {
		this.gamma = gamma;
	}
	
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
}

// I am Done! 😊
