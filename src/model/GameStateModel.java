package model;

import java.sql.Date;
import java.util.HashMap;

//Dominik TheEndles
public class GameStateModel {

	private String playerName;
	private Date lastSavedAt;
	private PlayerModel playerModel;
	private int score;
	private int tileX;
	private int tileY;
	private HashMap<String, String> tileInformation;	
	
	public GameStateModel(String playerName, Date lastSavedAt, PlayerModel playerModel, int score, int tileX, int tileY,
			HashMap<String, String> tileInformation) {
		super();
		this.playerName = playerName;
		this.lastSavedAt = lastSavedAt;
		this.playerModel = playerModel;
		this.score = score;
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileInformation = tileInformation;		
	}

	// Getter und Setter Methoden
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Date getLastSavedAt() {
		return lastSavedAt;
	}

	public void setLastSavedAt(Date lastSavedAt) {
		this.lastSavedAt = lastSavedAt;
	}
	
	public PlayerModel getPlayerModel() {
		return playerModel;
	}

	public void setPlayerModel(PlayerModel playerModel) {
		this.playerModel = playerModel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public HashMap<String, String> getTileInformation() {
		return tileInformation;
	}

	public void setTileInformation(HashMap<String, String> tileInformation) {
		this.tileInformation = tileInformation;
	}

}
