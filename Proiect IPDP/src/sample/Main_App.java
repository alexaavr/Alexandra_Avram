package sample;

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

    public static Stage window;

    public static void main(String[] args) {
        System.out.println("Database Connected");
        launch(args);
    }

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("InventoryApp");
        Image image = new Image("/icon/images.png");
        window.getIcons().add(image);

        window.setOnCloseRequest(e -> {
            e.consume();
            if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) window.close();
            else window.getScene().getWindow();
        });

        //scene
        Scene scene = new Scene(root, 1000, 600);
        window.setScene(scene);
        window.show();
    }

}

