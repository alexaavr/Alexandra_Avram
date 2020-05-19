package sample;

import Classes.AdminManager;
import Classes.DuplicateFunc;
import Classes.Item;
import Classes.UserManager;
import DB.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginAdminController implements Initializable {

    //NECCESARY
    private Item item = new Item();
    private Item itemToUP = new Item();
    private AdminManager am = new AdminManager();
    private DuplicateFunc t = new DuplicateFunc();
    private UserManager um = new UserManager();

    //FIRST INPUT
    @FXML
    private TextField nameInput = new TextField();
    @FXML
    private TextField codeInput = new TextField();
    @FXML
    private TextField amountInput = new TextField();
    @FXML
    private TextField priceInput = new TextField();

    //UPDATE
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

    //SEARCH
    @FXML
    private TextField searchInput = new TextField();
    @FXML
    private TextArea text = new TextArea();

    //TEXT AREA
    @FXML
    private TextArea text2 = new TextArea();

    //TableView
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColl;
    @FXML
    private TableColumn<Item, Integer> codeColl;
    @FXML
    private TableColumn<Item, Integer> amountColl;
    @FXML
    private TableColumn<Item, Integer> priceColl;


    //CLEAR
    @FXML
    private void clearButtonAction() {
        nameInput.clear();
        amountInput.clear();
        codeInput.clear();
        priceInput.clear();
    }

    //SEARCH VERIFY STOCK
    @FXML
    private void search() {
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "You must complete all fields!");
        else {
            item.name = searchInput.getText().trim();
            if (!am.findItem(item)) text.setText("Item not found!");
            else text.setText("Your item is: \n" + am.displayItem(item));
        }
    }

    //UPDATE
    @FXML
    private void updateItemButtonAction() {
        if (codeInput_to_UP.getText().equals("")) AlertBox.display("Alert", "You must complete the name field!");
        else {
            try {
                itemToUP.code = Integer.parseInt(codeInput_to_UP.getText().trim());
                if (!um.findItembyCode(itemToUP))
                    AlertBox.display("Alert", "Item dosen't exist!");
                else {
                    if (nameInputUP.getText().equals("") || codeInputUP.getText().equals("") || amountInputUP.getText().equals("") || priceInputUP.getText().equals(""))
                        AlertBox.display("Alert", "You must complete all fields!");
                    else {
                        ItemAction(item, nameInputUP, codeInputUP, amountInputUP, priceInputUP);
                        am.UpdateItem(itemToUP, item);
                        text2.setText("Item updated!");
                        tableView.getItems().clear();
                        tableView.setItems(t.getItems(ConnectionDB.collectionItem));
                    }
                }
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: "
                        + codeInput_to_UP.getText().trim().toUpperCase() + "\n or \n"
                        + codeInputUP.getText().trim().toUpperCase() + " \n or \n"
                        + amountInputUP.getText().trim().toUpperCase()
                        + "\n or \n" + priceInputUP.getText().trim().toUpperCase()
                        + " is not a number!");
            }
        }
    }

    private void ItemAction(Item item, TextField nameInputUP, TextField codeInputUP, TextField amountInputUP, TextField priceInputUP) {
        item.name = nameInputUP.getText().trim();
        item.code = Integer.parseInt(codeInputUP.getText().trim());
        item.amount = Integer.parseInt(amountInputUP.getText().trim());
        item.price = Integer.parseInt(priceInputUP.getText().trim());
    }

    //DELETE
    @FXML
    private void deleteItemButtonAction() {
        if (nameInput.getText().equals("") || codeInput.getText().equals("") || amountInput.getText().equals("") || priceInput.getText().equals(""))
            AlertBox.display("Alert", "You must complete all fields!");
        else {
            try {
                ItemAction(item, nameInput, codeInput, amountInput, priceInput);
                am.DeleteItem(item);
                text2.setText("Item deleted!");
                tableView.getItems().clear();
                tableView.setItems(t.getItems(ConnectionDB.collectionItem));
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: "
                        + codeInput.getText().trim().toUpperCase() + " \n or \n"
                        + amountInput.getText().trim().toUpperCase()
                        + "\n or \n" + priceInput.getText().trim().toUpperCase()
                        + " is not a number!");
            }
        }
    }

    //ADD
    @FXML
    private void addItemButtonAction() {
        if (nameInput.getText().equals("") || codeInput.getText().equals("") || amountInput.getText().equals("") || priceInput.getText().equals(""))
            AlertBox.display("Alert", "You must complete all fields!");
        else {
            try {
                ItemAction(item, nameInput, codeInput, amountInput, priceInput);
                am.AddItem(item);
                text2.setText("Item added!");
                tableView.getItems().clear();
                tableView.setItems(t.getItems(ConnectionDB.collectionItem));
            } catch (NumberFormatException ex) {
                AlertBox.display("Alert", "Error: "
                        + codeInput.getText().trim().toUpperCase() + " \n or \n"
                        + amountInput.getText().trim().toUpperCase()
                        + "\n or \n" + priceInput.getText().trim().toUpperCase()
                        + " is not a number!");
            }
        }
    }

    //USER HANDLE
    @FXML
    private void UserHandleButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserHandle.fxml"));
        Parent pane = loader.load();
        Main_App.window.getScene().setRoot(pane);
    }

    //SINGOUT
    @FXML
    private void singOutButtonAction() throws IOException {
        if (ConfirmBox.display("Alert!", " Are you sure you want to sing out?")) {
            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Main_App.window.getScene().setRoot(LoginAdminParent);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColl.setCellValueFactory(new PropertyValueFactory<>("name"));
        codeColl.setCellValueFactory(new PropertyValueFactory<>("code"));
        amountColl.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceColl.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.setItems(t.getItems(ConnectionDB.collectionItem));
    }
}
