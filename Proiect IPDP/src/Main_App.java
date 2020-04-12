import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Scanner;


public class Main_App extends Application {

    public static void main(String[] args) {

/*        String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/test";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
        MongoCollection collection = mongoDatabase.getCollection("Login");
        System.out.println("Database Connected");*/
        Scanner s = new Scanner(System.in);
        Login log_principal = new Login("Alexa", "alexa");
        Login log = new Login(s.nextLine(), s.nextLine());
        System.out.println(log.verifyLogin(log_principal));

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
/*
    public static boolean verifyLogin(String username, String password){
        boolean found = false;
        String tUsername = "Alexa";
        String tPassword = "alexa";
        if(username.equals(tUsername)){
            if(password.equals(tPassword)){
                found = true;
            }
        }

        return found;
    }*/
}
