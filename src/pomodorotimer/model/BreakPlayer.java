package pomodorotimer.model;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * class plays mp3 file loaded from file
 *  
 * @author Bartlomiej Kirejczyk
 */
public class BreakPlayer extends RingtonePlayer
{

    private final Media media = new Media(this.getClass().getResource("ringMulan.mp3").toString());
    private final MediaPlayer player = new MediaPlayer(media);
    
    public BreakPlayer(){}
    
    @Override
    public void play()
    {
        player.play();
    }
}
