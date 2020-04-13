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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.bson.Document;


public class Main_App extends Application {

    Stage window;
    User u ;
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

    private static final Document toDocument(User u) {
        return new Document("First Name", u.getFirstName())
                .append("Last Name", u.getLastName())
                .append("Age", u.getAge())
                .append("Username", u.username)
                .append("Password", u.password)
                .append("Mail adress", u.getMail_adress());
    }


    ////////////////////////                     INTERFACE                        //////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws Exception {
//////////////////////////////////////////////                         WINDOW                              ////////////////////////////////////////////////////////
        window = primaryStage;
        window.setTitle("Login");
        window.setMinWidth(600);
        //window.setMaximized(true);
        Image image = new Image("/icon/images.png");
        window.getIcons().add(image);

//////////////////////////////////////////////                         LOGIN SCENE                              ////////////////////////////////////////////////////////

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setAlignment(Pos.CENTER);


        GridPane grid_REGISTER = new GridPane();/////////////////////////////////////////////////////////////               GRID REGISTER
        grid_REGISTER.setPadding(new Insets(10, 10, 10, 10));
        grid_REGISTER.setVgap(8);
        grid_REGISTER.setHgap(10);
        grid_REGISTER.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        grid_REGISTER.setAlignment(Pos.CENTER);

        // scene
        Scene scene_LOGIN = new Scene(grid, 600, 300);

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
        PasswordField passInput = new PasswordField();
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
        Scene scene_REGISTER = new Scene(grid_REGISTER, 600, 300);///////////////////////////////////////////////////////////////////////////  SCENE REGISTER INIT
        Button registerButton = new Button("Register (only users)");
        registerButton.setOnAction(e -> window.setScene(scene_REGISTER));
        GridPane.setConstraints(registerButton, 0, 2);


        //Add everything to grid

        grid.getChildren().addAll(nameLabel, usernameInput, passLabel, passInput, hor, registerButton);

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(scene_LOGIN);
        });

        window.setScene(scene_LOGIN);

///////////////////////////////////////////////                         REGISTER SCENE                              ////////////////////////////////////////////////////////


        //FN label
        Label firstNameL_REGISTER = new Label("First name:");
        GridPane.setConstraints(firstNameL_REGISTER, 0, 0);

        //LN Label
        Label lastNameL_REGISTER = new Label("Last name:");
        GridPane.setConstraints(lastNameL_REGISTER, 0, 1);

        //Name Label - constrains use (child, column, row)
        Label usernameL_REGISTER = new Label("Username:");
        GridPane.setConstraints(usernameL_REGISTER, 0, 2);

        //Age label
        Label ageL_REGISTER = new Label("Age:");
        GridPane.setConstraints(ageL_REGISTER, 0, 5);

        //Pass Label
        Label passwordL_REGISTER = new Label("Password:");
        GridPane.setConstraints(passwordL_REGISTER, 0, 3);

        //E_mail label
        Label mailL_REGISTER = new Label("E-mail adress:");
        GridPane.setConstraints(mailL_REGISTER, 0, 4);



        //FN text
        TextField firstNameInput_REGISTER = new TextField("First name");
        GridPane.setConstraints(firstNameInput_REGISTER, 1, 0);

        //LN text
        TextField lastNameInput_REGISTER = new TextField("Last name");
        GridPane.setConstraints(lastNameInput_REGISTER, 1, 1);

        //UserName Input
        TextField usernameInput_REGISTER = new TextField("Username");
        GridPane.setConstraints(usernameInput_REGISTER, 1, 2);

        //Password Input
        PasswordField passInput_REGISTER = new PasswordField();
        passInput_REGISTER.setPromptText("Password");
        GridPane.setConstraints(passInput_REGISTER, 1, 3);

        //E-mail Input
        TextField mailInput_REGISTER = new TextField("E-mail adress");
        GridPane.setConstraints(mailInput_REGISTER, 1, 4);

        //age input
        TextField ageInput_REGISTER = new TextField("Age");
        GridPane.setConstraints(ageInput_REGISTER, 1, 5);

        /////buttons
        HBox hor_REGISTER = new HBox();
        hor_REGISTER.setSpacing(10);
        hor_REGISTER.setAlignment(Pos.CENTER);

        Button createAccount = new Button("Create account");
        createAccount.setOnAction(e ->{
            u = new User();
            u.setFirstName(firstNameInput_REGISTER.getText().trim());
            System.out.println(u.getFirstName());
            u.setLastName(lastNameInput_REGISTER.getText().trim());
            try{
            u.setAge((Integer.parseInt(ageInput_REGISTER.getText().trim())));}
            catch(NumberFormatException ex){
                AlertBox.display("Alert", "Error: " + ageInput_REGISTER.getText().trim().toUpperCase() + " is not a number");
            }
            u.username = usernameInput_REGISTER.getText().trim();
            u.password = passInput_REGISTER.getText().trim();
            u.setMail_adress(mailInput_REGISTER.getText().trim());
            System.out.println(u);
            collection.insertOne(Main_App.toDocument(u));
            AlertBox.display("Alerta", "IQ DIVIN CE ESTI !!!!!");////////////////////////////////////////////////////////////to main app
        });

        Button back_REGISTER = new Button("Back");
        back_REGISTER.setOnAction(e -> window.setScene(scene_LOGIN));

        hor_REGISTER.getChildren().addAll(createAccount, back_REGISTER);
        GridPane.setConstraints(hor_REGISTER, 1, 6);

        //add grid
        grid_REGISTER.getChildren().addAll(firstNameL_REGISTER, firstNameInput_REGISTER, lastNameL_REGISTER, lastNameInput_REGISTER,
                usernameL_REGISTER,usernameInput_REGISTER, passwordL_REGISTER, passInput_REGISTER, mailL_REGISTER, mailInput_REGISTER, ageL_REGISTER, ageInput_REGISTER,
                hor_REGISTER);




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

