package Classes;

import org.bson.Document;
import org.bson.conversions.Bson;
import DB.ConnectionDB;

public class ManagerUsers {

    public ManagerUsers() {
    }

    public void AddUser(User user){
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMail_adress());
        ConnectionDB.collectionLogin.insertOne(d);
    }

    public void DeleteUser(User user){
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMail_adress());
        Document found = (Document) ConnectionDB.collectionLogin.find(d).first();
        if(found != null){
            ConnectionDB.collectionLogin.deleteOne(d);
        }
    }

    public void UpdateUser(User user, User user_up){
        Document query = new Document("Username", user.username);
        Document found = (Document) ConnectionDB.collectionLogin.find(query).first();

        if(found != null){
            Bson updatedvalue = new Document("First Name", user_up.getFirstName())
                    .append("Last Name", user_up.getLastName())
                    .append("Age", user_up.getAge())
                    .append("Username", user_up.username)
                    .append("Password", user_up.password)
                    .append("Mail adress", user_up.getMail_adress());
            Bson updateoperation = new Document("$set", updatedvalue);
            ConnectionDB.collectionLogin.updateOne(found,updateoperation);
        }
    }

    public boolean findUser(User user){
        Document d = new Document("Username", user.username);
        if(ConnectionDB.collectionItem.find(d) == null)
            return false;
        else return true;
    }
}
