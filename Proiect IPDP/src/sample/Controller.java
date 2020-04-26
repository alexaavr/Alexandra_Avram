package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

public class Controller {
    CheckBox checkBox = new CheckBox();
    PasswordField passInput = new PasswordField();
    public void something(){
        if (checkBox.isSelected()){
            passInput.setPromptText(passInput.getText());
            passInput.setText("");

        }else {
            passInput.setText(passInput.getPromptText());
            passInput.setPromptText("");
        }
    }
}
