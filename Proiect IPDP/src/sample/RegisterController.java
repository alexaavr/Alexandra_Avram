package sample;

import Classes.AdminManager;
import Classes.DuplicateFunc;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    @FXML
    private Stage stage = new Stage();
    private User u = new User();
    private AdminManager am = new AdminManager();

    //LOGIN SCENE
    @FXML
    private TextField usernameInput = new TextField();
    @FXML
    private TextField firstnameInput = new TextField();
    @FXML
    private TextField lastnameInput = new TextField();
    @FXML
    private TextField mailInput = new TextField();
    @FXML
    private TextField ageInput = new TextField();
    @FXML
    private PasswordField passInput = new PasswordField();
    @FXML
    private TextField passInput_Visible= new TextField();
    @FXML
    private Button quitButton = new Button();
    @FXML
    private CheckBox checkBox_Register = new CheckBox();

    //CHECK BOX ACTION
    @FXML
    private void checkBox_RegisterAction() {
        LoginAsAdminController.check_box(checkBox_Register, passInput_Visible, passInput);
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
    private void clearButtonAction() {
        firstnameInput.clear();
        lastnameInput.clear();
        usernameInput.clear();
        passInput.clear();
        mailInput.clear();
        ageInput.clear();
    }

    //BACK BUTTON ACTION
    @FXML
    private void backButonnAction() throws IOException {
        Parent RegisterParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Main_App.window.getScene().setRoot(RegisterParent);
    }

    //REGISTER BUTTON ACTION
    @FXML
    private void registerButtonAction() {
        if (usernameInput.getText().equals("") || passInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To register you must complete all fields!");
        else {
            if (!DuplicateFunc.isValid(passInput.getText()))
                AlertBox.display("Alert", "Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!DuplicateFunc.isValidMail(mailInput.getText().trim()))
                AlertBox.display("Alert", "Incorrect mail address!");
            else {
                try {
                    AfterLoginUserController.UserAction(u, usernameInput, passInput, mailInput, firstnameInput, lastnameInput, ageInput);
                    am.AddUser(u);

                    AlertBox.display("Congratulations", "You registered successfully");
                    Parent Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Main_App.window.getScene().setRoot(Parent);
                } catch (NumberFormatException | IOException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBox_RegisterAction();
    }
}
