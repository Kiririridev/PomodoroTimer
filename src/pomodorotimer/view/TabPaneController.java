package pomodorotimer.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import pomodorotimer.model.SuperTimer;
import pomodorotimer.model.SuperTimerTest;



public class TabPaneController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        labelWork.setText(Integer.toString((int)sliderWork.getValue()) + " minutes");
    }    
    
    public void onActionStartWork()
    {
        
        SuperTimer workTimer;
        workTimer = new SuperTimer((int)sliderWork.getValue());
    }
    @FXML
    public void actualizeLabels()
    {
        labelWork.setText(Integer.toString((int)sliderWork.getValue()) + " minutes");
    }
    
}
