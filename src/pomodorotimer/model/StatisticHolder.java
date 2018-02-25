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




/*
Obiekt StaticHolder zbiera statystyki z WorkTimera. Nie bierze on statystyk BreakTimera. Przechowuje on tablicę klasy WorkDate,
którą wczytuje z pliku WorkDates.txt przy kosntruowaniu programu. Program nadpisuje także ten plik za pomocą odpowiedniej metody. 
*/

public class StatisticHolder
{
    InputStream inputStr;
    private WorkDate[] workDate = new WorkDate[8];
    private File file = new File("WorkDates.txt");
    URI uri;
    
    /*
    Pętla w konstruktorze wczytuje tablicę siedmiu WorkDatów z pliku. Potem sprawdza, czy w którymś momencie występuje data sprzed tygodnia od dzisiejszego dnia.
    Jeśli tak, to oznacza to miejsce zmienną dayMark. Jeśli nie taka data nie wystąpiłą, wtedy diff pozostaje równy 7 i cała lista zepełnia się nowymi datami'
    od dzisiaj do tygodnia wstecz. Jeżeli dayMark jest mniejszy od 7, wtedy przepisuje dane od pozycji 6 do dayMark, natomiast tworzy nowe dni od dzisiaj do dayMark.
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
    
    /*
    Metoda zapisuje obecne dane przetrzymywane przez obiekt StaticHolder w pliku. Format danych to "rok miesiąc dzień czasPracy\n" jednak
    format .txt nie radzi sobie z \n z niewiadomych przyczyn. Nadpisiwany jest stary plik otwierany przy stworzeniu klasy.
    Program zbiera dane do tygodnia wstecz.
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
