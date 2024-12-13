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
public class ownerController  implements Initializable  {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane OwnerPage;

    public Stage prevStage;

    @FXML
    private TableView Summary;
    @FXML
    private TableColumn Date;
    @FXML
    private TableColumn Time;
    @FXML
    private TableColumn Name;
    @FXML
    private TableColumn Reason;

    @FXML
    private TableView UserList;
    @FXML
    private TableColumn UserName;
    @FXML
    private TableColumn Roles;

    @FXML
    private TextField Addname;
    @FXML
    private TextField AddPassword;
    @FXML
    private TextField AddRole;
    @FXML
    private TextField removeName;


    public App app;
    public String userName;
    public String userPassword;
    public String userRoles;
    public String rmName;
    public List<Transaction> ListT = new ArrayList<Transaction>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new App();
        app.init();

        // User Item
        UserList.getItems().addAll(app.userList);
        PropertyValueFactory<User, String> nameValue = new PropertyValueFactory<>("name");
        UserName.setCellValueFactory(nameValue);
        PropertyValueFactory<User, Double> rolesValue = new PropertyValueFactory<>("type");
        Roles.setCellValueFactory(rolesValue);

        // Summary Item
        for (Transaction t: app.transactionList){
            if (t.cancel == true){
                ListT.add(t);
            }
        }
        Summary.getItems().addAll(ListT);
        PropertyValueFactory<Transaction, String> dateValue = new PropertyValueFactory<>("date");
        Date.setCellValueFactory(dateValue);
        PropertyValueFactory<Transaction, Double> timeValue = new PropertyValueFactory<>("time");
        Time.setCellValueFactory(timeValue);
        PropertyValueFactory<Transaction, String> namev = new PropertyValueFactory<>("name");
        Name.setCellValueFactory(namev);
        PropertyValueFactory<Transaction, String> reasonValue = new PropertyValueFactory<>("reason");
        Reason.setCellValueFactory(reasonValue);
    }

    @FXML
    public void handleRemoveButton(ActionEvent event) {
        rmName = removeName.getText();
        Integer check = app.removeUser(rmName);
        if (check == 1){
            removeName.setText("");
            UserList.getItems().clear();
            UserList.getItems().addAll(app.userList);
        }
        else{
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Invalid Name");
            errorAlert.show();
        }
    }

    @FXML
    public void handleDownload1(ActionEvent event){
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("downloadUsers.json");
            mapper.writeValue(file, app.userList);
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
            File file = new File("downloadCancel.json");
            mapper.writeValue(file, ListT);
        }
        catch (Exception e){
            System.out.println("Could not add Snack to database.");
            System.out.print(e.getStackTrace());
        }
    }

    @FXML
    public void handleAddButton(ActionEvent event) {
        userName = Addname.getText();
        userPassword = AddPassword.getText();
        userRoles = AddRole.getText();
        if (userName != null && userPassword != null && userRoles != null){
            Integer check = app.addUserOwner(userName,userPassword,userRoles);
            if (check == 1){
                Addname.setText("");
                AddPassword.setText("");
                AddRole.setText("");
                UserList.getItems().clear();
                UserList.getItems().addAll(app.userList);
            }
            else if (check == 0){
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Invalid Type");
                errorAlert.show();
            }
            else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("User already exist");
                errorAlert.show();
            }
        }
    }

    @FXML
    private void handleChangeButton(ActionEvent event) {
        try {
            app.Logout();
            ((Stage) OwnerPage.getScene().getWindow()).close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Cashier.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, 800);
            stage.setTitle("Vending Machine");
            stage.setScene(scene);
            Canvas canvas = new Canvas(WIDTH, 800);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleModifyButton(ActionEvent event) {
        try {
            app.Logout();
            ((Stage) OwnerPage.getScene().getWindow()).close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Seller.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, 800);
            stage.setTitle("Vending Machine");
            stage.setScene(scene);
            Canvas canvas = new Canvas(WIDTH, 800);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) {
        try {
            app.Logout();
            ((Stage) OwnerPage.getScene().getWindow()).close();
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
