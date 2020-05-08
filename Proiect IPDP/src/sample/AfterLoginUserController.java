package sample;

import Classes.Item;
import Classes.ManagerItems;
import Classes.ManagerUsers;
import Classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginUserController implements Initializable {

    ManagerUsers u = new ManagerUsers();
    User user = new User();

    @FXML TextField usernameInput = new TextField();
    @FXML TextField passwordInput = new TextField();
    @FXML TextField firstnameInput = new TextField();
    @FXML TextField lastnameInput = new TextField();
    @FXML TextField mailInput = new TextField();
    @FXML TextField ageInput = new TextField();


    //Update
    @FXML TextField nameInputUP = new TextField();
    @FXML TextField codeInputUP = new TextField();
    @FXML TextField amountInputUP = new TextField();
    @FXML TextField priceInputUP = new TextField();
    @FXML TextField codeInput_to_UP = new TextField();


    Item item = new Item();
    Item itemToUP = new Item();
    ManagerItems i = new ManagerItems();

    @FXML TextField searchInput = new TextField();
    @FXML TextArea text = new TextArea();
    @FXML TextArea text2 = new TextArea();

    @FXML
    private void clearButtonAction(){
        nameInputUP.clear();
        amountInputUP.clear();
        codeInputUP.clear();
        priceInputUP.clear();
    }

    @FXML
    private void updateItemButtonAction(){
        if(nameInputUP.getText().equals("") || codeInputUP.getText().equals("")|| amountInputUP.getText().equals("") || priceInputUP.getText().equals("")
                || codeInput_to_UP.getText().equals(""))
            AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            try {
                itemToUP.code = Integer.parseInt(codeInput_to_UP.getText().trim());
                item.name = nameInputUP.getText().trim();
                item.code = Integer.parseInt(codeInputUP.getText().trim());
                item.amount = Integer.parseInt(amountInputUP.getText().trim());
                item.price = Integer.parseInt(priceInputUP.getText().trim());
                i.UpdateItem(itemToUP, item);
                text2.setText("Item updated!");
            }catch(NumberFormatException ex){
                AlertBox.display("Alert", "Error: "
                        + codeInput_to_UP.getText().trim().toUpperCase() + " \n or \n"
                        + codeInputUP.getText().trim().toUpperCase() + " \n or \n"
                        + amountInputUP.getText().trim().toUpperCase()
                        + "\n or \n" + priceInputUP.getText().trim().toUpperCase()
                        + " is not a number!");
            }
        }
    }

    @FXML
    private void search(){
        if(searchInput.getText().equals("")) AlertBox.display("Alert", "Error: You must complete all fields!");
        else{
            item.name = searchInput.getText().trim();
            if(i.findItem(item) == false) text.setText("Item not found!");
            else text.setText("Your item is this: \n" + i.displayItem(item));
        }
    }

    @FXML
    private void singOutButton(javafx.event.ActionEvent actionEvent) throws IOException {
        if(ConfirmBox.display("Alert!", " Are you sure you want to sing out?") == true) {
            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene LoginAdminScene = new Scene(LoginAdminParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(LoginAdminScene);
            window.show();
        }
    }

    @FXML
    private void deleteAccountButton(javafx.event.ActionEvent actionEvent) throws IOException {
        if(usernameInput.getText().equals("") || passwordInput.getText().equals("")|| mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals("")) AlertBox.display("Alert", "Error: To delete account you must complete all fields!");
        else{
            try {
                user.username = usernameInput.getText().trim();
                user.password = passwordInput.getText().trim();
                user.setMail_adress(mailInput.getText().trim());
                user.setFirstName(firstnameInput.getText().trim());
                user.setLastName(lastnameInput.getText().trim());
                user.setAge((Integer.parseInt(ageInput.getText().trim())));
                if(ConfirmBox.display("Alert", "Are you shure you want to delet yout account?") == true) {
                    u.DeleteUser(user);
                    AlertBox.display("Alert", "Account deleted!");
                    Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Scene LoginAdminScene = new Scene(LoginAdminParent);

                    //This line gets the Stage information
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    window.setScene(LoginAdminScene);
                    window.show();
                }
            }catch(NumberFormatException ex){
                AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
            }
        }
    } /////////////////FA IN ASA FEL INCAT CONTUL CURENT SA SE STEARGA

    //TableView
    @FXML TableView tableView = new TableView();
    @FXML TableColumn<Item, String> nameColl;
    @FXML TableColumn<Item, Integer> codeColl;
    @FXML TableColumn<Item, Integer> amountColl;
    @FXML TableColumn<Item, Integer> priceColl;

    //TABLEVIEW
    public ObservableList<Item> getItems(){
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(new Item("Cartofi", 152312,1212,316731), new Item("Ceapa", 12365712,124212,3131),
                new Item("Ardei", 12312,122112,311231));
        return items;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(getItems());
    }
}
