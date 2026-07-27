package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
	public static Clip btnClip;
	
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
