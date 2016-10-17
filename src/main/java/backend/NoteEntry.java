package backend;

import org.bson.Document;

/**
 * Created by Dovashar on 16.10.2016.
 */
public class NoteEntry {
    private Document entry;

    public NoteEntry(String dateTime, String tag, String text) {
        entry = new Document().append("timestamp", dateTime).append("tag", tag).append("text", text);
    }

    public Document getEntry() {
        return entry;
    }
}
