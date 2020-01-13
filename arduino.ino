
# include<EEPROM.h>

int output = 53; //LED

int pirPin = 52;  //Counter for Syringes
int laststate = 0;
int counter=0;

char serialData = '0'; //EEPROM
int addr = 100; //EEPROM

void setup() 
{  
  pinMode(output, OUTPUT);
  
  pinMode(pirPin, INPUT);
  Serial.begin(9600);
  counter = EEPROM.read(addr);
  
}

void loop() // runs repeatedly after setup() finishes
{ 
  
  
  int state = digitalRead(pirPin);
  digitalWrite(output,state);

  if ( state != laststate)
  {  
     laststate = state; 
     counter=counter+1;
     EEPROM.write(addr,counter);
     delay(5000);
     
  }

  
  if (Serial.available()>0){
  
      serialData = Serial.read();
      if (serialData=='5'){
        counter=0;
        EEPROM.write(addr,counter);
      }
  }
  Serial.println(EEPROM.read(addr));  
  delay(500);

}
