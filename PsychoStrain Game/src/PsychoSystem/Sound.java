
//Sound.java (Deprecated)

package PsychoSystem;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimides Diaz)

import java.io.*;
import javax.sound.midi.*;

public class Sound {
  
  boolean playing=false;
  Sequencer sound;
  
  public Sound(String s){    
    try{
      sound = MidiSystem.getSequencer();
          if (sound == null)
              throw new MidiUnavailableException();
          sound.open();
          FileInputStream is = new FileInputStream(s);
          Sequence mySeq = MidiSystem.getSequence(is);
          sound.setSequence(mySeq);
    } catch (Exception e){ System.out.println("Error de Sonido: "+e);}
  }
  
  public void play(){
    try{
    playing=true;
    sound.start();
    } catch(Exception e){System.out.println("Error de Sonido: "+e);}
  }
  
  public void stop(){
    try{
    playing=false;
    //sound.stop();
    //sound.close();
    } catch(Exception e){System.out.println("Error de Sonido: "+e);}
  }
  
  public boolean isPlaying(){
    return playing;
  }
  
  
}
