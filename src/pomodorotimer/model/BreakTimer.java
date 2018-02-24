package pomodorotimer.model;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import pomodorotimer.view.TabPaneController;
import pomodorotimer.PomodoroTimer;

/*
Klasa BreakTimer dziedziczy po abstrakcyjnej klasie Supertimer. Działa tak samo jak Worktimer,
jednak jej czas nie jest zapisywany przez StaticHolder.

*/

public class BreakTimer extends SuperTimer
{
    public BreakTimer(){}
    
    //konstruktor breaktimera, w atrybutach wprowadzamy czas docelowy. konstruktor wywoluje statyczna metod Pomodorotimer.getTabPaneController
    //by pobrac referencje do kontrolera i aktualizowac stan progress indicatora/
    //konstruktor uruchamia od razu timer
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
                    
                    //miejsce na wysłanie statystyk do obiektu trzymającego statystyki
                }
            }
        };
        
        timer.scheduleAtFixedRate(timerTask, 1000, 50);
    }
    
    //metoda zatrzymuje timer i powoduje resetowanie przycisków Start Work i Start Break
    @Override
    public void cancel()
    {
        this.timer.cancel();
        //wysylanie statystyk
        tabPaneController.resetButtonStartBreak();
        tabPaneController.resetbuttonStartWork();
        //tabPaneController.resetProgressIndocators();
    }
    
    //pauza - zmienia isPasued na odwrotna wartosc boolean
    //powoduje wywolanie metody TabPaneController zmieniajacego wyglad przycisków pauzy
    @Override
    public void pause()
    {
        isPaused = !isPaused;
        System.out.println("Timer state: " + isPaused);
        tabPaneController.switchPauseWorkButton(isPaused);
    }
}