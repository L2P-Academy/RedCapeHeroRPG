package model;

import java.util.ArrayList;
import java.util.List;

public class QuestRepository {

    private static final List<QuestModel> QUEST_LIST = new ArrayList<>();

    static {
        QUEST_LIST.add(new QuestModel(0, "Das Erwachen", "Der Schmied hat ein Schwert aus den Trümmern gerettet. Hol es dir!", false));
        QUEST_LIST.add(new QuestModel(1, "Erste Wölfe", "Töte 5 Schattenwölfe im dunklen Wald, um den Weg freizumachen.", false));
        QUEST_LIST.add(new QuestModel(2, "Die verlassene Mine", "Finde den alten Mineneingang und beschaffe 3 Leuchtkristalle.", false));
        QUEST_LIST.add(new QuestModel(3, "Der verfluchte Trank", "Bringe dem Alchemisten die Zutaten für ein Gegengift.", false));
        QUEST_LIST.add(new QuestModel(4, "Wache der Finsternis", "Besiege den Torwächter der alten Festung.", false));
        QUEST_LIST.add(new QuestModel(5, "Das verlorene Buch", "Suche in den Archiven nach den Glyphen des Schattenfürsten.", false));
        QUEST_LIST.add(new QuestModel(6, "Schmiedefeuer", "Schmiede deine Klinge neu mit dem magischen Erz.", false));
        QUEST_LIST.add(new QuestModel(7, "Hinterhalt im Sumpf", "Überlebe den Angriff der Schattenkultisten.", false));
        QUEST_LIST.add(new QuestModel(8, "Das Portalsiegel", "Zerstöre die 3 Dämonensteine, um das Tor zu schwächen.", false));
        QUEST_LIST.add(new QuestModel(9, "Der Schattenfürst", "Betritt den Thronsaal und vernichte den Schattenfürsten!", false));
    }

    public static List<QuestModel> getQuestList() {
        return QUEST_LIST;
    }

    public void addQuest(QuestModel quest) {
        QUEST_LIST.add(quest);
    }
}