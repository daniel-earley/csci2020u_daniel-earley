package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BBSController {

    private Socket socket = null;
    private static String message;
    @FXML private static TextArea messageArea;

    public void initialize(){

    }

    public void setMessageArea(String text){
        messageArea.setText(text);
    }

    public TextArea getMessageArea(){
        return messageArea;
    }

    public static void updateMessageArea(Socket inputSocket){
        try {
            DataInputStream input = new DataInputStream(inputSocket.getInputStream());
            String message = input.readUTF();
            messageArea.appendText(message);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void updateMessage(String text){
        System.out.println("Before Appending");
        message = text;
        messageArea.appendText(text);
        System.out.println("After appending");
    }


}
