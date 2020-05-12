package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);
        Image image = new Image("/icon/checkbox_form_unchecked_tick-512.png");
        window.getIcons().add(image);

        //grid
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32), CornerRadii.EMPTY, Insets.EMPTY)));

        //label
        Label label = new Label();
        label.setText(message);
        label.setTextFill(Color.rgb(192, 192, 192));
        GridPane.setConstraints(label, 0, 0);

        //Create two buttons
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);

        Button yesButton = new Button("Yes");
        yesButton.setEffect(reflection);
        yesButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        yesButton.setTextFill(Color.WHITE);
        GridPane.setConstraints(yesButton, 0, 1);

        Button noButton = new Button("No");
        noButton.setEffect(reflection);
        noButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        noButton.setTextFill(Color.WHITE);
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