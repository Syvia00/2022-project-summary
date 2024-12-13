package vending;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Generated
public class summaryController implements Initializable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    @FXML
    private Pane SummaryPage;

    @FXML
    private Text summary;

    public Stage prevStage;

    public App app;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new App();
        app.init();
        summary.setText(app.summary());
    }

    @FXML
    private void handleLogoutButton(ActionEvent event) {
        try {
            app.Logout();
            ((Stage) SummaryPage.getScene().getWindow()).close();
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
