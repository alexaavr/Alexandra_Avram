package sample;

import Classes.ManagerUsers;
import Classes.User;
import DB.ConnectionDB;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHandleController implements Initializable {

    private User user = new User();
    private User user_UP = new User();
    private ManagerUsers u = new ManagerUsers();

    @FXML private TextField usernameInput = new TextField();
    @FXML private TextField passwordInput = new TextField();
    @FXML private TextField firstnameInput = new TextField();
    @FXML private TextField lastnameInput = new TextField();
    @FXML private TextField mailInput = new TextField();
    @FXML private TextField ageInput = new TextField();

    //search
    @FXML private TextField searchInput = new TextField();
    @FXML private TextArea text = new TextArea();

    //TABLE VIEW
    @FXML private TableView tableView = new TableView();

    @FXML
    private void clearButtonAction(){
        usernameInput.clear();
        passwordInput.clear();
        lastnameInput.clear();
        firstnameInput.clear();
        mailInput.clear();
        ageInput.clear();
    }

    @FXML
    private void search(){
        if(searchInput.getText().equals("")) AlertBox.display("Alert", "Error: You must complete all fields!");
        else{
            user.username = searchInput.getText().trim();
            if(u.findUser(user) == true) text.setText("User found! \n" + u.displayUser(user));
            else text.setText("User not found! \n" );
        }
    }


    ////////    NU MERGE FUCKING FIND

    @FXML
    private void updateUserButtonAction(){
        if(usernameInput.getText().equals("") || passwordInput.getText().equals("")|| mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals("")
        || searchInput.getText().equals("")) AlertBox.display("Alert", "Error: To register you must complete all fields!");
        else{
            if (Main_App.isValid(passwordInput.getText()) == false) AlertBox.display("Alert", "Error: Password must contain: \n " +
                    "-at least 8 characters;\n" +
                    "-at least an Uppercase;\n " +
                    "-at least an Lowercase;\n " +
                    "-at least a special character!");
            else if (Main_App.isValidMail(mailInput.getText().trim()) == false) AlertBox.display("Alert", "Error: Incorrect mail address!");
            else{
                try {
                    user_UP.username = searchInput.getText().trim();
                    user.username = usernameInput.getText().trim();
                    user.password = passwordInput.getText().trim();
                    user.setMail_adress(mailInput.getText().trim());
                    user.setFirstName(firstnameInput.getText().trim());
                    user.setLastName(lastnameInput.getText().trim());
                    user.setAge((Integer.parseInt(ageInput.getText().trim())));
                    u.UpdateUser(user_UP, user);
                    tableView.getItems().clear();
                    tableView.setItems(getItems());
                    AlertBox.display("Alert", "User updated!");
                }catch(NumberFormatException ex){
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    @FXML
    private void addUserButtonAction(){
        if(usernameInput.getText().equals("") || passwordInput.getText().equals("")|| mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals("")) AlertBox.display("Alert", "Error: To register you must complete all fields!");
        else{
            if (Main_App.isValid(passwordInput.getText()) == false) AlertBox.display("Alert", "Error: Password must contain: \n " +
                    "-at least 8 characters;\n" +
                    "-at least an Uppercase;\n " +
                    "-at least an Lowercase;\n " +
                    "-at least a special character!");
            else if (Main_App.isValidMail(mailInput.getText().trim()) == false) AlertBox.display("Alert", "Error: Incorrect mail address!");
            else{
                try {
                    user.username = usernameInput.getText().trim();
                    user.password = passwordInput.getText().trim();
                    user.setMail_adress(mailInput.getText().trim());
                    user.setFirstName(firstnameInput.getText().trim());
                    user.setLastName(lastnameInput.getText().trim());
                    user.setAge((Integer.parseInt(ageInput.getText().trim())));
                    u.AddUser(user);
                    AlertBox.display("Alert", "User addead!");
                    tableView.getItems().clear();
                    tableView.setItems(getItems());
                }catch(NumberFormatException ex){
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
            }
        }
    }

    @FXML
    private void deleteUserButtonAction(){
        if(usernameInput.getText().equals("") || passwordInput.getText().equals("")|| mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals("")) AlertBox.display("Alert", "Error: To register you must complete all fields!");
        else{
                try {
                    user.username = usernameInput.getText().trim();
                    user.password = passwordInput.getText().trim();
                    user.setMail_adress(mailInput.getText().trim());
                    user.setFirstName(firstnameInput.getText().trim());
                    user.setLastName(lastnameInput.getText().trim());
                    user.setAge((Integer.parseInt(ageInput.getText().trim())));
                    u.DeleteUser(user);
                    AlertBox.display("Alert", "User deleted!");
                    tableView.getItems().clear();
                    tableView.setItems(getItems());
                }catch(NumberFormatException ex){
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
        }
    }


    @FXML
    private void ItemHandlingButton(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfterLoginAdmin.fxml"));
        Parent pane = loader.load();
        Main_App.window.getScene().setRoot(pane);
    }

    @FXML
    private void singOutButton(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent pane = loader.load();
        Main_App.window.getScene().setRoot(pane);
    }

    private ObservableList<User> getItems(){

        ObservableList<User> users = FXCollections.observableArrayList();
        MongoCursor<Document> cursorLogin = ConnectionDB.collectionLogin.find().iterator();

        while(cursorLogin.hasNext())
        {
            Document doc = cursorLogin.next();
            String username = doc.get("Username").toString();
            String password = doc.get("Password").toString();
            String LastName = doc.get("Last Name").toString();
            String FirstName =  doc.get("First Name").toString();
            String Mail_adress = doc.get("Mail adress").toString();
            int Age = Integer.parseInt(doc.get("Age").toString());
            users.add(new User(FirstName,LastName, Age, username, password, Mail_adress));
        }

        return users;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(getItems());
    }
}
