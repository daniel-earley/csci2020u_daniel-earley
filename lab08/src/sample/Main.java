package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 08");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static Stage getStage(){
        return Main.primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
