package piano;

import javax.sound.midi.MidiUnavailableException;
import midi.Instrument;

import midi.Midi;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	public int octave = 0;
	public Instrument CURRENT_INSTRUMENT = Midi.DEFAULT_INSTRUMENT;    
	
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    /**
     * Begin playing note corresponding to pushed key.
     * 1 -> C, 2-> ^C, 3 -> D, 4 -> ^D, 5 -> E,
     * 6 -> F, 7 -> ^F, 8 -> G, 9 -> G^, 0 -> A,
     * - -> ^A, = -> B
     * 
     * @param rawPitch the designated pitch to be played
     */
    public void beginNote(Pitch rawPitch) {
    	switch(octave){
    	case 1:  rawPitch.transpose(Pitch.OCTAVE);
    			 break;
    	case 2:  rawPitch.transpose(Pitch.OCTAVE);
    			 rawPitch.transpose(Pitch.OCTAVE);
    			 break;
    	case -1: rawPitch.transpose(-Pitch.OCTAVE);
				 break;
    	case -2: rawPitch.transpose(-Pitch.OCTAVE);
    			 rawPitch.transpose(-Pitch.OCTAVE);
    			 break;
    	default:
     	}

    	if  ( rawPitch.toString().length() == 1){
            midi.beginNote(new Pitch(rawPitch.toString().charAt(0)).toMidiFrequency());
        }
        else if ( rawPitch.toString().equals("G^") ){
            midi.beginNote(new Pitch('G').transpose(1).toMidiFrequency());
        }
        else{
            midi.beginNote(new Pitch(rawPitch.toString().charAt(1)).transpose(1).toMidiFrequency());
        }

    }
    
    /**
     * Stop playing note corresponding to released key.
     * 
     * @param rawPitch the designated pitch to be stopped
     */
    public void endNote(Pitch rawPitch) {
    	//
    	switch(octave){
    	case 1:  rawPitch.transpose(Pitch.OCTAVE);
    			 break;
    	case 2:  rawPitch.transpose(Pitch.OCTAVE);
    			 rawPitch.transpose(Pitch.OCTAVE);
    			 break;
    	case -1: rawPitch.transpose(-Pitch.OCTAVE);
				 break;
    	case -2: rawPitch.transpose(-Pitch.OCTAVE);
    			 rawPitch.transpose(-Pitch.OCTAVE);
    			 break;
    	default:
     	}
        if  ( rawPitch.toString().length() == 1){
            midi.endNote(new Pitch(rawPitch.toString().charAt(0)).toMidiFrequency());
        }
        else if ( rawPitch.toString().equals("G^") ){
            midi.endNote(new Pitch('G').transpose(1).toMidiFrequency());
        }
        else{
            midi.endNote(new Pitch(rawPitch.toString().charAt(1)).transpose(1).toMidiFrequency());
        }
    }
    
    /**
     * Cycle the current instrument in the default ordering.
     * @modifies CURRENT_INSTRUMENT
     */
    public void changeInstrument() {
    	CURRENT_INSTRUMENT = CURRENT_INSTRUMENT.next();
    }
    
    //TODO write method spec
    public void shiftUp() {
    	if(octave < 2){octave++;}
    }
    
    //TODO write method spec
    public void shiftDown() {
    	if(octave > -2){octave--;}
    }
    
    //TODO write method spec
    public boolean toggleRecording() {
    	return false;
    	//TODO: implement for question 4
    }
    
    //TODO write method spec
    protected void playback() {    	
        //TODO: implement for question 4
    }

}
