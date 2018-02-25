package pomodorotimer.model;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BreakPlayer extends RingtonePlayer
{

    private final Media media;
    private final MediaPlayer player;
    
    public BreakPlayer()
    {

        media = new Media(this.getClass().getResource("ringMulan.mp3").toString());
        player = new MediaPlayer(media);
    }
    
    @Override
    public void play()
    {
        player.play();
    }
}
