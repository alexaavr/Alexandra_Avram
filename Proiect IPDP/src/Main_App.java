import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main_App extends Application {

    public static void main(String[] args) {

/*        String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/Login";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
        MongoCollection collection = mongoDatabase.getCollection("Login");
        System.out.println("Database Connected");

        Document found = (Document) collection.find(new Document("Username", "Alexa")).first();

        Scanner s = new Scanner(System.in);
        Login log = new Login(s.nextLine(), s.nextLine());
        boolean da = log.verifyLogin(found);
        System.out.println(da);*/

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Stage window = primaryStage;
        window.setTitle("Login");
        window.setMaximized(true);

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        TextField nameInput = new TextField("username");
        GridPane.setConstraints(nameInput, 1, 0);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        //Login
        HBox hor = new HBox();
        hor.setSpacing(10);

        Button loginButton = new Button("Log In");
        Button clearButton = new Button("Clear");
        Button quitButton = new Button("Quit");

        hor.setAlignment(Pos.CENTER);
        hor.getChildren().addAll(loginButton, clearButton, quitButton);
        GridPane.setConstraints(hor,1,2);

        //Add everything to grid
        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, hor);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
