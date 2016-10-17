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

    public NotebookHelper printNotesViaSearch() {
        System.out.println("Printing");
        try (MongoCursor<Document> cursor = methods.getFromDB(searchCondigion)) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(String.format("Date: %s, Tag: %s, Text: %s", document.get("timestamp"), document.get("tag"), document.get("text")));
            }
        }
        return this;
    }

    public void printAllNotes() {
        System.out.println();
        try (MongoCursor<Document> cursor = methods.getFromDB()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(String.format("Date: %s, Tag: %s, Text: %s", document.get("timestamp"), document.get("tag"), document.get("text")));
            }
        }

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
