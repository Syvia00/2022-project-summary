package vending;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
@Generated
public class loginController implements Initializable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public App app;

    public String user;
    public String pwd;
    @FXML
    private Pane LoginPage;

    @FXML
    private ListView ListDrinks;

    @FXML
    private ListView ListChocolates;

    @FXML
    private ListView ListChips;

    @FXML
    private ListView ListCandies;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    public Stage prevStage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        app = new App();
        app.init();

        // drink data
        List<String> drinks = app.displaySnacksByCategory("Drinks");
        for (String drink : drinks) {
            ListDrinks.getItems().add(drink);
        }

        // chocolate data
        List<String> chocolates = app.displaySnacksByCategory("Chocolates");
        for (String chocolate : chocolates) {
            ListChocolates.getItems().add(chocolate);
        }

        // chips data
        List<String> chips = app.displaySnacksByCategory("Chips");
        for (String chip : chips) {
            ListChips.getItems().add(chip);
        }

        // candies data
        List<String> candies = app.displaySnacksByCategory("Candies");
        for (String candy : candies) {
            ListCandies.getItems().add(candy);
        }
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        user = Username.getText();
        pwd = Password.getText();
        if (user != "" && pwd != "") {
            // empty user list
            if (app.checkLogin(user, pwd) == 0) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("User did not exist");
                errorAlert.show();
            }
            // username correct, password incorrect
            else if (app.checkLogin(user, pwd) == -1) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Invalid Password");
                errorAlert.show();
            }
            // log in successfully, go to the vending machine page
            else {
                String type = "";
                for (User u:app.userList){
                    if (u.getName().equals(user)){
                        type = u.getType();
                    }
                }
                if (type.equals("customer")) {
                    try {
                        app.startTransaction(user);
                        ((Stage) LoginPage.getScene().getWindow()).close();
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Default.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), WIDTH, 400);
                        stage.setTitle("Vending Machine");
                        stage.setScene(scene);
                        Canvas canvas = new Canvas(WIDTH, 400);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (type.equals("seller")){
                    try {
                        ((Stage) LoginPage.getScene().getWindow()).close();
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Seller.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                        stage.setTitle("Vending Machine");
                        stage.setScene(scene);
                        Canvas canvas = new Canvas(WIDTH, HEIGHT);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (type.equals("cashier")){
                    try {
                        ((Stage) LoginPage.getScene().getWindow()).close();
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Cashier.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                        stage.setTitle("Vending Machine");
                        stage.setScene(scene);
                        Canvas canvas = new Canvas(WIDTH, HEIGHT);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        ((Stage) LoginPage.getScene().getWindow()).close();
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Owner.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
                        stage.setTitle("Vending Machine");
                        stage.setScene(scene);
                        Canvas canvas = new Canvas(WIDTH, HEIGHT);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(user != ""){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Empty Password");
            errorAlert.show();
        }
        else if (pwd != ""){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Empty Username");
            errorAlert.show();
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Empty Input");
            errorAlert.show();
        }
    }

    // create a alert
    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    private void handleCreateButton(ActionEvent event) {
        // user created successfully, go to the vending machine page
        try {
            ((Stage) LoginPage.getScene().getWindow()).close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Create.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, 400);
            stage.setTitle("Vending Machine");
            stage.setScene(scene);
            Canvas canvas = new Canvas(WIDTH, HEIGHT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAnonyButton(ActionEvent event) {
        // user created successfully, go to the vending machine page
        try {
            app.startTransaction("Anonymous");
            ((Stage) LoginPage.getScene().getWindow()).close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Default.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, 400);
            stage.setTitle("Vending Machine");
            stage.setScene(scene);
            Canvas canvas = new Canvas(WIDTH, HEIGHT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}