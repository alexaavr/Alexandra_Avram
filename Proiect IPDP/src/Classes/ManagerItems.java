package Classes;

import DB.ConnectionDB;
import org.bson.Document;

interface IManagerItems {
    void AddItem(Item item);

    void DeleteItem(Item item);

    boolean findItem(Item item);

    String displayItem(Item item);
}

public class ManagerItems implements IManagerItems {
    public ManagerItems() {
    }

    @Override
    public void AddItem(Item item) {
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        ConnectionDB.collectionItem.insertOne(d);
    }

    @Override
    public void DeleteItem(Item item) {
        Document d = new Document("Name", item.name)
                .append("Code", item.code)
                .append("Amount", item.amount)
                .append("Price", item.price);
        Document found = (Document) ConnectionDB.collectionItem.find(d).first();
        if (found != null) {
            ConnectionDB.collectionItem.deleteOne(d);
        }
    }

    @Override
    public boolean findItem(Item item) {
        boolean flag = true;
        Document d = new Document("Name", item.name);
        if (ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    @Override
    public String displayItem(Item item) {
        Document d = new Document("Name", item.name);
        Document found = (Document) ConnectionDB.collectionItem.find(d).first();
        if (found != null) {
            item.name = found.get("Name").toString();
            item.code = Integer.parseInt(found.get("Code").toString());
            item.amount = Integer.parseInt(found.get("Amount").toString());
            item.price = Integer.parseInt(found.get("Price").toString());
        }
        return item.toString();
    }
}
