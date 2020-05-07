package Classes;

import DB.ConnectionDB;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ManagerItems {
    public ManagerItems() {
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
        Document query = new Document("Code", item.code);
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();

        if(found != null){
            Bson updatedvalue = new Document("Name", item_up.name)
                    .append("Code", item_up.code)
                    .append("Amount", item_up.amount)
                    .append("Price", item_up.price);
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionItem.updateMany(found,updateoperation);
        }
    }

    public boolean findItem(Item item){
        boolean flag = true;
        Document d = new Document("Name", item.name);
        if(ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    public String displayItem(Item item){
        Document d = new Document("Name", item.name);
        Document found = (Document) ConnectionDB.collectionItem.find(d).first();
        if(found != null){
            item.name = found.get("Name").toString();
            item.code = Integer.parseInt(found.get("Code").toString());
            item.amount = Integer.parseInt(found.get("Amount").toString());
            item.price = Integer.parseInt(found.get("Price").toString());
        }
        return item.toString();
    }
}
