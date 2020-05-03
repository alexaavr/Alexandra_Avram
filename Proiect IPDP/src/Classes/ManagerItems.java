package Classes;

import org.bson.Document;
import org.bson.conversions.Bson;
import DB.ConnectionDB;

public class ManagerItems {
    Item items;

    public ManagerItems() {
    }

    public ManagerItems(Item items) {
        this.items = items;
    }

    public <T> void AddSomething(T t){
        Document d = new Document();
        ConnectionDB.collectionItem.insertOne(d);
    }

    public void AddItem(Item item){
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        ConnectionDB.collectionItem.insertOne(d);
    }

    public void DeleteItem(Item item){
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) ConnectionDB.collectionItem.find(d).first();
        if(found != null){
            ConnectionDB.collectionItem.deleteOne(d);
        }
    }

    public void UpdateItem(Item item, Item item_up){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();

        if(found != null){
            System.out.println("Found Item");

            Bson updatedvalue = new Document("Name", item_up.name)
                    .append("Code", item_up.code)
                    .append("Amount", item_up.amount)
                    .append("Price", item_up.price);
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionItem.updateMany(found,updateoperation);
            System.out.println("Item Updated");
        }
    }

    public void VerifyStock(Item item){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();

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
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();

        if(found != null) {
            Document updatedvalue = new Document("Ststus", "reserved");
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionItem.updateOne(query,updateoperation);
        }
        else System.out.println("Cand't reserve item");
    }

    public void AskForItem(Item item){
        Document query = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();
        if(found == null){
            System.out.println("Please add this item for me!");
            AddItem(item);
            System.out.println("Item added!");
        }
    }
}
