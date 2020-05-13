package Classes;

import DB.ConnectionDB;
import org.bson.Document;
import org.bson.conversions.Bson;

interface IDuplicate {
    void UpdateItem(Item item, Item item_up);

    void DeleteUser(User user);
}

public class ManagerDuplicate implements IDuplicate {

    @Override
    public void UpdateItem(Item item, Item item_up) {
        Document query = new Document("Code", item.code);
        Document found = (Document) ConnectionDB.collectionItem.find(query).first();

        if (found != null) {
            Bson updatedvalue = new Document("Name", item_up.name)
                    .append("Code", item_up.code)
                    .append("Amount", item_up.amount)
                    .append("Price", item_up.price);
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionItem.updateMany(found, updateoperation);
        }
    }

    @Override
    public void DeleteUser(User user) {
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMail_adress());
        Document found = (Document) ConnectionDB.collectionLogin.find(d).first();
        if (found != null) {
            ConnectionDB.collectionLogin.deleteOne(d);
        }
    }
}
