package vending;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;

@Generated
public class defaultController implements Initializable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane DefaultPage;

    @FXML
    private ListView ListDrinks;

    @FXML
    private ListView ListChocolates;

    @FXML
    private ListView ListChips;

    @FXML
    private ListView ListCandies;

    @FXML
    private ListView BoughtProducts;

    public Stage prevStage;

    public App app;

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

        // bought product
        List<String> bought = app.displayBoughtItems();
        if (!bought.isEmpty()) {
            for (String boughtItem : bought) {
                if (boughtItem == null){
                    BoughtProducts.getItems().add("");
                }
                else {
                    BoughtProducts.getItems().add(boughtItem);
                }
            }
        }
    }

    @FXML
    private void handlePurchaseButton(ActionEvent event) {
        try {
            ((Stage) DefaultPage.getScene().getWindow()).close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Purchase.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, 600);
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
