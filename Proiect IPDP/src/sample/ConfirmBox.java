package sample;

import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        Image image = new Image("/icon/checkbox_form_unchecked_tick-512.png");
        window.getIcons().add(image);

        //grid
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);

        //label
        Label label = new Label();
        label.setText(message);
        GridPane.setConstraints(label, 0, 0);

        //Create two buttons
        Button yesButton = new Button("Yes");
        GridPane.setConstraints(yesButton, 0, 1);

        Button noButton = new Button("No");
        GridPane.setConstraints(noButton, 1, 1);

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}