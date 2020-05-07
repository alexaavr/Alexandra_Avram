package sample;

import Classes.ManagerUsers;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHandleController implements Initializable {

    User user = new User();
    User user_UP = new User();
    ManagerUsers u = new ManagerUsers();

    @FXML TextField usernameInput = new TextField();
    @FXML TextField passwordInput = new TextField();
    @FXML TextField firstnameInput = new TextField();
    @FXML TextField lastnameInput = new TextField();
    @FXML TextField mailInput = new TextField();
    @FXML TextField ageInput = new TextField();

    //search
    @FXML TextField searchInput = new TextField();

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
                }catch(NumberFormatException ex){
                    AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
                }
        }
    }


    @FXML
    private void ItemHandlingButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("AfterLoginAdmin.fxml"));
        Scene LoginAdminScene = new Scene(LoginAdminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(LoginAdminScene);
        window.show();
    }

    @FXML
    private void singOutButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene LoginAdminScene = new Scene(LoginAdminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(LoginAdminScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

////   ADAUGA LABELS LA INTERFETELE DE DUPA LOGIN CA UITI CE UNDE E SCRIS
//// ABSTRACTION FACTORY PATTERN
///TABLEVIEW UL VIETII