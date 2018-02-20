package pomodorotimer.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import pomodorotimer.model.*;
import pomodorotimer.PomodoroTimer;



//kontroler TabPane
public class TabPaneController implements Initializable {


    //wszystkie elementry
    TabPaneController tabPaneController;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabTimer;
    @FXML
    private AnchorPane anchorPaneTimer;
    @FXML
    private Slider sliderWork;
    @FXML
    private Label labelWork;
    @FXML
    private Label labelBreak; 
    @FXML
    private Button buttonStartWork;
    @FXML
    private Button buttonStartBreak;
    @FXML
    private Slider sliderBreak;
    @FXML
    private Tab tabStatistics;
    @FXML
    private AnchorPane anchorPaneStatistics;
    @FXML
    private BarChart<?, ?> chartStatistics;
    @FXML
    private Tab tabSettings;
    @FXML
    private AnchorPane anchorPaneSettings;
    @FXML
    private ChoiceBox<?> choiceBoxSettings;
    @FXML
    private Tab tabAbout;
    @FXML
    private AnchorPane achorPaneAbout;
    @FXML
    private TextArea textAreaAbout;
    @FXML
    private ImageView imageViewAbout;
    @FXML
    private ProgressIndicator progressIndicatorWork;
    @FXML
    private ProgressIndicator progressIndicatorBreak;

    
    //metoda initialize ustawia tekst labeli i ustawia progres 0.5 w celach testowych
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        labelWork.setText(Integer.toString((int)sliderWork.getValue()) + " minutes");
        labelBreak.setText(Integer.toString((int)sliderBreak.getValue()) + " minutes");
        progressIndicatorWork.setProgress(0.0);
        progressIndicatorBreak.setProgress(0.0);

    }    
    
    
    //toworzy egzemplarz obiektu SuperTimer i przekazuje mu w konstruktorze czasw minutach, ktory timer pozniej zamienia na sekundy
    //przy kazdym kliknieciu tworzy sie nowy obiekt supertimer
    @FXML
    public void onActionStartWork()
    {

        WorkTimer workTimer;
        workTimer = new WorkTimer((int)sliderWork.getValue());
        
        //metoda zmienia przyciski
        //disabluje i zmienia tekst buttonStartBreak
        this.buttonStartBreak.setText("WorkTimer working");
        this.buttonStartBreak.setDisable(true);
        this.progressIndicatorBreak.setProgress(0);
        
        //i zmienia tekst i przeznaczenie buttonStartWork
        this.buttonStartWork.setText("Cancel");
        this.buttonStartWork.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                workTimer.cancel();
            }
        });
    }
    
    //aktualizuje tekst na labelu work przy wykonywaniu akcji sliderem
    @FXML
    public void actualizeLabelWork()
    {
        labelWork.setText(Integer.toString((int)sliderWork.getValue()) + " minutes");
    }
    
    
    //resetuje buttonStartWork, nadaje mu pierwotny tekst i pierwotny on ActionEvent
    @FXML
    public void resetbuttonStartWork()
    {
       Platform.runLater(new Runnable()
       {   
           @Override
           public void run()
            {
                buttonStartWork.setDisable(false);
                buttonStartWork.setText("Start Work");
            }
       });
       
       
       
        buttonStartWork.setOnAction(new EventHandler<ActionEvent> () {
                    
                    @Override
                    public void handle(ActionEvent event)
                    {
                        onActionStartWork();
                    }
                });
    }
    
    
    //tworzy egzemplarz SuperTimer dla przerwy
    @FXML
    public void onActionStartBreak()
    {

        BreakTimer breakTimer;
        breakTimer = new BreakTimer((int)sliderBreak.getValue());
        
        this.buttonStartWork.setText("BreakTimer working");
        this.buttonStartWork.setDisable(true);
        this.progressIndicatorWork.setProgress(0);
        
        this.buttonStartBreak.setText("Cancel");
        this.buttonStartBreak.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent event) {
                breakTimer.cancel();
            }
        });
    }
    
    
    //aktualizuje tekst na labelu break przy wykonywaniu akcji sliderem
    @FXML
    public void actualizeLabelBreak()
    {
        labelBreak.setText(Integer.toString((int)sliderBreak.getValue()) + " minutes");
    }
    
    //resetuje buttonStartBreak
    @FXML
    public void resetButtonStartBreak()
    {
        
        Platform.runLater(new Runnable()
        {   
            @Override
            public void run()
            {
                buttonStartBreak.setText("Start Break");
                buttonStartBreak.setDisable(false);
            }
        });
        
        
        buttonStartBreak.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                onActionStartBreak();
            }
        });

    }
    //aktualizuje Progress indicator Work
    @FXML
    public void actualizeWorkProgressIndicator(double progress)
    {
        progressIndicatorWork.setProgress(progress);
    }
    
    
    //aktualizuje progress indicator Break
    @FXML
    public void actualizeBreakProgressIndicator(double progress)
    {
        progressIndicatorBreak.setProgress(progress);
    }
    
    @FXML
    public void resetProgressIndocators()
    {
        this.progressIndicatorBreak.setProgress(0);
        this.progressIndicatorWork.setProgress(0);
    }
    
    @FXML
    public void resetButtons()
    {
        this.resetButtonStartBreak();
        this.resetbuttonStartWork();
        
    }
    
}
