import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.Scanner;


public class Main_App extends Application {

    public static void main(String[] args) {

        String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/Login";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
        MongoCollection collection = mongoDatabase.getCollection("Login");
        System.out.println("Database Connected");

        Document found = (Document) collection.find(new Document("Username", "Alexa")).first();

        Scanner s = new Scanner(System.in);
        Login log = new Login(s.nextLine(), s.nextLine());
        boolean da = log.verifyLogin(found);
        System.out.println(da);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("First attempt!");

        Button button = new Button();
        button.setText("Something");
        button.setOnAction(e -> primaryStage.close());

        StackPane leyout = new StackPane();
        leyout.getChildren().add(button);

        Scene scene = new Scene(leyout, 300, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
