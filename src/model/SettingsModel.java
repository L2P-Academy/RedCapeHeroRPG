package model;
	// Recayi Giousoufoglou (Student.ComCave.Duisburg)

public class SettingsModel {
	private int volume, gamma;
	private String resolution;
	
	//Constructor with parameters
	public SettingsModel(int volume, int gamma, String resolution) {
		setVolume(volume); // Use the validation method
		this.gamma = gamma;
		this.resolution = resolution;
	}
	
	// getter & setter volume
	public int getVolume() {
		return volume;	
	}
	
	// Setter with Volume Validation (0-100)
	public void setVolume(int volume) {
		if (volume < 0) {
			this.volume = 0; // Negative Volume Stop
		} else if (volume > 100) {
			this.volume = 100; // Too loud values are prevented
		} else {
			this.volume = volume; // Invalid value
		}
		this.volume = volume;
	}
	// getter & Setter gamma
	public int getGamma() {
		return gamma;
	}
	public void setGamma(int gamma) {
		this.gamma = gamma;
	}
	// getter & Setter resolution
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
}

// I am Done! 😊
