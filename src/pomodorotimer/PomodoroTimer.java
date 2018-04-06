package pomodorotimer;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import pomodorotimer.view.TabPaneController;
import pomodorotimer.model.StatisticHolder;

/**
 * @author Bartlomiej Kirejczyk
 * 
 * Main Class
 */
public class PomodoroTimer extends Application {
    

    private TabPane tabPane;    //main Pane, with three tabs
    static TabPaneController tabPaneController;    //tabPaneController
    static StatisticHolder statisticHolder; //statistic holder

    
 
    /**
     * Classic JavaFX method, creates Stage, calls initTabPane()
     * @param primaryStage 
     */
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
        
        Image icon = new Image(this.getClass().getResourceAsStream("view/uzi.jpg"));
        primaryStage.getIcons().add(icon);
        primaryStage.setIconified(true);
        

        primaryStage.show();
        
        

    }
    
    //main method
    public static void main(String[] args) {
        launch(args);
    }
    
    

    /**
     * Initializes Tab Pane. Loads FXML and receives controller
     */
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
    

    /**
     * @return TabPaneController
     * used by Timers for ProgressIndicators
     */
    public static TabPaneController getTabPaneController()
    {
       return tabPaneController;
    }
    
    /**
     * 
     * @return StatisticHolder 
     */
    public static StatisticHolder getStatisticHolder()
    {
        return statisticHolder;
    }

}
