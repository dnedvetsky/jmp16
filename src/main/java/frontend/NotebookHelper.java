package frontend;

import backend.DBMethods;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dovashar on 17.10.2016.
 */
public class NotebookHelper {
    DBMethods methods = new DBMethods();
    private Map<String, String> searchCondigion = new HashMap<>();
    private String foundEntry = "";

    public void addNote(String noteTag, String noteText) {
        System.out.println();
        methods.addToDB(noteTag, noteText);
    }

    public NotebookHelper buildSearchCondition(String key, String value) {
        searchCondigion.put(key, value);
        return this;
    }

    public void cleanSearchCondition() {
        searchCondigion.clear();
    }

    public String printAndSearchNotesByTag(String tag) {
        return this.buildSearchCondition("tag", tag).printNotesViaSearch();
    }

    public String printNotesViaSearch() {
        System.out.println("Printing");
        foundEntry = "";
        try (MongoCursor<Document> cursor = methods.getFromDB(searchCondigion)) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                foundEntry += String.format("Tag: %s, Text: %s", document.get("tag"), document.get("text"));
                if (cursor.hasNext()) foundEntry += "\n";
            }
            System.out.println(foundEntry);

        }
        return foundEntry;
    }

    public String printAllNotes() {
        System.out.println("All notes:");
        foundEntry = "";
        try (MongoCursor<Document> cursor = methods.getFromDB()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                foundEntry += String.format("Date: %s, Tag: %s, Text: %s\n", document.get("timestamp"), document.get("tag"), document.get("text"));
            }
            System.out.println(foundEntry);

        }
        return foundEntry;

    }

    public NotebookHelper deleteFoundNote() {
        System.out.println();
        methods.removeSearchResultsFromDB(searchCondigion);
        return this;
    }

    public void deleteAllNotes() {
        methods.clearNotepad();
    }
}
