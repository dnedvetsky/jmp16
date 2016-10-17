package backend;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dovashar on 16.10.2016.
 */
public class DBMethods {
    private final MongoClient mongoClient;
    private final MongoDatabase db;
    private final MongoCollection<Document> dbCollection;

    public DBMethods() {
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("notebook");
        dbCollection = db.getCollection("notebook");
    }

    public void addToDB(String tag, String text) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String date = cal.getTime().toString();
        System.out.println(String.format("Adding entry with following values: Date = %s; Tag = %s; Text = %s", date, tag, text));
        dbCollection.insertOne(new NoteEntry(date, tag, text).getEntry());
    }

    public MongoCursor<Document> getFromDB(Map<String, String> searchConditions) {
        BasicDBObject query = new BasicDBObject();
        query.putAll(searchConditions);
        return dbCollection.find(query).iterator();
    }

    public MongoCursor<Document> getFromDB() {
        return dbCollection.find().iterator();
    }

    public void removeFromDB(String... tag) {
        List<String> tags = Arrays.asList(tag);
        for (String t : tags) {
            dbCollection.findOneAndDelete(new Document().append("tag", t));
        }
    }

    public void clearNotepad() {
        dbCollection.drop();
    }

    public void removeSearchResultsFromDB(Map<String, String> searchConditions) {
        BasicDBObject query = new BasicDBObject();
        query.putAll(searchConditions);
        dbCollection.findOneAndDelete(query);
    }
}
