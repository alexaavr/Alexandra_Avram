package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginAdminController implements Initializable {

    @FXML
    private void UserHandleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("UserHandle.fxml"));
            Scene LoginAdminScene = new Scene(LoginAdminParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(LoginAdminScene);
            window.show();
    }

    @FXML
    private void singOutButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        if(ConfirmBox.display("Alert!", " Are you sure you want to sing out?") == true) {
            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene LoginAdminScene = new Scene(LoginAdminParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(LoginAdminScene);
            window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}