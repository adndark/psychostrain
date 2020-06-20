package PsychoSystem;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
import java.io.FileInputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Sound {

    boolean playing = false;
    Sequencer sound;

    public Sound(String filepath) {
        try {
            sound = MidiSystem.getSequencer();
            if (sound == null) {
                throw new MidiUnavailableException();
            }
            sound.open();
            FileInputStream is = new FileInputStream(filepath);
            Sequence mySeq = MidiSystem.getSequence(is);
            sound.setSequence(mySeq);
        } catch (Exception e) {
            System.out.println("Error de Sonido: " + filepath + " " + e
                    .getMessage());
        }
    }

    public void play() {
        try {
            playing = true;
            sound.start();
        } catch (Exception e) {
            System.out.println("Error de Sonido: " + e);
        }
    }

    public void stop() {
        try {
            playing = false;
            sound.stop();
            sound.close();
        } catch (Exception e) {
            System.out.println("Error de Sonido: " + e);
        }
    }

    public boolean isPlaying() {
        return playing;
    }

}
