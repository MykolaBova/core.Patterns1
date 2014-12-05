package com.mb.patterns.command.v1;

import java.util.List;
import java.util.ArrayList;
 
/* The Command interface */
interface Command {
   void execute();
}
 
/* The Invoker class */
class Switch {
   private List<Command> history = new ArrayList<Command>();
 
   public void storeAndExecute(Command cmd) {
      this.history.add(cmd); // optional 
      cmd.execute();
   }
}
 
/* The Receiver class */
class Light {
 
   public void turnOn() {
      System.out.println("The light is on");
   }
 
   public void turnOff() {
      System.out.println("The light is off");
   }
}
 
/* The Command for turning on the light - ConcreteCommand #1 */
class FlipUpCommand implements Command {
   private Light theLight;
 
   public FlipUpCommand(Light light) {
      this.theLight = light;
   }
 
   public void execute(){
      theLight.turnOn();
   }
}
 
/* The Command for turning off the light - ConcreteCommand #2 */
class FlipDownCommand implements Command {
   private Light theLight;
 
   public FlipDownCommand(Light light) {
      this.theLight = light;
   }
 
   public void execute() {
      theLight.turnOff();
   }
}
 
/* The test class or client */
public class PressSwitch {
   public static void main(String[] args){
      Light lamp = new Light();
      Command switchUp = new FlipUpCommand(lamp);
      Command switchDown = new FlipDownCommand(lamp);
 
      Switch mySwitch = new Switch();
 
	  int switch1 = -1;
	  if(args[0].equals("ON")) {
			switch1 = 0;
	  } else if(args[0].equals("OFF")) {
			switch1 = 1;
	  }
	  
      switch(switch1) {
         case 0:
            mySwitch.storeAndExecute(switchUp);
         break;
         case 1:
            mySwitch.storeAndExecute(switchDown);
         break;
         default:
            System.out.println("Argument \"ON\" or \"OFF\" is required.");
       }
   }
}