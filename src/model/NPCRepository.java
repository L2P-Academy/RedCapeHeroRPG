package model;

// Denis

import java.util.ArrayList;
import java.util.List;

public class NPCRepository {

	private static final List<NPCModel> NPCLIST = new ArrayList<>();

	static {

		// Innkeeper
		NPCLIST.add(new NPCModel(0,"Wirt","Willkommen im Roten Umhang!",0,0));

		// Healer
		NPCLIST.add(new NPCModel(1,"Heilerin","Ich kann deine Wunden heilen.",0,0));

		// Merchant
		NPCLIST.add(new NPCModel(2,"Händler","Willst du etwas kaufen?",0,0));

		// Villager
		NPCLIST.add(new NPCModel(3,"Dorfbewohner","Schöner Tag heute.",0,0));
		NPCLIST.add(new NPCModel(4,"Dorfbewohnerin","Die Heilerin hat tolle Kräuter.",0,0));
		NPCLIST.add(new NPCModel(5,"Alter Dorfbewohner","Ein Blick beim Händler lohnt sich.",0,0));
		NPCLIST.add(new NPCModel(6,"Alte Dorfbewohnerin","Früher war hier alles ganz anders.",0,0));
		NPCLIST.add(new NPCModel(7,"Junger Dorfbewohner","Ich möchte später ein Held werden!",0,0));
		NPCLIST.add(new NPCModel(8,"Junge Dorfbewohnerin","Ich sammele jeden Tag schöne Blumen.",0,0));

	}

	public static List<NPCModel> getNpcList() {
		return NPCLIST;
	}

}

