package model;

import java.util.ArrayList;
import java.util.List;


// Verwaltung der Items:

public class ItemRepository {
	
	// Liste aller Items
	private List<ItemModel> items;

	//neues ItemRepository und initial. Itemliste
	public ItemRepository() {
		items=new ArrayList<>();
		initializeItems();
	}
	
	//Standard Items des Spiels
	private void initializeItems() {
		
		//Waffen
		items.add(new WeaponModel(
				1, 											//id
				"Holzschwert", 								//name
				"Ein einfaches Schwert aus Holz.",			//description
				10,											//value
				"textures/holzschwert.png",					//texturePath
				1,											//rarityLvl
				5,											//damage
				50,											//durability
				1));										//miminumLvl
	
		
		items.add(new WeaponModel(
				2,											//id
				"Eisenschwert",								//name
				"Ein Schwert aus Eisen.",					//descrption
				25,											//value
				"textures/eisenschwert.png",				//texturePath
				2,											//rarityLvl
				9,											//damage
				90,											//durabilty
				3));										//minimumLvl
		
		
		
		//Zaubertränke
		items.add(new PotionModel (
				3,											//id
				"Heiltrank",								//name
				"Heilt den Spieler um 25 Lebenspunkte",		//description
				10,											//value
				"textures/heiltrank.png",					//texturePath
				1,											//rarityLvl
				25,											//statChange
				"health",									//effectType
				0));										//durationSeconds
		
		items.add(new PotionModel (
				4,											//id
				"Krafttrank",								//name
				"Weckt die Kraft des RedCape in dir",		//description
				25,											//value
				"textures/krafttrank.png",					//texturePath
				2,											//rarityLvl
				50,											//statChange
				"strength",									//effectType
				15));										//durationSeconds
		
	}
		//Getter für die Rückgabe aller Items
		public List<ItemModel> getItems() {
			return items;
		}
	
}
