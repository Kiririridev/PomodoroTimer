package pomodorotimer.model;
//zmienić fixer rate!!

import java.util.Timer;
import java.util.TimerTask;

import pomodorotimer.view.TabPaneController;
import pomodorotimer.PomodoroTimer;

//szablon timerów
public class SuperTimer{

    protected Timer timer;
    protected TimerTask timerTask;
    protected int timeGoalInSeconds;
    protected int timePassedInSeconds= 0;
    protected TabPaneController tabPaneController;
    
    public SuperTimer(){}
    
    public SuperTimer(int time)
    {
        tabPaneController = PomodoroTimer.getTabPaneController();
        this.timeGoalInSeconds = time * 60;
        timer = new Timer();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                timePassedInSeconds++;
                System.out.println("Passed: " + timePassedInSeconds + " Goal: " + timeGoalInSeconds);
                if(timePassedInSeconds>=timeGoalInSeconds)
                {
                    
                    timer.cancel();
                    //miejsce na wysłanie statystyk do obiektu trzymającego statystyki
                }
            }
        };
        
        timer.scheduleAtFixedRate(timerTask, 1000, 50);
    }
    
    public void cancel()
    {
    }
}

