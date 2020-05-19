package sample;

import Classes.AdminManager;
import Classes.DuplicateFunc;
import Classes.User;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHandleController implements Initializable {

    //NECESSARY
    private User user = new User();
    private User user_UP = new User();
    private AdminManager am = new AdminManager();
    private DuplicateFunc t = new DuplicateFunc();

    @FXML
    private TextField usernameInput = new TextField();
    @FXML
    private TextField passwordInput = new TextField();
    @FXML
    private TextField firstnameInput = new TextField();
    @FXML
    private TextField lastnameInput = new TextField();
    @FXML
    private TextField mailInput = new TextField();
    @FXML
    private TextField ageInput = new TextField();

    @FXML
    private TextField usernameInput1 = new TextField();
    @FXML
    private TextField passwordInput1 = new TextField();
    @FXML
    private TextField firstnameInput1 = new TextField();
    @FXML
    private TextField lastnameInput1 = new TextField();
    @FXML
    private TextField mailInput1 = new TextField();
    @FXML
    private TextField ageInput1 = new TextField();

    //search
    @FXML
    private TextField searchInput = new TextField();
    @FXML
    private TextArea text = new TextArea();

    //TABLE VIEW
    @FXML
    private TableView<User> tableView;

    //CLEAR
    @FXML
    private void clearButtonAction() {
        usernameInput.clear();
        passwordInput.clear();
        lastnameInput.clear();
        firstnameInput.clear();
        mailInput.clear();
        ageInput.clear();
    }

    //SEARCH
    @FXML
    private void search() {
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "You must complete all fields!");
        else {
            user.username = searchInput.getText().trim();
            if (am.findUser(user)) text.setText("User found! \n" + am.displayUser(user));
            else text.setText("User not found! \n");
        }
    }

    //UPDATE
    @FXML
    private void updateUserButtonAction() {
        if (usernameInput1.getText().equals("") || passwordInput1.getText().equals("") || mailInput1.getText().equals("") || firstnameInput1.getText().equals("")
                || lastnameInput1.getText().equals("") || ageInput1.getText().equals("")
                || searchInput.getText().equals(""))
            AlertBox.display("Alert", "To update user you must complete all fields!");
        else {
            if (!DuplicateFunc.isValid(passwordInput1.getText()))
                AlertBox.display("Alert", "Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!DuplicateFunc.isValidMail(mailInput1.getText().trim()))
                AlertBox.display("Alert", "Incorrect mail address!");
            else {
                try {
                    user_UP.username = searchInput.getText().trim();
                    AfterLoginUserController.UserAction(user, usernameInput1, passwordInput1, mailInput1, firstnameInput1, lastnameInput1, ageInput1);
                    am.UpdateUser(user_UP, user);
                    tableView.getItems().clear();
                    tableView.setItems(t.getUsers(ConnectionDB.collectionLogin));
                    AlertBox.display("Alert", "User updated!");
                } catch (NumberFormatException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput1.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    //ADD
    @FXML
    private void addUserButtonAction() {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To register you must complete all fields!");
        else {
            if (!DuplicateFunc.isValid(passwordInput.getText()))
                AlertBox.display("Alert", "Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!DuplicateFunc.isValidMail(mailInput.getText().trim()))
                AlertBox.display("Alert", "Incorrect mail address!");
            else {
                try {
                    AfterLoginUserController.UserAction(user, usernameInput, passwordInput, mailInput, firstnameInput, lastnameInput, ageInput);
                    am.AddUser(user);
                    tableView.getItems().clear();
                    tableView.setItems(t.getUsers(ConnectionDB.collectionLogin));
                    AlertBox.display("Alert", "User addead!");
                } catch (NumberFormatException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    //DELETE
    @FXML
    private void deleteUserButtonAction() {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "To register you must complete all fields!");
        else {
            try {
                AfterLoginUserController.UserAction(user, usernameInput, passwordInput, mailInput, firstnameInput, lastnameInput, ageInput);
                am.DeleteUser(user);
                tableView.getItems().clear();
                tableView.setItems(t.getUsers(ConnectionDB.collectionLogin));
                AlertBox.display("Alert", "User deleted!");
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
            }
        }
    }

    //CHANGE TO ITEM SCENE
    @FXML
    private void ItemHandlingButton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfterLoginAdmin.fxml"));
        Parent pane = loader.load();
        Main_App.window.getScene().setRoot(pane);
    }

    //SINGOUT
    @FXML
    private void singOutButton() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent pane = loader.load();
        Main_App.window.getScene().setRoot(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(t.getUsers(ConnectionDB.collectionLogin));
    }
}
