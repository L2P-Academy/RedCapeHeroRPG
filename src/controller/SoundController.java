package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
	public static Clip btnClip;
	public static Clip musicClip;
	private int sfxVolume = 100;
	private static SoundController instance;
	
	// constructor for single instance
	public SoundController() {
		instance = this;
	}
	
	public static SoundController getInstance() {
		if (instance == null) {
			instance = new SoundController();
		}
		return instance;
	}
	
	public static Clip playMusicLoop(String filePath) {
		try {
			if (musicClip == null || !musicClip.isOpen()) {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
				musicClip = AudioSystem.getClip();
				musicClip.open(audio);
				musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				stopMusicLoop();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicClip;
	}
	
	public static void stopMusicLoop() {
		if (musicClip != null) {
			musicClip.stop();
			musicClip.close();
			musicClip = null;
		}
	}
	
	public static void playBtnSound() {
		if (btnClip != null && btnClip.isOpen()) {
			btnClip.stop();
			btnClip.flush();
		}
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("res/sounds/button_click.wav"));
			btnClip = AudioSystem.getClip();
			btnClip.open(audioStream);
			btnClip.setFramePosition(0);
			btnClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
