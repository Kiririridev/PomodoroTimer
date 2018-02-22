package pomodorotimer.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    StatisticHolder statistics;
    @FXML
    private BarChart<?, ?> barChart;
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
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;
    
    //obiekty wykresu
   
    //metoda initialize ustawia tekst labeli i ustawia progres 0.0 w celach testowych
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        labelWork.setText(Integer.toString((int)sliderWork.getValue()) + " minutes");
        labelBreak.setText(Integer.toString((int)sliderBreak.getValue()) + " minutes");
        progressIndicatorWork.setProgress(0.0);
        progressIndicatorBreak.setProgress(0.0);
        
        
        //statystyki i wykres
        statistics = PomodoroTimer.getStatisticHolder();
        
        XYChart.Series xyChart = new XYChart.Series<>();
        for(int i = 6; i>=0; i--)
        {   
            System.out.println(statistics.getWorkDate(i).getDate().toString() +  statistics.getWorkDate(i).getWorkMins());
            xyChart.getData().add(new XYChart.Data<>(statistics.getWorkDate(i).getDate().toString(), statistics.getWorkDate(i).getWorkMins()));
            
        }
        barChart.getData().addAll(xyChart);

        
        
    }    
    
    /*
    Tworzy nowy obiekt typu SuperTimer. W atrybucie przekazuje czas ze slidera po jakim timer ma się wyłączyć.
    Zmienia także funcje i tekst przycisków. Przycisk użyty staje się przyciskiem cancel, który kończy działanie timera.
    Przycisk drugiego timera staje się przyciskiem pauzy. Używa on metody, która nie zatrzymuje timera, ale sprawia,
    że upływające sekundy obiektu timer nie są inkrementowane (pomimo, że timer dalej działa i wszystkie jest taski są wykonywane).
    Przycisk pauzy zmienia się z pause na paused w momencie właczenia pauzy. Jest to regulowane odpowiednimi metodami z TabPaneController.
    */

    @FXML
    public void onActionStartWork()
    {

        WorkTimer workTimer;
        workTimer = new WorkTimer((int)sliderWork.getValue());
        
        //metoda zmienia przyciski
        //disabluje i zmienia tekst buttonStartBreak
        this.buttonStartBreak.setText("Pause");
        this.buttonStartBreak.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event)
            {
                workTimer.pause();
            }
        });
        
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
       //nie rozumiem do końca tego rozwiazania, nie przerabiałem jeszcze wielowątkowości
       //bład był spowodowany tym, że tylko wątek JavyFX moze edytować tekst przycisków
       //zmiana tekstu przez wątek timera bez Platform.runLater wywoływała błąd
       Platform.runLater(new Runnable()
       {   
           @Override
           public void run()
            {
                buttonStartWork.setDisable(false);
                buttonStartWork.setText("Start Work");
            }
       });
       
       
    //zmiana na pierwotną funkcję przycisku   
        buttonStartWork.setOnAction(new EventHandler<ActionEvent> () {
                    
                    @Override
                    public void handle(ActionEvent event)
                    {
                        onActionStartWork();
                    }
                });
    }
    
    
    /*
    Tworzy nowy obiekt typu SuperTimer. W atrybucie przekazuje czas ze slidera po jakim timer ma się wyłączyć.
    Zmienia także funcje i tekst przycisków. Przycisk użyty staje się przyciskiem cancel, który kończy działanie timera.
    Przycisk drugiego timera staje się przyciskiem pauzy. Używa on metody, która nie zatrzymuje timera, ale sprawia,
    że upływające sekundy obiektu timer nie są inkrementowane (pomimo, że timer dalej działa i wszystkie jest taski są wykonywane).
    Przycisk pauzy zmienia się z pause na paused w momencie właczenia pauzy. Jest to regulowane odpowiednimi metodami z TabPaneController.
    */
    @FXML
    public void onActionStartBreak()
    {

        BreakTimer breakTimer;
        breakTimer = new BreakTimer((int)sliderBreak.getValue());
        
        this.buttonStartWork.setText("Pause");
        this.buttonStartWork.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event)
            {
                breakTimer.pause();
            }
        });
        
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
        //nie rozumiem do końca tego rozwiazania, nie przerabiałem jeszcze wielowątkowości
        //bład był spowodowany tym, że tylko wątek JavyFX moze edytować tekst przycisków
        //zmiana tekstu przez wątek timera bez Platform.runLater wywoływała błąd
        Platform.runLater(new Runnable()
        {   
            @Override
            public void run()
            {
                buttonStartBreak.setText("Start Break");
                buttonStartBreak.setDisable(false);
            }
        });
        
        //zmienia na pierwotną funkcję przycisku
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
    
    
    //zeruje wskazania obu ProgressIndicatorów
    @FXML
    public void resetProgressIndocators()
    {
        this.progressIndicatorBreak.setProgress(0);
        this.progressIndicatorWork.setProgress(0);
    }
    
    //wywołuje metody resetujące obu przycisków na raz(zrobione dla wygody)
    @FXML
    public void resetButtons()
    {
        this.resetButtonStartBreak();
        this.resetbuttonStartWork();
    }
    
    //zmienia ktest wyświetlany przez buttonStartBreak na "pause" lub "paused" zaleznie od stanu timera
    //atrybut przekazuje stan timera (czy jest spauzowany czy nie)
    @FXML
    public void switchPauseBreakButton(boolean isPaused)
    {
        if(isPaused)
        {
            this.buttonStartBreak.setText("Paused");
        }else
        {
            this.buttonStartBreak.setText("Pause");
        }
    }
    
    //zmienia ktest wyświetlany przez buttonStartWork na "pause" lub "paused" zaleznie od stanu timera
    //atrybut przekazuje stan timera (czy jest spauzowany czy nie)
    @FXML
    public void switchPauseWorkButton(boolean isPaused)
    {
        if(isPaused)
        {
            this.buttonStartWork.setText("Paused");
        }else
        {
            this.buttonStartWork.setText("Pause");
        }
    }
    
    @FXML
    public void refreshChart()
    {
       Platform.runLater(new Runnable()
       {   
           
           @Override
           public void run(){
           
           XYChart.Series xyChart = new XYChart.Series<>();
            for(int i = 6; i>=0; i--)
            {   
                System.out.println(statistics.getWorkDate(i).getDate().toString() +  statistics.getWorkDate(i).getWorkMins());
                xyChart.getData().add(new XYChart.Data<>(statistics.getWorkDate(i).getDate().toString(), statistics.getWorkDate(i).getWorkMins()));
            
            }
            barChart.getData().clear();
            barChart.getData().addAll(xyChart);
       }});
        
    }
    
    
    
}
