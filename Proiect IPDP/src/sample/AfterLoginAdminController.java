package sample;

import Classes.Item;
import Classes.Manager2;
import Classes.ManagerItems;
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
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginAdminController implements Initializable {

    //NECCESARY
    private Item item = new Item();
    private Item itemToUP = new Item();
    private ManagerItems i = new ManagerItems();
    private Manager2 u_it = new Manager2();

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
        if (searchInput.getText().equals("")) AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            item.name = searchInput.getText().trim();
            if (i.findItem(item) == false) text.setText("Item not found!");
            else text.setText("Your item is this: \n" + i.displayItem(item));
        }
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
                u_it.UpdateItem(itemToUP, item);
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

    //DELETE
    @FXML
    private void deleteItemButtonAction() {
        if (nameInput.getText().equals("") || codeInput.getText().equals("") || amountInput.getText().equals("") || priceInput.getText().equals(""))
            AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            try {
                item.name = nameInput.getText().trim();
                item.code = Integer.parseInt(codeInput.getText().trim());
                item.amount = Integer.parseInt(amountInput.getText().trim());
                item.price = Integer.parseInt(priceInput.getText().trim());
                i.DeleteItem(item);
                text2.setText("Item deleted!");
                tableView.getItems().clear();
                tableView.setItems(getItems());
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
            AlertBox.display("Alert", "Error: You must complete all fields!");
        else {
            try {
                item.name = nameInput.getText().trim();
                item.code = Integer.parseInt(codeInput.getText().trim());
                item.amount = Integer.parseInt(amountInput.getText().trim());
                item.price = Integer.parseInt(priceInput.getText().trim());
                i.AddItem(item);
                text2.setText("Item added!");
                tableView.getItems().clear();
                tableView.setItems(getItems());
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
    private void UserHandleButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserHandle.fxml"));
        Parent pane = loader.load();
        Main_App.window.getScene().setRoot(pane);
    }

    //SINGOUT
    @FXML
    private void singOutButtonAction(javafx.event.ActionEvent actionEvent) throws IOException {
        if (ConfirmBox.display("Alert!", " Are you sure you want to sing out?") == true) {
            Parent LoginAdminParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Main_App.window.getScene().setRoot(LoginAdminParent);
        }
    }


    private ObservableList<Item> getItems() {

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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColl.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        codeColl.setCellValueFactory(new PropertyValueFactory<Item, Integer>("code"));
        amountColl.setCellValueFactory(new PropertyValueFactory<Item, Integer>("amount"));
        priceColl.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));
        tableView.setItems(getItems());
    }
}
