package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class Controller {
    //    @FXML private Text registerButton;
    @FXML private TextField userNameField;
    @FXML private TextField passwordField;
    @FXML private TextField fullNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField emailField;
    @FXML private DatePicker datePick;

    @FXML protected void handleSubmitButtonAction(ActionEvent event){
        String phoneNumber = phoneNumberFormat(phoneNumberField.getText());
        System.out.printf("User Name: %s\nPassword: %s\nFull Name: %s\nE-mail: %s\nPhone Number: %s\nBirth Date: %s\n",
                userNameField.getText(), passwordField.getText(), fullNameField.getText(), emailField.getText(),
                phoneNumber, datePick.getValue());

    }

    private String phoneNumberFormat(String number){
        // \d{3} will find 3 digits in a row, \d{4} will find 4 digits in a row || $1 the first 3 digits, $2 the second set of digits, $3 the final set of digits
        return String.valueOf(number).replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
    }

}
