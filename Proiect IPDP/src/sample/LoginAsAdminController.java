package sample;

import Classes.DuplicateFunc;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginAsAdminController implements Initializable {

    //GENERAL USE
    @FXML
    private Stage stage = new Stage();

    @FXML
    private TextField serialInput = new TextField();
    @FXML
    private TextField idInput = new TextField();
    @FXML
    private PasswordField passInput = new PasswordField();
    @FXML
    private Button quitButton = new Button();
    @FXML
    private CheckBox checkBox_Login = new CheckBox();

    //QUIT BUTTON ACTION
    @FXML
    private void quitButtonAction() {
        stage = (Stage) quitButton.getScene().getWindow();
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) stage.close();
        else stage.getScene().getWindow();
    }

    //CLEAR BUTTON ACTION
    @FXML
    private void clearButtonAction() {
        serialInput.clear();
        idInput.clear();
        passInput.clear();
    }

    //LOGIN BUTTON ACTION
    @FXML
    private void loginButtonAction() throws IOException {
        if (serialInput.getText().equals("") || idInput.getText().equals("") || passInput.getText().equals(""))
            AlertBox.display("Alert", "Error: To login you must complete all fields!");
        else {
            Document d = new Document("Login serial", serialInput.getText().trim()).append("admin ID", idInput.getText().trim()).append("Password", passInput.getText().trim());
            if (DuplicateFunc.verifyLogin(d, ConnectionDB.collectionAdmin, "Wrong Adimin ID or Login serial number or Password", "Alert!")) {
                Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("AfterLoginAdmin.fxml"));
                Main_App.window.getScene().setRoot(LoginAdminParent);
            }
        }
    }

    //BACK BUTTON ACTION
    @FXML
    private void backButoonAction() throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main_App.window.getScene().setRoot(LoginAdminParent);
    }

    // CHACK BOX ACTION
    @FXML
    private void checkBox_LoginAction() {
        if (checkBox_Login.isSelected()) {
            passInput.setPromptText(passInput.getText());
            passInput.setText("");

        } else {
            passInput.setText(passInput.getPromptText());
            passInput.setPromptText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
