package model;
// Albert

import java.util.*;

public class ClassRepository {
	private static final List<ClassModel> CLASSLIST = new ArrayList<ClassModel>();
	
	static {
		CLASSLIST.add(new ClassModel(0, "Ritter", "Schwertkämpfer .Du bist die Sichel der Partei, mit deiner SichelSchnittwaffe zersichelst du den Spreu vom Weizen. ALLES FÜR DIE PARTEI!!!", 5, 2, 6));
		CLASSLIST.add(new ClassModel(1, "Barbar", "Zweihandkämpfer. Der große Zweihandhammer ist dein Werkzeug, Die Gegner sind die Nägel, mit jedem Schlag baust du am Fundament des Kommunismus mit. ALLES FÜR DIE PARTEI!!!", 4, 6, 7));
		CLASSLIST.add(new ClassModel(2, "Magier", "Zauberer. Dein Zauberbuch ist unser Zauberbuch Genosse.Versprüh den roten Zauber auf die Welt.bekehre die Gegner.ALLES FÜR DIE PARTEI!!! ", 2, 3, 5));
		CLASSLIST.add(new ClassModel(3, "Landwirt", "Itemspezialist. Verteile die roten Tränke unter deinen Genossen,Kalaschnikows und Molotows unter deinen Feinden, und das bitte Sozialgerecht. ALLES FÜR DIE PARTEI!!!!", 4, 7, 4));
	}

	public static List<ClassModel> getClasslist() {
		return CLASSLIST;
	}
	
}
