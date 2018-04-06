package pomodorotimer.model;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import pomodorotimer.model.SuperTimer;
import pomodorotimer.view.TabPaneController;
import pomodorotimer.PomodoroTimer;

/*
Klasa WorkTimer dziedziczy po abstakcyjnej klasie SuperTimer. Jej wyniki są zbierane przez klasę StaticHolder


*/
public class WorkTimer extends SuperTimer
{
    public WorkTimer(){}
  
    /**
     * Work Timer. receives reference to TabPaneController to actualize progress indicators
     * 
     * @param time gets time goal
     */
    public WorkTimer(int time)
    {   
        tabPaneController = PomodoroTimer.getTabPaneController();
        this.timeGoalInSeconds = time * 60;
        this.timer = new Timer();
        WorkPlayer player = new WorkPlayer();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                if(!isPaused)
                {
                    timePassedInSeconds++;
                }
                System.out.println("WorkTimer. Passed: " + timePassedInSeconds + " Goal: " + timeGoalInSeconds);
                tabPaneController.actualizeWorkProgressIndicator((double)timePassedInSeconds/timeGoalInSeconds);
                if(timePassedInSeconds>=timeGoalInSeconds)
                {   
                    //Toolkit.getDefaultToolkit().beep();
                    player.play();
                    PomodoroTimer.getStatisticHolder().addTime(timePassedInSeconds);                    
                    tabPaneController.resetButtons();
                    tabPaneController.refreshChart();
                    timer.cancel();
                }
            }
        };
        
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
    
    //method stops timer and resets buttons
    @Override
    public void cancel()
    {
        PomodoroTimer.getStatisticHolder().addTime(timePassedInSeconds);
        this.timer.cancel();
        //wysylanie statystyk
        tabPaneController.resetButtonStartBreak();
        tabPaneController.resetbuttonStartWork();
        tabPaneController.refreshChart();
        //tabPaneController.resetProgressIndocators();
        
    }
    
  

    //reverses boolean isPaused, changes button text
    @Override
    public void pause()
    {
        isPaused = !isPaused;
        System.out.println("Timer state: " + isPaused);
        tabPaneController.switchPauseBreakButton(isPaused);
    }
}
