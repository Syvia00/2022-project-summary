package vending;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
@Generated
public class cashController implements Initializable  {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane CashPage;

    @FXML
    private ChoiceBox<Integer> cash100;

    @FXML
    private ChoiceBox<Integer> cash50;

    @FXML
    private ChoiceBox<Integer> cash20;

    @FXML
    private ChoiceBox<Integer> cash10;

    @FXML
    private ChoiceBox<Integer> cash5;

    @FXML
    private ChoiceBox<Integer> cash2;

    @FXML
    private ChoiceBox<Integer> cash1;

    @FXML
    private ChoiceBox<Integer> cash50c;

    @FXML
    private ChoiceBox<Integer> cash20c;

    @FXML
    private ChoiceBox<Integer> cash10c;

    @FXML
    private ChoiceBox<Integer> cash5c;

    public Stage prevStage;

    public HashMap<Double, Integer> cashIn = new HashMap<Double, Integer>();

    public App app;

    public Double change = -2.0;

    public Integer[] amount = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init from app
        app = new App();
        app.init();
        cash100.getItems().addAll(amount);
        cash50.getItems().addAll(amount);
        cash20.getItems().addAll(amount);
        cash10.getItems().addAll(amount);
        cash5.getItems().addAll(amount);
        cash2.getItems().addAll(amount);
        cash1.getItems().addAll(amount);
        cash50c.getItems().addAll(amount);
        cash20c.getItems().addAll(amount);
        cash10c.getItems().addAll(amount);
        cash5c.getItems().addAll(amount);
        cash100.setOnAction(this::get100);
        cash50.setOnAction(this::get50);
        cash20.setOnAction(this::get20);
        cash10.setOnAction(this::get10);
        cash5.setOnAction(this::get5);
        cash2.setOnAction(this::get2);
        cash1.setOnAction(this::get1);
        cash50c.setOnAction(this::get50c);
        cash20c.setOnAction(this::get20c);
        cash10c.setOnAction(this::get10c);
        cash5c.setOnAction(this::get5c);
    }

    private void get100(ActionEvent event){
        Integer num100 = cash100.getValue();
        if (num100 != null){
            cashIn.put(100.0,num100);
        }
    }

    private void get50(ActionEvent event){
        Integer num50 = cash50.getValue();
        if (num50 != null){
            cashIn.put(50.0,num50);
        }
    }

    private void get20(ActionEvent event){
        Integer num20 = cash20.getValue();
        if (num20 != null){
            cashIn.put(20.0,num20);
        }
    }

    private void get10(ActionEvent event){
        Integer num10 = cash10.getValue();
        if (num10 != null){
            cashIn.put(10.0,num10);
        }
    }

    private void get5(ActionEvent event){
        Integer num5 = cash5.getValue();
        if (num5 != null){
            cashIn.put(5.0,num5);
        }
    }

    private void get2(ActionEvent event){
        Integer num2 = cash2.getValue();
        if (num2 != null){
            cashIn.put(2.0,num2);
        }
    }

    private void get1(ActionEvent event){
        Integer num1 = cash1.getValue();
        if (num1 != null){
            cashIn.put(1.0,num1);
        }
    }

    private void get50c(ActionEvent event){
        Integer num50c = cash50c.getValue();
        if (num50c != null){
            cashIn.put(0.5,num50c);
        }
    }

    private void get20c(ActionEvent event){
        Integer num20c = cash20c.getValue();
        if (num20c != null){
            cashIn.put(0.2,num20c);
        }
    }

    private void get10c(ActionEvent event){
        Integer num10c = cash10c.getValue();
        if (num10c != null){
            cashIn.put(0.1,num10c);
        }
    }

    private void get5c(ActionEvent event){
        Integer num5c = cash5c.getValue();
        if (num5c != null){
            cashIn.put(0.05,num5c);
        }
    }

    @FXML
    private void handlePurchaseButton(ActionEvent event) {
        if (cashIn.size() > 0){
            app.getTotal();
            change = app.cashPayment(cashIn);
        }
        if (change > 0 || change == 0) {
            if (app.countChange(change) == 1) {
                app.completePurchase("cash",change);
                try {
                    ((Stage) CashPage.getScene().getWindow()).close();
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Summary.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), WIDTH, 450);
                    stage.setTitle("Vending Machine");
                    stage.setScene(scene);
                    Canvas canvas = new Canvas(WIDTH, 450);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Can not provide valid Change, Please input again or cancel this transaction.");
                errorAlert.show();
            }
        }
        else if (change == -2.0){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please choose your amount of cash");
            errorAlert.show();
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Cash amount is not enough");
            errorAlert.show();
        }
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        app.cancelPurchase("User Cancel");
        app.Logout();
        try {
            ((Stage) CashPage.getScene().getWindow()).close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            stage.setTitle("Vending Machine");
            stage.setScene(scene);
            Canvas canvas = new Canvas(WIDTH, HEIGHT);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
