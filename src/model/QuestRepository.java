package model;

import java.util.ArrayList;
import java.util.List;

public class QuestRepository {
    // Hier werden die Quests im Speicher gehalten
    private List<QuestModel> questList = new ArrayList<>();

    // Alle Quests abrufen
    public List<QuestModel> getAllQuests() {
        return questList;
    }

    // Neue Quest hinzufügen
    public void addQuest(QuestModel quest) {
        questList.add(quest);
    }
}