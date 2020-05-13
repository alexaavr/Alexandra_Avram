package Classes;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;

public class DuplicateFunc {

    public ObservableList<Item> duplicate = FXCollections.observableArrayList();
    public MongoCursor<Document> cursor;

    public ObservableList<Item> getItems(MongoCollection coll) {
        try {
            cursor = coll.find().iterator();

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String name = doc.get("Name").toString();
                int code = Integer.parseInt(doc.get("Code").toString());
                int amount = Integer.parseInt(doc.get("Amount").toString());
                int price = Integer.parseInt(doc.get("Price").toString());
                duplicate.add(new Item(name, code, amount, price));
            }
        } finally {
            cursor.close();
        }

        return duplicate;
    }
}
