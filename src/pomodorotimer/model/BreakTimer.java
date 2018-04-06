package pomodorotimer.model;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import pomodorotimer.view.TabPaneController;
import pomodorotimer.PomodoroTimer;


/**
 * 
 * Works same as WorkTimer, just doesn't gather statistics
 * @author Bartlomiej Kirejczyk
 */
public class BreakTimer extends SuperTimer
{
    public BreakTimer(){}
    
 /**
     * Work Timer. receives reference to TabPaneController to actualize progress indicators
     * 
     * @param time gets time goal
     */
    public BreakTimer(int time)
    {
        tabPaneController = PomodoroTimer.getTabPaneController();
        this.timeGoalInSeconds = time *60;
        this.timer = new Timer();
        BreakPlayer player = new BreakPlayer();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                if(!isPaused)
                {
                    timePassedInSeconds++;
                }
                System.out.println("BreakTimer. Passed: " + timePassedInSeconds + " Goal: " + timeGoalInSeconds);
                tabPaneController.actualizeBreakProgressIndicator((double)timePassedInSeconds/timeGoalInSeconds);
                if(timePassedInSeconds>=timeGoalInSeconds)
                {
                    //Toolkit.getDefaultToolkit().beep();
                    player.play();
                    timer.cancel();
                    tabPaneController.resetButtons();
                    
   
                }
            }
        };
        
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    //method stops timer and resets buttons
    @Override
    public void cancel()
    {
        this.timer.cancel();
        //wysylanie statystyk
        tabPaneController.resetButtonStartBreak();
        tabPaneController.resetbuttonStartWork();
        //tabPaneController.resetProgressIndocators();
    }
    
    //reverses boolean isPaused, changes button text
    @Override
    public void pause()
    {
        isPaused = !isPaused;
        System.out.println("Timer state: " + isPaused);
        tabPaneController.switchPauseWorkButton(isPaused);
    }
}