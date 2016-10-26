package backend;

import org.bson.Document;

import java.util.NoSuchElementException;

/**
 * Created by Dovashar on 16.10.2016.
 */
public class NoteEntry {
    private Document entry;

    public Document setEntry(String dateTime, String tag, String text) {
        return new Document().append("timestamp", dateTime).append("tag", tag).append("text", text);
    }

    public NoteEntry(String dateTime, String tag, String text) {
        entry = setEntry(dateTime, tag, text);
    }



    public Document getEntry() {
        return entry;
    }

    public String getTimestamp() {
        return entry.getString("timestamp");
    }

    public String getTag() {
        return entry.getString("tag");
    }

    public String getText() {
        return entry.getString("text");
    }

    public String getErrorText() {
        throw new NoSuchElementException();
    }
}
