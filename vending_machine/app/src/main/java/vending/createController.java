package vending;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;

@Generated
public class createController implements Initializable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane CreatePage;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public Stage prevStage;

    public App app;

    public String user;

    public String pwd;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new App();
        app.init();
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        user = username.getText();
        pwd = password.getText();
        if (user != "" && pwd != "") {
            int check = app.checkCreate(user, pwd);
            if (check == 1) {
                try {
                    app.startTransaction(user);
                    ((Stage) CreatePage.getScene().getWindow()).close();
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Default.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                    stage.setTitle("Vending Machine");
                    stage.setScene(scene);
                    Canvas canvas = new Canvas(WIDTH, HEIGHT);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Username already exist");
                errorAlert.show();
            }
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Invalid Input");
            errorAlert.show();
        }
    }

}