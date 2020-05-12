package sample;

import Classes.Item;
import Classes.ManagerItems;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bson.Document;

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
                tableView.getItems().clear();
                tableView.setItems(getItems());
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
            Parent pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Main_App.window.getScene().setRoot(pane);
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
                    Main_App.window.getScene().setRoot(LoginAdminParent);
                }
            }catch(NumberFormatException ex){
                AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
            }
        }
    } /////////////////FA IN ASA FEL INCAT CONTUL CURENT SA SE STEARGA

    //TableView
    @FXML private TableView tableView;
    @FXML private TableColumn<Item, String> nameColl;
    @FXML private TableColumn<Item, Integer> codeColl;
    @FXML private TableColumn<Item, Integer> amountColl;
    @FXML private TableColumn<Item, Integer> priceColl;

    //TABLEVIEW
    private ObservableList<Item> getItems(){

        ObservableList<Item> items = FXCollections.observableArrayList();
        MongoCursor<Document> cursorItem = ConnectionDB.collectionItem.find().iterator();

        while(cursorItem.hasNext())
        {
            Document doc = cursorItem.next();
            String name = doc.get("Name").toString();
            int code = Integer.parseInt(doc.get("Code").toString());
            int amount = Integer.parseInt(doc.get("Amount").toString());
            int price = Integer.parseInt(doc.get("Price").toString());
            items.add(new Item(name,code,amount,price));
        }

        return items;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            tableView.setItems(getItems());
    }
}
