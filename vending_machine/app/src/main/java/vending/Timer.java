package vending;

import javafx.scene.text.Text;
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

import java.net.URL;
import java.util.ResourceBundle;

public class Timer{

    Text text;
    double time;
    int minute;
    int second;
    double FRAMETIME = 1.0/60.0;
    public Timer(Text text) {
        this.text = text;
        this.time = 0;
    }

    public void update() {
        this.time += FRAMETIME;

        second = (int)time % 60;
        minute = (int)time / 60;

        this.text.setText(String.format("%2d:%2d", minute, second));
    }

    public Text getText() {return this.text; }
    public void reset(){
        this.time = 0;
    }
    public int getMinute(){
        return this.minute;
    }
    public int getSecond(){
        return this.second;
    }
}
