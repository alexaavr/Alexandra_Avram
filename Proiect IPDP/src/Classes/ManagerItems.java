package Classes;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ManagerItems {
    Item items;

    String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/Login";
    MongoClientURI clientURI = new MongoClientURI(uri);
    MongoClient mongoClient = new MongoClient(clientURI);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
    MongoCollection collection = mongoDatabase.getCollection("Items");


    public ManagerItems() {
    }

    public ManagerItems(Item items) {
        this.items = items;
    }

    public void AddItem(Item item){
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount);
        collection.insertOne(d);
    }

    public void DeleteItem(Item item){
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount);
        Document found = (Document) collection.find(d).first();
        if(found != null){
            collection.deleteOne(d);
        }
    }

    public void UpdateItem(Item item, Item item_up){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount);
        Document d = new Document("Name", item_up.name)
                .append("Code", item_up.code)
                .append("Amount", item_up.amount);
        Document d_up = new Document();
        d_up.append("$set", d);
        collection.updateOne(query, d_up);
    }  ///////////////////////////////////////////////////////////intreaba de asta
}
