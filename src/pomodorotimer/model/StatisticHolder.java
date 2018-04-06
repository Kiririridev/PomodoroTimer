package pomodorotimer.model;

import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;





/**
 * Gathers statistics from WorkTimer. Uses  WorkDate class
 * Loads text file "WorkDates.txt" and overwrites this file when adding next WorkDates
 * 
 * @author Bartlomiej Kirejczyk
 */
public class StatisticHolder
{
    InputStream inputStr;
    private WorkDate[] workDate = new WorkDate[8];
    private File file = new File("WorkDates.txt");
    URI uri;
 
    /**
     * loads array of seven last WorkDates from "WorkDates.txt". Checks which of the loaded day is the one week ago and marks it. 
     * If it hasn't found the day, it means last WorkDate is from more than week ago and it creates new seven WorkDates. 
     * Otherwise it creates new WorkDates from today to marked day and load the rest
     */
    public StatisticHolder()
    {
 
        try
        {   
            file = new File("WorkDates.txt");
            Scanner in = new Scanner(file);
            int dayMark = 7;
            
            for(int i = 0; i<7;i++)
            {   
    
                workDate[i] = new WorkDate(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());  
                System.out.println(workDate[i].getDate());
                if(workDate[0].getDate().plusDays(i).equals(LocalDate.now()))
                {
                    dayMark = i;
                }
            }
            
            if(dayMark==7)
            {
                for(int i = 0; i<7;i++)
                {
                    workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
                }
            }
            else{
                for(int i = 6;i>=0;i--)
                {
                    if(i>=dayMark)
                    {
                        workDate[i] = workDate[i-dayMark];
                    }else
                    {
                        workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
                    }
                }
            }

            in.close();
        }
        catch(Exception e)
        {   
            System.out.println("wyjebalo exception");
            System.out.println(e.getMessage() + e.getLocalizedMessage());
            
            for(int i = 0; i<7; i++)
            {
                workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
            }
        }
    }

    
    public WorkDate getWorkDate(int i)
    {
        return workDate[i];
    }
    
    //metoda dodaje czas pracy do dzisiejszego dnia oraz aktualizuje plik WorkDates.txt
    public void addTime(int timeInSec)
    {
        workDate[0].addWorkMins(timeInSec);
        writeFile();
    }
    
    
    /**
     * Method saves currently holded WorkDates in text file.
     * Format of data is "year month day time".
     * When StatisticHolder is contructed it overwrites previous "WorkDates.txt"
     */
    public void writeFile()
    {
        //PrintStream out = new PrintStream(this.getClass().getResourceAsStream("WorkDates.txt").toString())
        
        
        try
        {       
                PrintStream printStr = new PrintStream(file);
                System.setOut(printStr);
                for(int i = 0; i<7; i++)
                {
                    
                    System.out.printf("%d %d %d %d\n", workDate[i].getDate().getYear(), workDate[i].getDate().getMonthValue(), workDate[i].getDate().getDayOfMonth(), workDate[i].getWorkMins());
                }
            
        }catch(Exception e)
        {
            e.printStackTrace();   
 
        }
    }
    
    
}
