package pomodorotimer.model;

import java.util.Timer;
import java.util.TimerTask;


public class SuperTimer {

    Timer timer;
    TimerTask timerTask;
    int timeGoalInSeconds;
    int timePassedInSeconds= 0;
    
    public SuperTimer(int time)
    {
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
        
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
    
    
}
