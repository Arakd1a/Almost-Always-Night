
import javax.sound.sampled.*;

public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String s, float vol) {

        try {

            AudioInputStream ais
                    = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream(
                                    s
                            )
                    );
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais
                    = AudioSystem.getAudioInputStream(
                            decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);

            FloatControl gainControl
                    = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(vol); // Reduce volume by 10 decibels.

        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    public void play() {

        if (clip == null) {
            return;
        }
        stop();

        clip.setFramePosition(0);

        clip.start();

    }

    public void loop() {
        if (clip == null) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.loop(-1);

    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.close();
    }

    public void setVolume() {
        if (clip.isRunning()) {

        }
    }

}
