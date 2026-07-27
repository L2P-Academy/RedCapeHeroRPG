package model;

import java.util.ArrayList;
import java.util.List;

public class QuestRepository {

    private static final List<QuestModel> QUEST_LIST = new ArrayList<>();

    static {
        QUEST_LIST.add(new QuestModel(0, "Startquest", "Der Schmied hat ein Schwert für dich angefertigt!", false));
        QUEST_LIST.add(new QuestModel(1, "Nächste Quest...", "", false));
        QUEST_LIST.add(new QuestModel(2, "Übernächste Quest...", "", false));
    }

    public static List<QuestModel> getQuestList() {
        return QUEST_LIST;
    }

    public void addQuest(QuestModel quest) {
        QUEST_LIST.add(quest);
    }
}