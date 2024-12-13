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

import javax.accessibility.AccessibleKeyBinding;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.*;
@Generated
public class cashierController implements Initializable  {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane CashierPage;

    public Stage prevStage;

    @FXML
    private TableView summary;
    @FXML
    private TableColumn SummaryDate;
    @FXML
    private TableColumn SummaryTime;
    @FXML
    private TableColumn SummaryItems;
    @FXML
    private TableColumn SummaryPaid;
    @FXML
    private TableColumn SummaryChange;
    @FXML
    private TableColumn SummaryMethod;

    @FXML
    private TableView changeList;
    @FXML
    private TableColumn a;
    @FXML
    private TableColumn b;
    @FXML
    private TableColumn c;
    @FXML
    private TableColumn d;
    @FXML
    private TableColumn f;
    @FXML
    private TableColumn g;
    @FXML
    private TableColumn h;
    @FXML
    private TableColumn i;
    @FXML
    private TableColumn j;
    @FXML
    private TableColumn k;
    @FXML
    private TableColumn l;

    @FXML
    private ChoiceBox<String> choseChange;
    @FXML
    private TextField ChangeValue;

    public App app;
    public String change;
    public String value;
    public List<Transaction> ListT = new ArrayList<Transaction>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new App();
        app.init();

        // Current Item
        changeList.getItems().addAll(app.cashList);
        PropertyValueFactory<Cash, Integer> value1 = new PropertyValueFactory<>("d100");
        a.setCellValueFactory(value1);
        PropertyValueFactory<Cash, Integer> value2 = new PropertyValueFactory<>("d50");
        b.setCellValueFactory(value2);
        PropertyValueFactory<Cash, Integer> value3 = new PropertyValueFactory<>("d20");
        c.setCellValueFactory(value3);
        PropertyValueFactory<Cash, Integer> value4 = new PropertyValueFactory<>("d10");
        d.setCellValueFactory(value4);
        PropertyValueFactory<Cash, Integer> value5 = new PropertyValueFactory<>("d5");
        f.setCellValueFactory(value5);
        PropertyValueFactory<Cash, Integer> value6 = new PropertyValueFactory<>("d2");
        g.setCellValueFactory(value6);
        PropertyValueFactory<Cash, Integer> value7 = new PropertyValueFactory<>("d1");
        h.setCellValueFactory(value7);
        PropertyValueFactory<Cash, Integer> value8 = new PropertyValueFactory<>("c50");
        i.setCellValueFactory(value8);
        PropertyValueFactory<Cash, Integer> value9 = new PropertyValueFactory<>("c20");
        j.setCellValueFactory(value9);
        PropertyValueFactory<Cash, Integer> value10 = new PropertyValueFactory<>("c10");
        k.setCellValueFactory(value10);
        PropertyValueFactory<Cash, Integer> value11 = new PropertyValueFactory<>("c5");
        l.setCellValueFactory(value11);


        //Summary

        for (Transaction t: app.transactionList){
            if (t.cancel == false){
                ListT.add(t);
            }
        }
        summary.getItems().addAll(ListT);
        PropertyValueFactory<Transaction, String> summary1 = new PropertyValueFactory<>("date");
        SummaryDate.setCellValueFactory(summary1);
        PropertyValueFactory<Transaction, String> summary2 = new PropertyValueFactory<>("time");
        SummaryTime.setCellValueFactory(summary2);
        PropertyValueFactory<Transaction, String> summary3 = new PropertyValueFactory<>("items");
        SummaryItems.setCellValueFactory(summary3);
        PropertyValueFactory<Transaction, Double> summary4 = new PropertyValueFactory<>("amountPaid");
        SummaryPaid.setCellValueFactory(summary4);
        PropertyValueFactory<Transaction, Double> summary5 = new PropertyValueFactory<>("change");
        SummaryChange.setCellValueFactory(summary5);
        PropertyValueFactory<Transaction, String> summary6 = new PropertyValueFactory<>("method");
        SummaryMethod.setCellValueFactory(summary6);

        String[] changes = new String[]{"100$","50$","20$","10$","5$","2$","1$","50c","20c","10c","5c"};
        choseChange.getItems().addAll(changes);
    }


    @FXML
    public void handleModifyButton(ActionEvent event) {
        change = choseChange.getValue();
        value = ChangeValue.getText();
        int checkValid = 0;
        int number = 0;
        try{
            number = Integer.parseInt(value);
        }
        catch (NumberFormatException ex){
            checkValid = 1;
        }
        if (checkValid == 0){
            app.cash.modify(change,number);
            app.cashList.set(0,app.cash);
            app.addCash();
            choseChange.setValue("100$");
            ChangeValue.setText("");
            changeList.getItems().clear();
            changeList.getItems().addAll(app.cashList);
            summary.getItems().clear();
            summary.getItems().addAll(app.cashList);
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Invalid Change Value");
            errorAlert.show();
        }
    }

    @FXML
    public void handleDownload1(ActionEvent event){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("downloadCoins.json");
            mapper.writeValue(file, app.cashList);
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
            File file = new File("downloadTransactions.json");
            mapper.writeValue(file, ListT);
        }
        catch (Exception e){
            System.out.println("Could not add Snack to database.");
            System.out.print(e.getStackTrace());
        }
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) {
        try {
            app.Logout();
            ((Stage) CashierPage.getScene().getWindow()).close();
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