package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main_App extends Application {

    public static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("InventoryApp");
        Image image = new Image("/icon/images.png");
        window.getIcons().add(image);

        window.setOnCloseRequest(e -> {
            e.consume();
            if (ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?")) window.close();
            else window.getScene().getWindow();
        });

        //scene
        Scene scene = new Scene(root, 1000, 600);
        window.setScene(scene);
        window.show();
    }

}

