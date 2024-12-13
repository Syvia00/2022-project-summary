package vending;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
