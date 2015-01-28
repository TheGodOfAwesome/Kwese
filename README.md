# Kwese
 
Kwese app

==============

Kwese is a Java app built to listen to capacitive touch inputs from an 
arduino and use these to play musical notes that depend on which input 
is touched. The musical notes are generated using the jfugue-4.0.3 class.
Jfugue generates the notes using midi

To run

1. Upload the touchresponse.ino to your arduino. 

2. Connect the arduino to the host pc.

3. Run the netbeans JavaArduino application on the same host  
   so they can catch the arduino inputs.

/************************************************/

Note 
To find the circuit construction for arduino capacitive touch look up the
Makey makey project. Modify build to suit requirements.

There is some latency when too many notes are played in quick succession
Any fixes are welcome.


follow on twitter @kuzytheawsomest
email kmuvezwa@gmail.com
K. Muvezwa 2014 
