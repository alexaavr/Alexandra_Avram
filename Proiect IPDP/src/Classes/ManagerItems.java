package Classes;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

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

    public <T> void AddSomething(T t){
        Document d = new Document();
        collection.insertOne(d);
    }

    public void AddItem(Item item){
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        collection.insertOne(d);
    }

    public void DeleteItem(Item item){
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) collection.find(d).first();
        if(found != null){
            collection.deleteOne(d);
        }
    }

    public void UpdateItem(Item item, Item item_up){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) collection.find(query).first();

        if(found != null){
            System.out.println("Found Item");

            Bson updatedvalue = new Document("Name", item_up.name)
                    .append("Code", item_up.code)
                    .append("Amount", item_up.amount)
                    .append("Price", item_up.price);
            Bson updateoperation = new Document("$set", updatedvalue);
            collection.updateMany(found,updateoperation);
            System.out.println("Item Updated");
        }
    }

    public void VerifyStock(Item item){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) collection.find(query).first();

        if(found != null) {
            System.out.println("Found item");
            System.out.println(item.toString());
        }
        else System.out.println("Cand't Found item");
    }

    public void ReserveItem(Item item){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) collection.find(query).first();

        if(found != null) {
            Document updatedvalue = new Document("Ststus", "reserved");
            Bson updateoperation = new Document("$set", updatedvalue);
            collection.updateOne(query,updateoperation);
        }
        else System.out.println("Cand't reserve item");
    }

    public void AskForItem(Item item){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) collection.find(query).first();
        if(found == null){
            System.out.println("Please add this item for me!");
            AddItem(item);
            System.out.println("Item added!");
        }
    }
}
