package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class BulletinBoardServer {
    private static ServerSocket serverSocket = null;

    private int port;


    public BulletinBoardServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

        public void handleRequests() throws IOException{
        System.out.println("Listening to port: " + port);
        // Create a thread to handle each client
        while(true){
            Socket clientSocket = serverSocket.accept();
            BulletinBoardClientHandler handler = new BulletinBoardClientHandler(clientSocket);
             Thread handlerThread = new Thread(handler);
             handlerThread.start();
        }
    }

    public static void main(String[] args){
        int port = 8080;

        try {
            BulletinBoardServer server = new BulletinBoardServer(port);
            server.handleRequests();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
