import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bson.Document;


public class Main_App extends Application {

    Stage window;
    Document d;
    String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/Login";
    MongoClientURI clientURI = new MongoClientURI(uri);
    MongoClient mongoClient = new MongoClient(clientURI);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
    MongoCollection collection = mongoDatabase.getCollection("Login");

    public static void main(String[] args) {
        System.out.println("Database Connected");
        launch(args);
    }
////////////////////////                     DATABASE                        //////////////////////////////////////////////////////////////////////////////

    private static final DBObject toDBObject(User u) {
        return new BasicDBObject("First Name", u.getFirstName())
                .append("Last Name", u.getLastName())
                .append("Age", u.getAge())
                .append("Username", u.username)
                .append("Password", u.password)
                .append("Mail adress", u.getMail_adress());
    }


    ////////////////////////                     INTERFACE                        //////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Login");
        window.setMinWidth(600);
        //window.setMaximized(true);

//////////////////////////////////////////////                         LOGIN SCENE                              ////////////////////////////////////////////////////////

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));

        // scene
        Scene scene_LOGIN = new Scene(grid, 300, 200);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        TextField usernameInput = new TextField("username");
        GridPane.setConstraints(usernameInput, 1, 0);

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
        loginButton.setOnAction(e -> {
            d = new Document( "Username", usernameInput.getText().trim()).append("Password", passInput.getText().trim());
            verifyLogin(d);
        });

        Button login_adminButton = new Button("Log In As Admin");

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            usernameInput.clear();
            passInput.clear();
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> closeProgram(scene_LOGIN));

        hor.setAlignment(Pos.CENTER);
        hor.getChildren().addAll(loginButton, login_adminButton, clearButton, quitButton);
        GridPane.setConstraints(hor, 1, 2);

        //register
        Button registerButton = new Button("Register (only users)");
        GridPane.setConstraints(registerButton, 0, 2);


        //Add everything to grid
        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(nameLabel, usernameInput, passLabel, passInput, hor, registerButton);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(scene_LOGIN);
        });

        window.setScene(scene_LOGIN);

///////////////////////////////////////////////                         REGISTER SCENE                              ////////////////////////////////////////////////////////

        window.show();
    }

    private void closeProgram(Scene current_scene) {
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) {
            window.close();
        } else {
            window.setScene(current_scene);
        }
    }

    private void verifyLogin(Document uDB) {
        Document found = (Document) collection.find(uDB).first();
        if (found != null) {
            AlertBox.display("Alert", "Esti o IDIOATA, dar te-ai descurcat!!!!!");
        } else {
            AlertBox.display("Alert", "Username or password is wrong!");
        }
    }
}

