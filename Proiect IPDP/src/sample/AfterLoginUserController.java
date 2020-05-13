package sample;

import Classes.DuplicateFunc;
import Classes.Item;
import Classes.User;
import Classes.UserManager;
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

public class AfterLoginUserController implements Initializable {

    //NECESSARY
    private User user = new User();
    private Item item = new Item();
    private Item itemToUP = new Item();
    private UserManager um = new UserManager();
    private DuplicateFunc t = new DuplicateFunc();

    //TEXT
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


    //Update
    @FXML
    private TextField nameInputUP = new TextField();
    @FXML
    private TextField codeInputUP = new TextField();
    @FXML
    private TextField amountInputUP = new TextField();
    @FXML
    private TextField priceInputUP = new TextField();
    @FXML
    private TextField codeInput_to_UP = new TextField();


    @FXML
    private TextField searchInput = new TextField();
    @FXML
    private TextArea text = new TextArea();
    @FXML
    private TextArea text2 = new TextArea();

    //TableView
    @FXML
    private TableView<Item> tableView;

    //CLEAR
    @FXML
    private void clearButtonAction() {
        nameInputUP.clear();
        amountInputUP.clear();
        codeInputUP.clear();
        priceInputUP.clear();
    }

    //UPDATE
    @FXML
    private void updateItemButtonAction() {
        if (nameInputUP.getText().equals("") || codeInputUP.getText().equals("") || amountInputUP.getText().equals("") || priceInputUP.getText().equals("")
                || codeInput_to_UP.getText().equals(""))
            AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            try {
                itemToUP.code = Integer.parseInt(codeInput_to_UP.getText().trim());
                item.name = nameInputUP.getText().trim();
                item.code = Integer.parseInt(codeInputUP.getText().trim());
                item.amount = Integer.parseInt(amountInputUP.getText().trim());
                item.price = Integer.parseInt(priceInputUP.getText().trim());
                um.UpdateItem(itemToUP, item);
                text2.setText("Item updated!");
                tableView.getItems().clear();
                tableView.setItems(t.getItems(ConnectionDB.collectionItem));
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: "
                        + codeInput_to_UP.getText().trim().toUpperCase() + " \n or \n"
                        + codeInputUP.getText().trim().toUpperCase() + " \n or \n"
                        + amountInputUP.getText().trim().toUpperCase()
                        + "\n or \n" + priceInputUP.getText().trim().toUpperCase()
                        + " is not a number!");
            }
        }
    }

    //SEARCH
    @FXML
    private void search() {
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            item.name = searchInput.getText().trim();
            if (!um.findItem(item)) text.setText("Item not found!");
            else text.setText("Your item is this: \n" + um.displayItem(item));
        }
    }

    //SINGOUT
    @FXML
    private void singOutButton() throws IOException {
        if (ConfirmBox.display("Alert!", " Are you sure you want to sing out?")) {
            Parent pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Main_App.window.getScene().setRoot(pane);
        }
    }

    //DELETE
    @FXML
    private void deleteAccountButton() throws IOException {
        if (usernameInput.getText().equals("") || passwordInput.getText().equals("") || mailInput.getText().equals("") || firstnameInput.getText().equals("")
                || lastnameInput.getText().equals("") || ageInput.getText().equals(""))
            AlertBox.display("Alert", "Error: To delete account you must complete all fields!");
        else {
            try {
                user.username = usernameInput.getText().trim();
                user.password = passwordInput.getText().trim();
                user.setMail_adress(mailInput.getText().trim());
                user.setFirstName(firstnameInput.getText().trim());
                user.setLastName(lastnameInput.getText().trim());
                user.setAge((Integer.parseInt(ageInput.getText().trim())));
                if (ConfirmBox.display("Alert", "Are you shure you want to delet yout account?")) {
                    um.DeleteUser(user);
                    AlertBox.display("Alert", "Account deleted!");
                    Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Main_App.window.getScene().setRoot(LoginAdminParent);
                }
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: " + ageInput.getText().trim().toUpperCase() + " is not a number!");
            }
        }
    }

    //TABLEVIEW
    /*private ObservableList<Item> getItems() {

        ObservableList<Item> items = FXCollections.observableArrayList();
        MongoCursor<Document> cursorItem = ConnectionDB.collectionItem.find().iterator();

        while (cursorItem.hasNext()) {
            Document doc = cursorItem.next();
            String name = doc.get("Name").toString();
            int code = Integer.parseInt(doc.get("Code").toString());
            int amount = Integer.parseInt(doc.get("Amount").toString());
            int price = Integer.parseInt(doc.get("Price").toString());
            items.add(new Item(name, code, amount, price));
        }

        return items;
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setItems(t.getItems(ConnectionDB.collectionItem));
    }
}
