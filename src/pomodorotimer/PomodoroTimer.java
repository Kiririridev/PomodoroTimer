package pomodorotimer;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import pomodorotimer.view.TabPaneController;
import pomodorotimer.model.StatisticHolder;

//głowna klasa rozpoczynająca program
public class PomodoroTimer extends Application {
    

    private TabPane tabPane;    //głowna scena z czterema kartami
    static TabPaneController tabPaneController;    //obiekt kontrolera
    static StatisticHolder statisticHolder; //obiekt zbieracza statystyk

    
    //klasyczna metoda javafx, w której tworzymy okno, wywołujemy w niej metodę inicjującą TabPane
    @Override
    public void start(Stage primaryStage) {
        
        //primaryStage = this.primaryStage;
        statisticHolder = new StatisticHolder();
        initTabPane();
        Scene scene = new Scene(tabPane, 600, 200);
        //primaryStage.getStyle().UNDECORATED;
        primaryStage.setTitle("Pomodoro Timer");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);

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
        
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    } 
    
    //referencja do obiektu tabPaneController - uzywana przez timery do obslugi progressindicatorów
    public static TabPaneController getTabPaneController()
   {
       return tabPaneController;
   }
    
    public static StatisticHolder getStatisticHolder()
    {
        return statisticHolder;
    }

}
