package pomodorotimer.model;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class RingtonePlayer {

    private Media media;
    private MediaPlayer player;
    
    public RingtonePlayer()
    {
       // playerBreak.setVolume(1);
    }
    
    public abstract void play();
    

}
