package controller;

import java.io.*;
import model.GameStateModel;


public class SaveGameController 
{

	// Der Dateiname, unter dem gespeichert wird
    private String saveFileName = "savegame.dat";
    
	// Methode 1: Spielstand speichern
    // Wir übergeben das aktuelle GameStateModel an den Controller
    public void saveGame(GameStateModel currentState) {
        
    	System.out.println("Versuche Spielstand zu speichern...");

        // Der Try-Block: Wir versuchen, die Datei zu schreiben
        try {
            // 1. Wir öffnen einen Stream (einen Kanal) zur Datei "savegame.dat"
            FileOutputStream fileOut = new FileOutputStream(saveFileName);
            
            // 2. Wir nutzen den ObjectOutputStream, um unser Objekt einzufrieren und durch den Kanal zu schicken
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            // 3. Wir schreiben das gesamte Spielstand-Objekt in die Datei
            objectOut.writeObject(currentState);
            
            // 4. Kanal wieder schließen, damit die Datei freigegeben wird
            objectOut.close();
            fileOut.close();
            
            System.out.println("Spielstand wurde erfolgreich gespeichert!");

        } 
        // Der Catch-Block: Falls etwas schiefgeht (IOException = Input/Output Fehler)
        catch (IOException e) {
            System.out.println("Fehler beim Speichern des Spielstands!");
            e.printStackTrace(); // Das druckt die genaue Fehlermeldung in die Konsole
        }
    }

    // Methode 2: Spielstand laden
    // Diese Methode gibt uns als Ergebnis ein GameStateModel zurück
    public GameStateModel loadGame() {
        
System.out.println("Versuche Spielstand zu laden...");
        
        // Wir brauchen eine Variable, in der wir den geladenen Spielstand speichern.
        // Falls das Laden fehlschlägt, bleibt sie auf "null" (nichts).
        GameStateModel loadedState = null;

        try {
            // 1. Das Rohr ZUM Lesen legen
            FileInputStream fileIn = new FileInputStream(saveFileName);
            
            // 2. Die Maschine anschließen, die Bytes wieder zu Objekten macht
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            
            // 3. Das Objekt lesen. 
            // Das (GameStateModel) davor ist das "Casting". Wir zwingen das "irgendein Objekt" 
            // in die Form unseres GameStateModels.
            loadedState = (GameStateModel) objectIn.readObject();
            
            // 4. Aufräumen
            objectIn.close();
            fileIn.close();
            
            System.out.println("Spielstand erfolgreich geladen!");
            
        } catch (FileNotFoundException e) {
            // Spezieller Catch: Was ist, wenn die Datei gar nicht existiert? (z.B. beim ersten Start)
            System.out.println("Kein Speicherstand gefunden. Es wird ein neues Spiel gestartet.");
            
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei!");
            e.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            // Spezieller Catch: Was ist, wenn die Datei ein Objekt enthält, dessen Bauplan wir nicht haben?
            System.out.println("Klasse nicht gefunden! Die Speicherdatei ist möglicherweise fehlerhaft.");
            e.printStackTrace();
        }

        // Am Ende geben wir den geladenen Spielstand zurück 
        // (oder null, falls es einen Fehler gab)
        return loadedState;
    }
   }



/*
 * 
 * IPMPLEMENTS Serializable
 * 
GameStateModel (Die Wurzel, von Dominik)

PlayerModel (Dein Modell)

ClassModel (Von Albert)

ItemModel (Von Baris – und damit auch die Unterklassen WeaponModel und PotionModel)

NPCModel (Von Denis)

QuestModel (Von Ali)

SettingsModel (Von Recayi – falls das Teil des GameState ist)
 * 
 */
	
	
	
	
	
	
	
	


