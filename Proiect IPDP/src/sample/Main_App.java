package sample;

import Classes.Item;
import Classes.ManagerItems;
import Classes.ManagerUsers;
import Classes.User;
import com.mongodb.client.MongoCollection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.regex.Pattern;


public class Main_App extends Application {

    Stage window;

    public static void main(String[] args) {
        System.out.println("Database Connected");
        Item it = new Item("Something", 121124, 1314, 14);
        Item it_up = new Item("Da", 1214, 131, 300, "");
        ManagerItems item = new ManagerItems();
        ManagerUsers us = new ManagerUsers();
        //item.AddItem(it_up);
        item.AskForItem(it_up);
        //item.DeleteItem(it_up);
        //item.UpdateItem(it, it_up);
        //item.VerifyStock(it);
        //item.ReserveItem(it);
        launch(args);
    }

    static final Document toDocument(User u) {
        return new Document("First Name", u.getFirstName())
                .append("Last Name", u.getLastName())
                .append("Age", u.getAge())
                .append("Username", u.username)
                .append("Password", u.password)
                .append("Mail adress", u.getMail_adress());
    }

    public static boolean verifyLogin(Document uDB, MongoCollection coll, String message, String title) {
        Document found = (Document) coll.find(uDB).first();
        if (found != null) {
            return true;
        } else {
            return AlertBox.display(title,message);
        }

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("InventoryApp");
        //window.setMaximized(true);
        Image image = new Image("/icon/images.png");
        window.getIcons().add(image);

        window.setOnCloseRequest(e -> {
            e.consume();
            if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) window.close();
            else window.getScene().getWindow();
        });

        //scene
        Scene scene = new Scene(root,600,600);

        window.setScene(scene );
        window.show();
    }

}

