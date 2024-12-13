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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Generated
public class purchaseController implements Initializable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane PurchasePage;

    @FXML
    private TableView ListDrinks;
    @FXML
    private TableColumn Drinks;
    @FXML
    private TableColumn DrinksPrice;

    @FXML
    private TableView ListChocolates;
    @FXML
    private TableColumn Chocolates;
    @FXML
    private TableColumn ChocolatesPrice;

    @FXML
    private TableView ListChips;
    @FXML
    private TableColumn Chips;
    @FXML
    private TableColumn ChipsPrice;

    @FXML
    private TableView ListCandies;
    @FXML
    private TableColumn Candies;
    @FXML
    private TableColumn CandiesPrice;

    @FXML
    private TableView ListItems;
    @FXML
    private TableColumn Items;
    @FXML
    private TableColumn ItemsPrice;

    @FXML
    private ChoiceBox<String> ProductName;

    @FXML
    private ChoiceBox<Integer> ProductAmount;

    @FXML
    private Text totalAmount;
    @FXML
    private Text timer;

    public Stage prevStage;

    public Integer checkAdd = 0;

    public App app;
    public Timer time;
    public Integer start = 0;
    public double FRAMETIME = 1.0/60.0;
    public Timeline timeline = new Timeline();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init from app
        start = 1;
        app = new App();
        app.init();

        //drink data
        List<Snack> drinks = app.displaySnacksClass("Drinks");
        ListDrinks.getItems().addAll(drinks);
        PropertyValueFactory<Snack, String> DrinksValue = new PropertyValueFactory<>("name");
        Drinks.setCellValueFactory(DrinksValue);
        PropertyValueFactory<Snack, Double> DrinksPriceValue = new PropertyValueFactory<>("price");
        DrinksPrice.setCellValueFactory(DrinksPriceValue);

        // chocolate data
        List<Snack> chocolates = app.displaySnacksClass("Chocolates");
        ListChocolates.getItems().addAll(chocolates);
        PropertyValueFactory<Snack, String> ChocolatesValue = new PropertyValueFactory<>("name");
        Chocolates.setCellValueFactory(ChocolatesValue);
        PropertyValueFactory<Snack, Double> ChocolatesPriceValue = new PropertyValueFactory<>("price");
        ChocolatesPrice.setCellValueFactory(ChocolatesPriceValue);

        // chips data
        List<Snack> chips = app.displaySnacksClass("Chips");
        ListChips.getItems().addAll(chips);
        PropertyValueFactory<Snack, String> ChipsValue = new PropertyValueFactory<>("name");
        Chips.setCellValueFactory(ChipsValue);
        PropertyValueFactory<Snack, Double> ChipsPriceValue = new PropertyValueFactory<>("price");
        ChipsPrice.setCellValueFactory(ChipsPriceValue);

        // candies data
        List<Snack> candies = app.displaySnacksClass("Candies");
        ListCandies.getItems().addAll(candies);
        PropertyValueFactory<Snack, String> CandiesValue = new PropertyValueFactory<>("name");
        Candies.setCellValueFactory(CandiesValue);
        PropertyValueFactory<Snack, Double> CandiesPriceValue = new PropertyValueFactory<>("price");
        CandiesPrice.setCellValueFactory(CandiesPriceValue);

        // Cart data
        ListItems.getItems().addAll(app.buyList);
        PropertyValueFactory<currentBuy, String> nameValue = new PropertyValueFactory<>("name");
        Items.setCellValueFactory(nameValue);
        PropertyValueFactory<currentBuy, Integer> amountValue = new PropertyValueFactory<>("amount");
        ItemsPrice.setCellValueFactory(amountValue);

        // Timer
        time = new Timer(timer);
        timer = time.getText();

        if (start == 1) {
            timeline.setCycleCount(Timeline.INDEFINITE);
            KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME),
                    (actionEvent) -> {
                        time.update();
                        if (time.getMinute() == 2) {
                            timeline.stop();
                            timeCancel();
                        }
                    });
            timeline.getKeyFrames().add(frame);
            timeline.play();
        }


        // list product
        int length = app.snackList.size();
        String[] items = new String[length];
        int position = 0;
        for (Snack s: app.snackList){
            items[position] = s.getName();
            position ++;
        }
        ProductName.getItems().addAll(items);
        ProductName.setOnAction(this::getItem);

    }

    private void getItem(ActionEvent event){
        time.reset();
        // list amount by name
        String name = ProductName.getValue();
        int count = 0;
        for (Snack s: app.snackList){
            if (s.getName().equals(name)){
                count = s.getAmount();
                break;
            }
        }
        Integer[] amount = new Integer[count];
        for (int i = 0; i < count; i++){
            amount[i] = i+1;
        }
        ProductAmount.getItems().clear();
        ProductAmount.getItems().addAll(amount);
        ProductAmount.setValue(1);
        ProductAmount.setOnAction(this::getAction);
    }

    public void getAction(ActionEvent event){
        time.reset();
    }
    @FXML
    public void timeCancel(){
        start = 0;
        app.cancelPurchase("TimeOut");
        app.Logout();
        try {
            timeline.stop();
            ((Stage) PurchasePage.getScene().getWindow()).close();
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

    @FXML
    public void handleAddButton(ActionEvent event) {
        time.reset();
        String item = ProductName.getValue();
        Integer amount = ProductAmount.getValue();
        if (item != null && amount != null){
            app.addPurchaseItems(item,amount);
            app.getTotal();
            totalAmount.setText(String.format("%.2f",app.totalPrice));
            checkAdd = 1;
            ListItems.getItems().clear();
            ListItems.getItems().addAll(app.buyList);
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please choose item and amount");
            errorAlert.show();
        }
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        time.reset();
        start = 0;
        app.cancelPurchase("User Cancel");
        app.Logout();
        try {
            timeline.stop();
            ((Stage) PurchasePage.getScene().getWindow()).close();
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

    @FXML
    private void handleCardButton(ActionEvent event) {
        time.reset();
        if (checkAdd == 1) {
            start = 0;
            try {
                timeline.stop();
                ((Stage) PurchasePage.getScene().getWindow()).close();
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Card.fxml"));
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
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please Add Items");
            errorAlert.show();
        }
    }

    @FXML
    private void handleCashButton(ActionEvent event) {
        time.reset();
        if (checkAdd == 1) {
            try {
                timeline.stop();
                start = 0;
                ((Stage) PurchasePage.getScene().getWindow()).close();
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Cash.fxml"));
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
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please Add Items");
            errorAlert.show();
        }
    }
}