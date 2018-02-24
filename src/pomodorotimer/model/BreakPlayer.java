package pomodorotimer.model;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BreakPlayer extends RingtonePlayer
{
    private File file;
    private Media media;
    private MediaPlayer player;
    
    public BreakPlayer()
    {
        file = new File("ringMulan.mp3");
        media = new Media(file.toURI().toString());
        player = new MediaPlayer(media);
    }
    
    @Override
    public void play()
    {
        player.play();
    }
}
