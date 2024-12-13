package vending;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
@Generated
public class cardController  implements Initializable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane CardPage;

    @FXML
    private TextField holderName;

    @FXML
    private PasswordField cardNumber;

    @FXML
    private CheckBox saveCard;

    public Stage prevStage;

    public App app;

    public String name = null;
    public String number = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new App();
        app.init();

//        if (app.transactionList.isEmpty()){
//            System.out.println("121212");
//        }
        if (app.findHolder() != null) {
            holderName.setText(app.findHolder());
            cardNumber.setText(app.findNumber(app.findHolder()));
            saveCard.setSelected(true);
        }
    }

    @FXML
    private void handlePurchaseButton(ActionEvent event) {
        name = holderName.getText();
        number = cardNumber.getText();
        if (name != "" && number != "") {
            int value = app.checkCard(name,number);
            if ( value == 1) {
                app.completePurchase("card",0.0);
                app.findLogin();
                if (saveCard.isSelected()){
                    for(User u : app.userList){
                        if (u.getName().equals(app.currentUser)){
                            u.addCard(name);
                            app.addUser();
                            break;
                        }
                    }
                }
                else{
                    for(User u : app.userList){
                        if (u.getName().equals(app.currentUser)){
                            u.removeCard();
                            app.addUser();
                            break;
                        }
                    }
                }
                try {
                    ((Stage) CardPage.getScene().getWindow()).close();
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
                errorAlert.setContentText("Invalid Card Holder or Card Number");
                errorAlert.show();
            }
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please input card details");
            errorAlert.show();
        }
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        app.cancelPurchase("User Cancel");
        app.Logout();
        try {
            ((Stage) CardPage.getScene().getWindow()).close();
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
