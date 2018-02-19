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


public class PomodoroTimer extends Application {
    
    Stage primaryStage = new Stage();
    TabPane tabPane;
    TabPaneController tabPaneController;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        
        initTabPane();
        Scene scene = new Scene(tabPane, 600, 300);
        
        
        primaryStage.setTitle("Pomodoro Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
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
    
}
