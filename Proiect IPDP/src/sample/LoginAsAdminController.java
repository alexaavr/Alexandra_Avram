package sample;

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
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginAsAdminController implements Initializable {

    //GENERAL USE
    @FXML private Stage stage = new Stage();

    @FXML private TextField serialInput = new TextField();
    @FXML private TextField idInput = new TextField();
    @FXML private PasswordField passInput = new PasswordField();
    @FXML private Button quitButton = new Button();
    @FXML private CheckBox checkBox_Login = new CheckBox();


    //ADMIN ACTIONS
    //QUIT BUTTON ACTION
    @FXML
    private void quitButtonAction() throws IOException {
        stage = (Stage) quitButton.getScene().getWindow();
        if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) stage.close();
        else stage.getScene().getWindow();
    }


    //CLEAR BUTTON ACTION
    @FXML
    private void clearButtonAction(){
        serialInput.clear();
        idInput.clear();
        passInput.clear();
    }

    //LOGIN BUTTON ACTION
    @FXML
    private void loginButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Document d = new Document( "Login serial", serialInput.getText().trim()).append( "admin ID", idInput.getText().trim()).append("Password", passInput.getText().trim());
        if (Main_App.verifyLogin(d, ConnectionDB.collectionAdmin, "Wrong Adimin ID or Login serial number or Password", "Alert!")) {
            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("AfterLoginUser.fxml"));
            Scene LoginAdminScene = new Scene(LoginAdminParent);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(LoginAdminScene);
            window.show();
        }
    }

    //BACK BUTTON ACTION
    @FXML
    private void backButoonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene LoginAdminScene = new Scene(LoginAdminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(LoginAdminScene);
        window.show();
    }

    // CHACK BOX ACTION
    @FXML
    private void checkBox_LoginAction(){
        if (checkBox_Login.isSelected()){
            passInput.setPromptText(passInput.getText());
            passInput.setText("");

        }else {
            passInput.setText(passInput.getPromptText());
            passInput.setPromptText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
