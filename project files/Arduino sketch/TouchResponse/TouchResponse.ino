
// Pin for the LED
int LEDPin = 13;
// Pin to connect to your drawing
int cPin1 = 2;
int cPin2 = 3;
int cPin3 = 4;
int cPin4 = 5;
int cPin5 = 6;
int cPin6 = 7;
int cPin7 = 8;
int cPin8 = 9;
int cPin9 = 10;
int cPin10 = 11;

// This is how high the sensor needs to read in order
//  to trigger a touch.  You'll find this number
//  by trial and error, or you could take readings at 
//  the start of the program to dynamically calculate this.
int touchedCutoff = 55;

void setup(){
  Serial.begin(9600);
  // Set up the LED
  pinMode(LEDPin, OUTPUT);
  digitalWrite(LEDPin, LOW);
}

void loop(){
  // If the capacitive sensor reads above a certain threshold,
  //  turn on the LED
  if (readCapacitivePin(cPin1) > touchedCutoff) {
    digitalWrite(LEDPin, HIGH);
  }
  else {
    digitalWrite(LEDPin, LOW);
  }
  
  // Every 500 ms, print the value of the capacitive sensor
  if ( (millis() % 1) == 0){
    if (readCapacitivePin(cPin1) > touchedCutoff) {
      Serial.print("A");
      Serial.print("Z");
    }
    else
    {
      Serial.print("A");
      Serial.print('0');
    }

    if (readCapacitivePin(cPin2) > touchedCutoff) {
      Serial.print("B");
      Serial.print("Y");
    }
    else
    {
      Serial.print("B");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin3) > touchedCutoff) {
      Serial.print("C");
      Serial.print("X");
    }
    else
    {
      Serial.print("C");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin4) > touchedCutoff) {
      Serial.print("D");
      Serial.print("W");
    }
    else
    {
      Serial.print("D");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin5) > touchedCutoff) {
      Serial.print("E");
      Serial.print("V");
    }
    else
    {
      Serial.print("E");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin6) > touchedCutoff) {
      Serial.print("F");
      Serial.print("U");
    }
    else
    {
      Serial.print("F");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin7) > touchedCutoff) {
      Serial.print("G");
      Serial.print("T");
    }
    else
    {
      Serial.print("G");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin8) > touchedCutoff) {
      Serial.print("H");
      Serial.print("S");
    }
    else
    {
      Serial.print("H");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin9) > touchedCutoff) {
      Serial.print("I");
      Serial.print("R");
    }
    else
    {
      Serial.print("I");
      Serial.print('0');
    }
    if (readCapacitivePin(cPin10) > touchedCutoff) {
      Serial.print('1');
    }
    else
    {
      Serial.print('0');
    }
    Serial.println("");
  }
}

// readCapacitivePin
//  Input: Arduino pin number
//  Output: A number, from 0 to 17 expressing
//          how much capacitance is on the pin
//  When you touch the pin, or whatever you have
//  attached to it, the number will get higher
//  In order for this to work now,
// The pin should have a 1+Megaohm resistor pulling
//  it up to +5v.
uint8_t readCapacitivePin(int pinToMeasure){
  // This is how you declare a variable which
  //  will hold the PORT, PIN, and DDR registers
  //  on an AVR
  volatile uint8_t* port;
  volatile uint8_t* ddr;
  volatile uint8_t* pin;
  // Here we translate the input pin number from
  //  Arduino pin number to the AVR PORT, PIN, DDR,
  //  and which bit of those registers we care about.
  byte bitmask;
  if ((pinToMeasure >= 0) && (pinToMeasure <= 7)){
    port = &PORTD;
    ddr = &DDRD;
    bitmask = 1 << pinToMeasure;
    pin = &PIND;
  }
  if ((pinToMeasure > 7) && (pinToMeasure <= 13)){
    port = &PORTB;
    ddr = &DDRB;
    bitmask = 1 << (pinToMeasure - 8);
    pin = &PINB;
  }
  if ((pinToMeasure > 13) && (pinToMeasure <= 19)){
    port = &PORTC;
    ddr = &DDRC;
    bitmask = 1 << (pinToMeasure - 13);
    pin = &PINC;
  }
  // Discharge the pin first by setting it low and output
  *port &= ~(bitmask);
  *ddr  |= bitmask;
  delay(1);
  // Make the pin an input WITHOUT the internal pull-up on
  *ddr &= ~(bitmask);
  // Now see how long the pin to get pulled up
  int cycles = 16000;
  for(int i = 0; i < cycles; i++){
    if (*pin & bitmask){
      cycles = i;
      break;
    }
  }
  // Discharge the pin again by setting it low and output
  //  It's important to leave the pins low if you want to 
  //  be able to touch more than 1 sensor at a time - if
  //  the sensor is left pulled high, when you touch
  //  two sensors, your body will transfer the charge between
  //  sensors.
  *port &= ~(bitmask);
  *ddr  |= bitmask;
  
  return cycles;
}



