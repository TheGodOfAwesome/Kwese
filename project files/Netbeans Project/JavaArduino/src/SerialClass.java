
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import org.jfugue.Player;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kuz
 */
public class SerialClass implements SerialPortEventListener {

 private static int cnt;
 private static int i;
 private static int LastPlayed = 0;
 private static int notesOff = 0;
 private static int KeyPress = 0; 
 private static int[] sustain = new int[3];
                 
 public SerialPort serialPort;

 /** The port we're normally going to use. */

 private static final String PORT_NAMES[] = {"COM4"};

 

public static BufferedReader input;

public static OutputStream output;

 /** Milliseconds to block while waiting for port open */

 public static final int TIME_OUT = 2000;

 /** Default bits per second for COM port. */

 public static final int DATA_RATE = 9600;

 

public void initialize() {

 CommPortIdentifier portId = null;

 Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

 

//First, Find an instance of serial port as set in PORT_NAMES.

 while (portEnum.hasMoreElements()) {

 CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

 for (String portName : PORT_NAMES) {

 if (currPortId.getName().equals(portName)) {

 portId = currPortId;

 break;

 }

 }

 }

 if (portId == null) {

 System.out.println("Could not find COM port.");

 return;

 }

 

try {

 // open serial port, and use class name for the appName.

 serialPort = (SerialPort) portId.open(this.getClass().getName(),

 TIME_OUT);

 

// set port parameters

 serialPort.setSerialPortParams(DATA_RATE,

 SerialPort.DATABITS_8,

 SerialPort.STOPBITS_1,

 SerialPort.PARITY_NONE);

 

// open the streams

 input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

 output = serialPort.getOutputStream();

 char ch = 1;

 output.write(ch);

 

 

 // add event listeners

 serialPort.addEventListener(this);

 serialPort.notifyOnDataAvailable(true);

 } catch (Exception e) {

 System.err.println(e.toString());

 }

 }

 

public synchronized void close() {

 if (serialPort != null) {

 serialPort.removeEventListener();

 serialPort.close();

 }

 }

 

public synchronized void serialEvent(SerialPortEvent oEvent) {

 if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {

 try {

 String inputLine=input.readLine();

 //System.out.println(inputLine);
 
 int A = inputLine.indexOf('Z');
 int B = inputLine.indexOf('Y');
 int C = inputLine.indexOf('X');
 int D = inputLine.indexOf('W');
 int E = inputLine.indexOf('V');
 int F = inputLine.indexOf('U');
 int G = inputLine.indexOf('T');
 int H = inputLine.indexOf('S');
 int I = inputLine.indexOf('R');
 int J = inputLine.indexOf('Q');
 
 //int Sum = A + B + C + D + E + F + G + H + I; 
int channel = 1; // between 1 and 16
int volume = 127; // between 0 et 127
int duration = 100; // in milliseconds
int instrument =  0;

 
Thread playA = new Thread() {
 public void run() {
 //the following lines will play C when it is touched
 try {
     if( A > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[1].programChange(instr[instrument].getPatch().getProgram());
     
     channels[1].noteOn( 60, volume ); // C note
     Thread.sleep( duration );
     channels[channel].noteOff( 60 );	
         
     } 
     
 } catch(Exception e) {}
 }
 };

Thread playB = new Thread() {
 public void run() {
 //the following lines will play D it is touched
 try {
     if( B > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[2].programChange(instr[instrument].getPatch().getProgram());
     
     channels[2].noteOn( 62, volume ); // D note
     Thread.sleep( duration );	
     channels[channel].noteOff( 62 );
         
     } 
     
 } catch(Exception e) {}
 }
 };

Thread playC = new Thread() {
 public void run() {
 //the following lines will play E when it is touched
 try {
     if( C > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[3].programChange(instr[instrument].getPatch().getProgram());
     
     channels[3].noteOn( 64, volume ); // E note
     Thread.sleep( duration );	
     channels[channel].noteOff( 64 );
         
     } 
     
 } catch(Exception e) {}
 }
 };

Thread playD = new Thread() {
 public void run() {
 //the following lines will play F when it is touched
 try {
     if( D > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[4].programChange(instr[instrument].getPatch().getProgram());
     
     channels[4].noteOn( 65, volume ); // F note
     Thread.sleep( duration );
     channels[channel].noteOff( 65 );	
         
     } 
     
 } catch(Exception e) {}
 }
 };

 Thread playE = new Thread() {
 public void run() {
 //the following lines will play G it is touched
 try {
     if( E > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[5].programChange(instr[instrument].getPatch().getProgram());
     
     channels[5].noteOn( 67, volume ); // G note
     Thread.sleep( duration );
     channels[channel].noteOff( 67 );	
         
     } 
     
 } catch(Exception e) {}
 }
 };
 
 Thread playF = new Thread() {
 public void run() {
 //the following lines will play A when it is touched
 try {
     if( F > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[6].programChange(instr[instrument].getPatch().getProgram());
     
     channels[6].noteOn( 70, volume ); // A note
     Thread.sleep( duration );	
     channels[channel].noteOff( 70 );
         
     } 
     
 } catch(Exception e) {}
 }
 };

 Thread playG = new Thread() {
 public void run() {
 //the following lines will play A when it is touched
 try {
     if( G > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[7].programChange(instr[instrument].getPatch().getProgram());
     
     channels[7].noteOn( 69, volume ); // A note
     Thread.sleep( duration );	
     channels[channel].noteOff( 69 );
         
     } 
     
 } catch(Exception e) {}
 }
 };
 
 Thread playH = new Thread() {
 public void run() {
 //the following lines will play B it touched
 try {
     if( H > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[8].programChange(instr[instrument].getPatch().getProgram());
     
     channels[8].noteOn( 71, volume ); // {B note, volume} 
     Thread.sleep( duration );	
     channels[channel].noteOff( 71 );
     
     }    
 } catch(Exception e) {}
     
 }
 };
 
 Thread playI = new Thread() {
 public void run() {
 //the following lines will play C# when it is touched
 try {
     if( I > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[9].programChange(instr[instrument].getPatch().getProgram());
     
     channels[9].noteOn( 61, volume ); // C# note
     Thread.sleep( duration );	
         
     } 
     
 } catch(Exception e) {}
 }
 };
 
 Thread playJ = new Thread() {
 public void run() {
 //the following lines will play G# when it is touched
 try {
     if( J > 1 ){
     Synthesizer synth = MidiSystem.getSynthesizer();
     synth.open();
     final MidiChannel[] channels = synth.getChannels();
     Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
     channels[10].programChange(instr[instrument].getPatch().getProgram());
     
     channels[10].noteOn( 68, volume ); // G# note
     Thread.sleep( duration );	
         
     } 
     
 } catch(Exception e) {}
 }
 };

playA.start();
playB.start(); 
playC.start();
playD.start(); 
playE.start();
playF.start();
playG.start();
playH.start(); 
playI.start();
playJ.start();
 //System.out.println(Sum);
 
 } catch (Exception e) {

 System.err.println(e.toString());

 }

 }

 

 }

 

 public static synchronized void writeData(String data) {

 System.out.println("Sent: " + data);

 try {

 output.write(data.getBytes());

 } catch (Exception e) {

 System.out.println("could not write to port");

 }

 }

  

public static void main(String[] args) throws Exception {


 SerialClass main = new SerialClass();

 main.initialize();

 Thread t=new Thread() {

 public void run() {

 //the following line will keep this app alive for 1000 seconds,

 //waiting for events to occur and responding to them (printing incoming messages to console).

 try {Thread.sleep(1500);

 writeData("2");} catch (InterruptedException ie) {}

 }

 };

 t.start();

 System.out.println("Started");

   ActionListener actListner = new ActionListener() {
	 
	@Override	 
	public void actionPerformed(ActionEvent event) { //this an action event used to keep time it is responsible for adding 1 to cnt (count) every millisecond
	    cnt += 1; //the variable cnt (count) is incremented every millisecond
	}
	 
       };
	 
       Timer timer = new Timer(1, actListner); //A timer thread triggering an action event every millisecond
       timer.start();
       }
}