package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BulletinBoardClientHandler implements Runnable{
    private Socket socket = null;
    public String currentMessage;

    public BulletinBoardClientHandler(Socket socket) throws IOException{
        this.socket = socket;
    }

    public void run(){
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            String message = input.readUTF();
            setCurrentMessage(message);
            if (message.length() > 0){
                System.out.println("Message = " + message);

                // Call method in controller
                System.out.println("Sending Through Socket");
                BBSController.updateMessageArea(socket);
                System.out.println("Sending String");
                BBSController.updateMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }
}
