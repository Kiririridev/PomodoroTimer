package pomodorotimer.model;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class WorkPlayer extends RingtonePlayer
{
        private File file;
        private Media media;
        private MediaPlayer player;
        
    public WorkPlayer()
    {
        file = new File("ringMaths.mp3");
        media = new Media(file.toURI().toString());
        player = new MediaPlayer(media);
    }
    
    @Override
    public void play()
    {
        player.play();
    }
}
