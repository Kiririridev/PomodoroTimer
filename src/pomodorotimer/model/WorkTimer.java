package pomodorotimer.model;

import java.util.Timer;
import java.util.TimerTask;
import pomodorotimer.model.SuperTimer;
import pomodorotimer.view.TabPaneController;
import pomodorotimer.PomodoroTimer;

public class WorkTimer extends SuperTimer
{
    public WorkTimer(){}
    //konstruktor worktimera, w atrybutach wprowadzamy czas docelowy. konstruktor wywoluje statyczna metod Pomodorotimer.getTabPaneController
    //by pobrac referencje do kontrolera i aktualizowac stan progress indicatora/
    public WorkTimer(int time)
    {   
        tabPaneController = PomodoroTimer.getTabPaneController();
        this.timeGoalInSeconds = time * 60;
        this.timer = new Timer();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                timePassedInSeconds++;
                System.out.println("WorkTimer. Passed: " + timePassedInSeconds + " Goal: " + timeGoalInSeconds);
                tabPaneController.actualizeWorkProgressIndicator((double)timePassedInSeconds/timeGoalInSeconds);
                if(timePassedInSeconds>=timeGoalInSeconds)
                {   
                    tabPaneController.resetButtons();
                    timer.cancel();
                }
            }
        };
        
        timer.scheduleAtFixedRate(timerTask, 1000, 50);
    }
    
    
    @Override
    public void cancel()
    {
        this.timer.cancel();
        //wysylanie statystyk
        tabPaneController.resetButtonStartBreak();
        tabPaneController.resetbuttonStartWork();
        //tabPaneController.resetProgressIndocators();
        
    }
}
