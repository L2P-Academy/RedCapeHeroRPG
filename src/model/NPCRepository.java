package model;
//Denis

import java.util.*;

public class NPCRepository {
	private static final List<NPCModel> NPCLIST = new ArrayList<NPCModel>();
	
	static {
		NPCLIST.add(new NPCModel(0, "Wache1Light", "WacheLight", 2, 2, 3));
		NPCLIST.add(new NPCModel(1, "Wache2Middle", "WacheMiddle", 3, 2, 4));
		NPCLIST.add(new NPCModel(2, "Wache3Hard", "WacheHard", 4, 2, 6));
		NPCLIST.add(new NPCModel(3, "Wache4Ultra", "WacheUltra", 6, 5, 6));
	}

	public static List<NPCModel> getNpclist() {
		return NPCLIST;
	}
	
}
