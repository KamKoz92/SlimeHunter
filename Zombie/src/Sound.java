import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    
    private AudioInputStream audioInputStream;
    private Clip clip;

    public Sound(String audioFilePath, float volume) {
        try {
            File file = new File(audioFilePath);
            clip = AudioSystem.getClip();
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioInputStream);
            clip.setFramePosition(0);   
            setVolume(volume);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolume(float volume) {
    if (volume < 0f || volume > 1f)
        throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume)); 
    }
    
    public void play() {
        try {
            clip.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        clip.loop(0);
        clip.stop();
        clip.setFramePosition(0);
    }
    public void playInLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
