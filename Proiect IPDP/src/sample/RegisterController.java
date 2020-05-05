package sample;
import Classes.User;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    //GENERAL USE
    @FXML private Stage stage = new Stage();
    //LOGIN SCENE
    @FXML private TextField usernameInput = new TextField();
    @FXML private TextField firstnameInput = new TextField();
    @FXML private TextField lastnameInput = new TextField();
    @FXML private TextField mailInput = new TextField();
    @FXML private TextField ageInput = new TextField();
    @FXML private PasswordField passInput = new PasswordField();
    @FXML private Button registerButton = new Button();

    @FXML private Button quitButton = new Button();
    @FXML private CheckBox checkBox_Register = new CheckBox();



    //REGISTER ACTIONS

    //CHECK BOX ACTION
    @FXML
    private void checkBox_RegisterAction(){
        if (checkBox_Register.isSelected()){
            passInput.setPromptText(passInput.getText());
            passInput.setText("");

        }else {
            passInput.setText(passInput.getPromptText());
            passInput.setPromptText("");
        }
    }

    //QUIT BUTTON ACTION
    @FXML
    private void quitButtonAction() {
        stage = (Stage) quitButton.getScene().getWindow();
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) stage.close();
        else stage.getScene().getWindow();
    }

    //CLEAR BUTTON ACTION
    @FXML
    private void clearButtonAction(){
        firstnameInput.clear();
        lastnameInput.clear();
        usernameInput.clear();
        passInput.clear();
        mailInput.clear();
        ageInput.clear();
    }

    //BACK BUTTON ACTION
    @FXML
    private void backButonnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent RegisterParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene RegisterScene = new Scene(RegisterParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(RegisterScene);
        window.show();
    }

    //REGISTER BUTTON ACTION
    @FXML
    private void registerButtonAction(javafx.event.ActionEvent actionEvent){
        Exception e = null;
        try {
            User u = new User();
            u.username = usernameInput.getText().trim();
            u.password = passInput.getText().trim();
            //password requires///////REZOLVA ASTA CA NU MERGE BINE
            if (passInput.getText().toString().length() < 8)  AlertBox.display("Alert", "Error: Password must contain at least 8 characters!");
            u.setMail_adress(mailInput.getText().trim());
            if (Main_App.isValid(u.getMail_adress()) != true)  {
                AlertBox.display("Alert", "Error: Incorrect mail address!");

            }
            u.setFirstName(firstnameInput.getText().trim());
            u.setLastName(lastnameInput.getText().trim());
            u.setAge((Integer.parseInt(ageInput.getText().trim())));
            ConnectionDB.collectionLogin.insertOne(Main_App.toDocument(u));

            AlertBox.display("Congratulations", "ou registered successfully");
            Parent Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene Scene = new Scene(Parent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(Scene);
            window.show();

        }
        catch(NumberFormatException | IOException ex){
            AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
