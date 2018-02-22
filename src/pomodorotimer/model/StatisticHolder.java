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

/*
Obiekt StaticHolder zbiera statystyki z WorkTimera. Nie bierze on statystyk BreakTimera. Przechowuje on tablicę klasy WorkDate,
którą wczytuje z pliku WorkDates.txt przy kosntruowaniu programu. Program nadpisuje także ten plik za pomocą odpowiedniej metody. 
*/

public class StatisticHolder
{
    LocalDate localDate;
    WorkDate[] workDate = new WorkDate[8];
    Pattern ptrn;
    File file;
    /*
    Pętla w konstruktorze wczytuje tablicę siedmiu WorkDatów z pliku. Potem sprawdza, w którymś momencie występuje data sprzed tygodnia od dzisiejszego dnia.
    Jeśli tak, to oznacza to miejsce zmienną dif. Jeśli nie taka data nie wystąpiłą, wtedy diff pozostaje równy 7 i cała lista zepełnia się nowymi datami'
    od dzisiaj do tygodnia wstecz. Jeżeli diff jest mniejszy od 7, wtedy przepisuje dane od pozycji 6 do diff, natomiast tworzy nowe dni od dzisiaj do diff.
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
            int diff = 7;
            
            for(int i = 0; i<7;i++)
            {   
                year = in.nextInt();
                month = in.nextInt();
                day = in.nextInt();
                time = in.nextInt();
                
                workDate[i] = new WorkDate(year,month,day,time);                
                System.out.println(workDate[i].getDate());
                if(workDate[0].getDate().plusDays(i).equals(LocalDate.now()))
                {
                    diff = i;
                }
            }
            
            if(diff==7)
            {
                for(int i = 0; i<7;i++)
                {
                    workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
                }
            }
            else{
                for(int i = 6;i>=0;i--)
                {
                    if(i>=diff)workDate[i] = workDate[i-diff];
                    else workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
                }
            }
            
            /*
            for(int i = 0; i<7; i++)
            {
                if(workDate[i].getDate().equals(LocalDate.now().minusDays(i)));
                else
                {
                    workDate[i+1] = workDate[i];
                    workDate[i] = new WorkDate(LocalDate.now().minusDays(i));
                }
            }
            */
            in.close();
        }
        catch(IOException e)
        {   
            System.out.println("wyjebalo exception");
            System.out.println(e.getMessage() + e.getLocalizedMessage());
        }
    }
    /*
    //metoda testowa
    public static void main(String[] args)
    {
        StatisticHolder statistic = new StatisticHolder();
    }
    */
    
    
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
