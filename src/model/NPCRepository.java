package model;

// Denis

import java.util.ArrayList;
import java.util.List;

public class NPCRepository {

	private static final List<NPCModel> NPCLIST = new ArrayList<>();

	static {

		NPCLIST.add(new NPCModel(0,"Wache1Light","Hallo Fremder!",0,0,2,2,3));

		NPCLIST.add(new NPCModel(1,"Wache2Middle","Hier darf niemand durch!",0,0,3,3,4));

		NPCLIST.add(new NPCModel(2,"Wache3Hard","Mach keinen Ärger!",0,0,4,4,5));

		NPCLIST.add(new NPCModel(3,"Wache4Ultra","Verschwinde sofort!",0,0,6,5,6));
	}

	public static List<NPCModel> getNpcList() {
		return NPCLIST;
	}

}