package pomodorotimer.model;

import java.io.File;
import java.io.InputStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pomodorotimer.PomodoroTimer;

/**
 * class plays mp3 file loaded from file
 *  
 * @author Bartlomiej Kirejczyk
 */
public class WorkPlayer extends RingtonePlayer
{

    private final Media media = new Media(this.getClass().getResource("ringMaths.mp3").toString());
    private final MediaPlayer player = new MediaPlayer(media);
        
    public WorkPlayer(){}

    
    @Override
    public void play()
    {
        player.play();
    }
}
