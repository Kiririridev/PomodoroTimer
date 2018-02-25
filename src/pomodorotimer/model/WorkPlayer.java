package pomodorotimer.model;

import java.io.File;
import java.io.InputStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pomodorotimer.PomodoroTimer;

public class WorkPlayer extends RingtonePlayer
{

    private final Media media;
    private final MediaPlayer player;
        
    public WorkPlayer()
    {
    //    file = new File(this.getClass().getResourceAsStream("ringMaths.mp3").toString());
//      media = new Media(file.toURI().toString());
 //       InputStream inputStr = this.getClass().getResourceAsStream("ringMaths.mp3");
        //media = new Media(PomodoroTimer.class.getClass().getResource("model/ringMaths.mp3").toString());
//        System.out.println("inputStr: " + inputStr.toString());
        media = new Media(this.getClass().getResource("ringMaths.mp3").toString());
        player = new MediaPlayer(media);
    }
    
    @Override
    public void play()
    {
        player.play();
    }
}
