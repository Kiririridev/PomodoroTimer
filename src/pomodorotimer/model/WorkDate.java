package pomodorotimer.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/*
Klasa obiektu przechowywanego przez StatisticHolder. Zapisuje date i czas pracy konkretnego dnia w minutach.
Mocno korzyta z klasy LocalDate. 
*/


public class WorkDate {
    private int year;
    private int month;
    private int day;
    private int workMins;
    
    public WorkDate()
    {
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonthValue();
        this.day = LocalDate.now().getDayOfMonth();
        this.workMins = 0;
    }
    
    public WorkDate(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.workMins = 0;
    }
    
    public WorkDate(LocalDate localDate)
    {
        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
    }
    
    public WorkDate(int year, int month, int day, int workMins)
    {
        this.day = day;
        this.month = month;
        this.year = year;
        this.workMins = workMins;
    }
    
    public void addWorkMins(int timeInSec)
    {
        workMins+=(timeInSec/60);
    }
    
    public LocalDate getDate()
    {
        return LocalDate.of(this.year, this.month, this.day);
    }
    
    public int getWorkMins()
    {
        return this.workMins;
    }

}