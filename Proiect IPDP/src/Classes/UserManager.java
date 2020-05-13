package Classes;

import DB.ConnectionDB;
import org.bson.Document;

interface IUser {
    boolean findItem(Item item);

    boolean findItembyCode(Item item);

    String displayItem(Item item);

    void UpdateItem(Item item, Item item_up);

    void DeleteUser(User user);
}

public class UserManager implements IUser {

    ManagerDuplicate d = new ManagerDuplicate();

    static String getString(Item item) {
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

    @Override
    public boolean findItem(Item item) {
        boolean flag = true;
        Document d = new Document("Name", item.name);
        if (ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    @Override
    public boolean findItembyCode(Item item) {
        boolean flag = true;
        Document d = new Document("Code", item.code);
        if (ConnectionDB.collectionItem.find(d).first() == null)
            flag = false;
        return flag;
    }

    @Override
    public String displayItem(Item item) {
        return getString(item);
    }

    @Override
    public void UpdateItem(Item item, Item item_up) {
        d.UpdateItem(item, item_up);
    }

    @Override
    public void DeleteUser(User user) {
        d.DeleteUser(user);
    }
}
