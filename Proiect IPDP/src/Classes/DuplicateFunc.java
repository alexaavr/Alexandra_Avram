package Classes;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import sample.AlertBox;

import java.util.regex.Pattern;

public class DuplicateFunc {

    public ObservableList<Item> duplicate = FXCollections.observableArrayList();
    public ObservableList<User> duplicateU = FXCollections.observableArrayList();
    public MongoCursor<Document> cursor;

    public static boolean verifyLogin(Document uDB, MongoCollection<Document> coll, String message, String title) {
        Document found = coll.find(uDB).first();
        if (found != null) {
            return true;
        } else {
            return AlertBox.display(title, message);
        }
    }

    public static boolean isValidMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValid(String passwordhere) {

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        boolean flag = true;
        if (passwordhere.length() < 8 || !(specailCharPatten.matcher(passwordhere).find())
                || !(UpperCasePatten.matcher(passwordhere).find()) || !(lowerCasePatten.matcher(passwordhere).find())
                || !(digitCasePatten.matcher(passwordhere).find()))
            flag = false;

        return flag;
    }

    public ObservableList<Item> getItems(MongoCollection coll) {
        try {
            cursor = coll.find().iterator();

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String name = doc.get("Name").toString();
                int code = Integer.parseInt(doc.get("Code").toString());
                int amount = Integer.parseInt(doc.get("Amount").toString());
                int price = Integer.parseInt(doc.get("Price").toString());
                duplicate.add(new Item(name, code, amount, price));
            }
        } finally {
            cursor.close();
        }

        return duplicate;
    }

    public ObservableList<User> getUsers(MongoCollection coll) {

        cursor = coll.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String username = doc.get("Username").toString();
            String password = doc.get("Password").toString();
            String LastName = doc.get("Last Name").toString();
            String FirstName = doc.get("First Name").toString();
            String Mail_adress = doc.get("Mail adress").toString();
            int Age = Integer.parseInt(doc.get("Age").toString());
            duplicateU.add(new User(FirstName, LastName, Age, username, password, Mail_adress));
        }

        return duplicateU;
    }
}
