package Classes;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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
}
