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
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
@Generated
public class sellerController implements Initializable  {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane SellerPage;

    public Stage prevStage;

    @FXML
    private TableView CurrentItems;
    @FXML
    private TableColumn ItemName;
    @FXML
    private TableColumn Category;
    @FXML
    private TableColumn ItemCode;
    @FXML
    private TableColumn ItemAmount;
    @FXML
    private TableColumn ItemPrice;

    @FXML
    private TableView Summary;
    @FXML
    private TableColumn SummaryCode;
    @FXML
    private TableColumn SummaryName;
    @FXML
    private TableColumn SoldAmount;

    @FXML
    private ChoiceBox<String> changeType;
    @FXML
    private ChoiceBox<Integer> ChoseCode;
    @FXML
    private TextField ChangeValue;

    public App app;
    public String type;
    public Integer code;
    public String value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new App();
        app.init();

        // Current Item
        CurrentItems.getItems().addAll(app.snackList);
        PropertyValueFactory<Snack, String> ItemNameValue = new PropertyValueFactory<>("name");
        ItemName.setCellValueFactory(ItemNameValue);
        PropertyValueFactory<Snack, Double> CategoryValue = new PropertyValueFactory<>("category");
        Category.setCellValueFactory(ItemNameValue);
        PropertyValueFactory<Snack, String> ItemCodeValue = new PropertyValueFactory<>("code");
        ItemCode.setCellValueFactory(ItemCodeValue);
        PropertyValueFactory<Snack, String> ItemAmountValue = new PropertyValueFactory<>("amount");
        ItemAmount.setCellValueFactory(ItemAmountValue);
        PropertyValueFactory<Snack, String> ItemPriceValue = new PropertyValueFactory<>("price");
        ItemPrice.setCellValueFactory(ItemPriceValue);

        //Summary
        Summary.getItems().addAll(app.snackList);
        PropertyValueFactory<Snack, String> SummaryCodeValue = new PropertyValueFactory<>("code");
        SummaryCode.setCellValueFactory(SummaryCodeValue);
        PropertyValueFactory<Snack, String> SummaryNameValue = new PropertyValueFactory<>("name");
        SummaryName.setCellValueFactory(SummaryNameValue);
        PropertyValueFactory<Snack, String> SoldAmountValue = new PropertyValueFactory<>("sold");
        SoldAmount.setCellValueFactory(SoldAmountValue);

        int length = app.snackList.size();
        Integer[] codes = new Integer[length];
        int position = 0;
        for (Snack s: app.snackList){
            codes[position] = s.getCode();
            position ++;
        }
        ChoseCode.getItems().addAll(codes);
        ChoseCode.setOnAction(this::getType);

    }

    private void getType(ActionEvent event){
        String[] types = new String[]{"Name","Category","Code","Amount","Price"};
        changeType.getItems().clear();
        changeType.getItems().addAll(types);
        changeType.setValue("Name");
        ChangeValue.setText("");
    }

    @FXML
    public void handleDownload1(ActionEvent event){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("downloadItems.json");
            mapper.writeValue(file, app.snackList);
        }
        catch (Exception e){
            System.out.println("Could not add Snack to database.");
            System.out.print(e.getStackTrace());
        }
    }
    @FXML
    public void handleDownload2(ActionEvent event){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("downloadSummary.json");
            mapper.writeValue(file, app.snackList);
        }
        catch (Exception e){
            System.out.println("Could not add Snack to database.");
            System.out.print(e.getStackTrace());
        }
    }

    @FXML
    public void handleModifyButton(ActionEvent event) {
        type = changeType.getValue();
        code = ChoseCode.getValue();
        value = ChangeValue.getText();

        if (type != null && code != null && value != null){
            Snack cur = null;
            for (Snack s:app.snackList){
                if (s.getCode().equals(code)){
                    cur = s;
                    break;
                }
            }
            int checkValid = 0;
            if (type.equals("Name")){
                for (Snack s:app.snackList){
                    if (s.getName().equals(value)){
                        checkValid = 1;
                        break;
                    }
                }
                if (checkValid == 0){
                    cur.changeName(value);
                }
            }
            else if (type.equals("Category")){
                if (cur.getCategory().equals(value) || !value.equals("Drinks") || !value.equals("Chips") ||!value.equals("Chocolates") ||!value.equals("Candies")){
                    checkValid = 1;
                }
                else{
                    cur.changeCategory(value);
                }
            }
            else if (type.equals("Code")){
                try{
                    int number = Integer.parseInt(value);
                    for (Snack s:app.snackList){
                        if (s.getCode().equals(number)){
                            checkValid = 1;
                            break;
                        }
                    }
                    if (checkValid == 0){
                        cur.changeCode(number);
                    }
                }
                catch (NumberFormatException ex){
                    checkValid = 1;
                }
            }
            else if (type.equals("Amount")){
                try{
                    int number = Integer.parseInt(value);
                    if (number > 15 || number < 0){
                        checkValid = 1;
                    }
                    if (checkValid == 0){
                        int diff = number - cur.getAmount();
                        cur.changeAmount(diff);
                    }
                }
                catch (NumberFormatException ex){
                    checkValid = 1;
                }
            }
            else if (type.equals("Price")){
                try{
                    Double number = Double.parseDouble(value);
                    if (number < 0){
                        checkValid = 1;
                    }
                    if (checkValid == 0){
                        Double diff = number - cur.getPrice();
                        cur.changePrice(diff);
                    }
                }
                catch (NumberFormatException ex){
                    checkValid = 1;
                }
            }

            app.addSnack();

            if (checkValid == 1){
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Invalid Change Value");
                errorAlert.show();
            }
            else{
                ChoseCode.setValue(1001);
                changeType.setValue("Name");
                ChangeValue.setText("");
                CurrentItems.getItems().clear();
                CurrentItems.getItems().addAll(app.snackList);
                Summary.getItems().clear();
                Summary.getItems().addAll(app.snackList);
            }
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Invalid Input");
            errorAlert.show();
        }
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) {
        try {
            app.Logout();
            ((Stage) SellerPage.getScene().getWindow()).close();
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
