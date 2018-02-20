package pomodorotimer.model;

import java.util.Timer;
import java.util.TimerTask;
import pomodorotimer.view.TabPaneController;
import pomodorotimer.PomodoroTimer;

//konstruktor breaktimera, w atrybutach wprowadzamy czas docelowy. konstruktor wywoluje statyczna metod Pomodorotimer.getTabPaneController
//by pobrac referencje do kontrolera i aktualizowac stan progress indicatora/
public class BreakTimer extends SuperTimer
{
    public BreakTimer(){}
    
    public BreakTimer(int time)
    {
        tabPaneController = PomodoroTimer.getTabPaneController();
        this.timeGoalInSeconds = time *60;
        this.timer = new Timer();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                timePassedInSeconds++;
                System.out.println("BreakTimer. Passed: " + timePassedInSeconds + " Goal: " + timeGoalInSeconds);
                tabPaneController.actualizeBreakProgressIndicator((double)timePassedInSeconds/timeGoalInSeconds);
                if(timePassedInSeconds>=timeGoalInSeconds)
                {
                    timer.cancel();
                    tabPaneController.resetButtons();
                    //miejsce na wysłanie statystyk do obiektu trzymającego statystyki
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