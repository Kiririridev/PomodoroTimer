package pomodorotimer;

import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import pomodorotimer.view.TabPaneController;
import pomodorotimer.model.WorkTimer;
import pomodorotimer.model.BreakTimer;

//głowna klasa rozpoczynająca program
public class PomodoroTimer extends Application {
    
    Stage primaryStage = new Stage();   //okno
    TabPane tabPane;    //głowna scena z czterema kartami
    static TabPaneController tabPaneController;    //obiekt kontrolera
    
    //klasyczna metoda javafx, w której tworzymy okno, inicjujemy w nim TabPane
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        
        initTabPane();
        Scene scene = new Scene(tabPane, 600, 300);
        
        
        primaryStage.setTitle("Pomodoro Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //main method
    public static void main(String[] args) {
        launch(args);
    }
    
    
    //metoda inicjująca tab pane. ładuje plik fxml, dostaje kontroller
    public void initTabPane()
    {
        try
        {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(PomodoroTimer.class.getResource("view/TabPane.fxml"));
        tabPane = (TabPane) fxmlLoader.load();
        
        tabPaneController = fxmlLoader.getController();
        
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    } 
    
    //referencja do obiektu tabPaneController - uzywana przez timery do obslugi progressindicatorów
    public static TabPaneController getTabPaneController()
   {
       return tabPaneController;
   }
}
