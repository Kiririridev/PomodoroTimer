package pomodorotimer.model;

import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.in;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;



public class StatisticHolder
{
    LocalDate localDate;
    WorkDate[] workDate = new WorkDate[8];
    Pattern ptrn;
    File file;
    /*
    Pętle w konstrktorze najpierw wczytują tydzień znajdujący się w pliku, a następnie sprawdzają czy dni zgadzają się z dzisiejszą datą
    i ostatnim tygodniem. Jeśli dnień i nie jest równy dzisiejszej dacie, to obiekt jest przesuwany na nastepne miejsce pętli a w miejsce dnia i
    tworzony jest nowy obiekt WorkDate. Dane z poprzednich dni nie są tracone, a tylko przesuwane na następne pozycje.
    
    */
    public StatisticHolder()
    {
        try
        {   
            int year;
            int month;
            int day;
            int time;
            file = new File("WorkDates.txt");
            Scanner in = new Scanner(file);
            
            for(int i = 0; i<7;i++)
            {   
                year = in.nextInt();
                month = in.nextInt();
                day = in.nextInt();
                time = in.nextInt();
                
                workDate[i] = new WorkDate(year,month,day,time);                
                System.out.println(workDate[i].getDate());
            }
            
            for(int i =0; i<7; i++)
            {
                if(workDate[i].getDate().equals(LocalDate.now().minusDays(i)));
                else
                {
                    workDate[i+1] = workDate[i];
                    workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
                }
            }
            
            in.close();
        }
        catch(IOException e)
        {   
            System.out.println("wyjebalo exception");
            System.out.println(e.getMessage() + e.getLocalizedMessage());
        }
    }
    public static void main(String[] args)
    {
        StatisticHolder statistic = new StatisticHolder();
    }
    
    public WorkDate getWorkDate(int i)
    {
        return workDate[i];
    }
    
    public void addTime(int timeInSec)
    {
        workDate[0].addWorkMins(timeInSec);
        writeFile();
    }
    
    public void writeFile()
    {
        try
        {
            PrintStream out = new PrintStream(file);
            System.setOut(out);
            for(int i = 0; i<7; i++)
            {
                System.out.printf("%d %d %d %d\n", workDate[i].getDate().getYear(), workDate[i].getDate().getMonthValue(), workDate[i].getDate().getDayOfMonth(), workDate[i].getWorkMins());
            }
            out.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}
