package model;
// Albert

import java.util.*;

public class ClassRepository {
	private static final List<ClassModel> CLASSLIST = new ArrayList<ClassModel>();
	
	static {
		CLASSLIST.add(new ClassModel(0, "Ritter", "Schwertkämpfer", 5, 2, 6));
		CLASSLIST.add(new ClassModel(1, "Barbar", "Zweihandkämpfer", 4, 6, 7));
		CLASSLIST.add(new ClassModel(2, "Magier", "Zauberer", 2, 3, 5));
		CLASSLIST.add(new ClassModel(3, "Landwirt", "Itemspezialist", 4, 7, 4));
	}

	public static List<ClassModel> getClasslist() {
		return CLASSLIST;
	}
	
}
