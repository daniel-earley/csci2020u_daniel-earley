package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientController {

    @FXML private TextField UsernameField;
    @FXML private TextField MessageField;
    private Socket socket = null;

    public void initialize(){

    }

    public void sendButton(ActionEvent actionEvent) {
        String user = UsernameField.getText();
        String message = MessageField.getText();

        try {
            socket = new Socket("localhost", 8080);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String output = user + ": " + message;
            out.writeUTF(output);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitButton(ActionEvent actionEvent) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}
