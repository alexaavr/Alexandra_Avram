package Classes;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ManagerUsers {
    User users;

    String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/Login";
    MongoClientURI clientURI = new MongoClientURI(uri);
    MongoClient mongoClient = new MongoClient(clientURI);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
    MongoCollection collection = mongoDatabase.getCollection("Login");


    public ManagerUsers(User users) {
        this.users = users;
    }

    public ManagerUsers() {
    }

    public void AddUser(User user){
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMail_adress());
        collection.insertOne(d);
    }

    public void DeleteUser(User user){
        Document d = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMail_adress());
        Document found = (Document) collection.find(d).first();
        if(found != null){
            collection.deleteOne(d);
        }
    }

    public void UpdateUser(User user, User user_up){
        Document query = new Document("First Name", user.getFirstName())
                .append("Last Name", user.getLastName())
                .append("Age", user.getAge())
                .append("Username", user.username)
                .append("Password", user.password)
                .append("Mail adress", user.getMail_adress());
        Document found = (Document) collection.find(query).first();

        if(found != null){
            System.out.println("Found User");

            Bson updatedvalue = new Document("First Name", user_up.getFirstName())
                    .append("Last Name", user_up.getLastName())
                    .append("Age", user_up.getAge())
                    .append("Username", user_up.username)
                    .append("Password", user_up.password)
                    .append("Mail adress", user_up.getMail_adress());
            Bson updateoperation = new Document("$set", updatedvalue);
            collection.updateOne(found,updateoperation);
            System.out.println("User Updated");
        }
    }
}
