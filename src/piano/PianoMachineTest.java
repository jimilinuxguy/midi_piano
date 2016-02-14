package piano;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
	PianoMachine pm = new PianoMachine();
	
    @Test
    public void singleNoteTest() throws MidiUnavailableException {
        String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void doubleNoteTest() throws MidiUnavailableException {
        String expected0 = "on(65,PIANO) wait(100) off(65,PIANO) wait(100) "
                + "on(63,PIANO) wait(100) off(63,PIANO)";
        
        Midi midi = Midi.getInstance();

        midi.clearHistory();
        
        pm.beginNote(new Pitch(5));
        Midi.wait(100);
        pm.endNote(new Pitch(5));
        Midi.wait(100);
        pm.beginNote(new Pitch(3));
        Midi.wait(100);
        pm.endNote(new Pitch(3));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void tripleNoteTest() throws MidiUnavailableException {
        String expected0 = "on(65,PIANO) wait(100) off(65,PIANO) "
                +"wait(100) on(63,PIANO) wait(100) off(63,PIANO) "
                +"wait(100) on(61,PIANO) wait(100) off(61,PIANO)";
        
        Midi midi = Midi.getInstance();

        midi.clearHistory();
        
        pm.beginNote(new Pitch(5));
        Midi.wait(100);
        pm.endNote(new Pitch(5));
        Midi.wait(100);
        pm.beginNote(new Pitch(3));
        Midi.wait(100);
        pm.endNote(new Pitch(3));
        Midi.wait(100);
        pm.beginNote(new Pitch(1));
        Midi.wait(100);
        pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    @Test
    public void instrumentTest() throws MidiUnavailableException {
   	 	
   	 // Middle case test.
   	 Instrument expected0 = Instrument.BRIGHT_PIANO;
   	 pm.changeInstrument();
   	 assertEquals(expected0, pm.CURRENT_INSTRUMENT);
   	 	
   	 // Boundary case test.
   	 Instrument expected1 = Instrument.PIANO;
   	 pm.CURRENT_INSTRUMENT = Instrument.GUNSHOT;
   	 pm.changeInstrument();
   	 assertEquals(expected1, pm.CURRENT_INSTRUMENT);
    }
    
	@Test
	public void octaveTest() throws MidiUnavailableException {
		//Not quite working yet.
		Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	Pitch testy = new Pitch(1);
    	
    	pm.beginNote(testy);
		Midi.wait(100);
		pm.endNote(testy);

		pm.shiftUp();
        pm.beginNote(testy);
		Midi.wait(100);
		pm.endNote(testy);
		
		pm.shiftDown();
		pm.beginNote(testy);
		Midi.wait(100);
		pm.endNote(testy);
		
        System.out.println(midi.history());
 //       assertEquals(expected0,midi.history());

	}
}