package sample;

import Classes.AdminManager;
import Classes.ManagerDuplicate;
import Classes.User;
import DB.ConnectionDB;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHandleController implements Initializable {

    //NECESSARY
    private User user = new User();
    private User user_UP = new User();
    private AdminManager am = new AdminManager();
    private ManagerDuplicate u_it = new ManagerDuplicate();

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
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            user.username = searchInput.getText().trim();
            if (am.findUser(user)) text.setText("User found! \n" + am.displayUser(user));
            else text.setText("User not found! \n");
        }
    }

    //UPDATE
    @FXML
    private void updateUserButtonAction() {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals("")
                || searchInput.getText().equals(""))
            AlertBox.display("Alert", "Error: To register you must complete all fields!");
        else {
            if (!Main_App.isValid(passwordInput.getText()))
                AlertBox.display("Alert", "Error: Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!Main_App.isValidMail(mailInput.getText().trim()))
                AlertBox.display("Alert", "Error: Incorrect mail address!");
            else {
                try {
                    user_UP.username = searchInput.getText().trim();
                    user.username = usernameInput.getText().trim();
                    user.password = passwordInput.getText().trim();
                    user.setMail_adress(mailInput.getText().trim());
                    user.setFirstName(firstnameInput.getText().trim());
                    user.setLastName(lastnameInput.getText().trim());
                    user.setAge((Integer.parseInt(ageInput.getText().trim())));
                    am.UpdateUser(user_UP, user);
                    tableView.getItems().clear();
                    tableView.setItems(getItems());
                    AlertBox.display("Alert", "User updated!");
                } catch (NumberFormatException ex) {
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    //ADD
    @FXML
    private void addUserButtonAction() {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "Error: To register you must complete all fields!");
        else {
            if (!Main_App.isValid(passwordInput.getText()))
                AlertBox.display("Alert", "Error: Password must contain: \n " +
                        "-at least 8 characters;\n" +
                        "-at least an Uppercase;\n " +
                        "-at least an Lowercase;\n " +
                        "-at least a special character!");
            else if (!Main_App.isValidMail(mailInput.getText().trim()))
                AlertBox.display("Alert", "Error: Incorrect mail address!");
            else {
                try {
                    user.username = usernameInput.getText().trim();
                    user.password = passwordInput.getText().trim();
                    user.setMail_adress(mailInput.getText().trim());
                    user.setFirstName(firstnameInput.getText().trim());
                    user.setLastName(lastnameInput.getText().trim());
                    user.setAge((Integer.parseInt(ageInput.getText().trim())));
                    am.AddUser(user);
                    tableView.getItems().clear();
                    tableView.setItems(getItems());
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
            AlertBox.display("Alert", "Error: To register you must complete all fields!");
        else {
            try {
                user.username = usernameInput.getText().trim();
                user.password = passwordInput.getText().trim();
                user.setMail_adress(mailInput.getText().trim());
                user.setFirstName(firstnameInput.getText().trim());
                user.setLastName(lastnameInput.getText().trim());
                user.setAge((Integer.parseInt(ageInput.getText().trim())));
                u_it.DeleteUser(user);
                tableView.getItems().clear();
                tableView.setItems(getItems());
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

    //TABLEVIEW
    private ObservableList<User> getItems() {

        ObservableList<User> users = FXCollections.observableArrayList();
        MongoCursor<Document> cursorLogin = ConnectionDB.collectionLogin.find().iterator();

        while (cursorLogin.hasNext()) {
            Document doc = cursorLogin.next();
            String username = doc.get("Username").toString();
            String password = doc.get("Password").toString();
            String LastName = doc.get("Last Name").toString();
            String FirstName = doc.get("First Name").toString();
            String Mail_adress = doc.get("Mail adress").toString();
            int Age = Integer.parseInt(doc.get("Age").toString());
            users.add(new User(FirstName, LastName, Age, username, password, Mail_adress));
        }

        return users;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(getItems());
    }
}
