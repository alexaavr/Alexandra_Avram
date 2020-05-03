package sample;

import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);
        Image image = new Image("/icon/alert-icon-picture-15464016804kg8n-1024x839.jpg");
        window.getIcons().add(image);

        Label label = new Label();
        label.setText(message);
        label.setTextFill(Color.rgb(192,192,192));

        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);

        Button closeButton = new Button("Close this window");
        closeButton.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        closeButton.setEffect(reflection);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(32,32,32), CornerRadii.EMPTY, Insets.EMPTY)));

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return false;
    }

}