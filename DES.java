/*Joshua Ramos
 * When you run the program, it will log the output to Simulation.txt
 * You will not be able to see the output in real time in the Simulation.txt file.
 * To see output, Simulation.txt MUST be closed, and can be opened once program finishes running.
 * 
 * 
 * NOTE: My input is no more than 379 minutes long (1 minute == 1 sec).
 * Simulation will stop if all clients are processed(which is at minute 379), or if time is up.
 * 
 * 
 * If you want to see the output in your IDE, just comment lines 51 and 52.
 */

import java.io.*;
import java.util.*;
public class DES{
  public static void main(String [] args) throws Exception{
    
    
    
    //Priority Queue
    PriorityQueue<UberUser> pq = new PriorityQueue<UberUser>();
    
    //Fill the Priority Queue
    BufferedReader br=new BufferedReader(new FileReader("uberLog.txt"));
    String line = br.readLine();
    
    while(line != null){
      pq.add(new UberUser(line));
      line = br.readLine();
    }
    br.close();
    
    //*****Simulation*****
    boolean simulator = true;
    int TIME = 0;
    int STOP = 0;
    boolean transit = false;
    int requestsProcessed = 0;
    int milesTotal = 0;
    int moneyEarned = 0;
    
    //Ask Driver for total driving time
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome to Uber");
    System.out.println("Enter the number of minutes you would like to drive: ");
    STOP = input.nextInt();
    input.close();
    System.out.println("Thanks for driving for Uber");
    System.out.println("Open Simulation.txt to see your driving log after "
                      + STOP + " minutes. (1 minute == 1 sec)");
    
    //Output to Simulation.txt
    PrintStream out = new PrintStream(new FileOutputStream("Simulation.txt"));
    System.setOut(out);
    
    //pick up fist client
    int pickUpTime=-1;
    int rideTime=-1;
    int dropOffTime=-1;
    
    UberUser nextClient = pq.poll();
    System.out.println("Picking up first client: "+nextClient.getName()+" From: "+nextClient.getPickup()+
                       " To: "+nextClient.getDest()+" Driving "+nextClient.getMiles()+" miles.");
    pickUpTime = TIME;
    rideTime = nextClient.getMiles();
    dropOffTime = pickUpTime+rideTime;
    requestsProcessed++;
    milesTotal +=nextClient.getMiles();
    transit = true;
    
    //start simulation
    while(simulator){
      if(pq.isEmpty()){
        System.out.println("No more clients at this time.");
        System.out.println("************************");
        System.out.println("You drove "+requestsProcessed+" people for a total of "+milesTotal+" miles.");
        System.out.println("You earned a total of $"+moneyEarned);
        System.out.println("Thanks for driving for UBER!");
        simulator = false;
      }
      //pick up
      if(!transit){
        nextClient = pq.poll();
        
        System.out.println("Picking up: "+nextClient.getName()+" From: "+nextClient.getPickup()+
                           " To: "+nextClient.getDest()+" Driving "+nextClient.getMiles()+" miles.");
        pickUpTime = TIME;
        rideTime = nextClient.getMiles();
        dropOffTime = pickUpTime+rideTime;
        requestsProcessed++;
        milesTotal +=nextClient.getMiles();
        transit = true;
      }
      
      //drop off
      if(TIME == dropOffTime){
        if(transit){
          System.out.println("Dropping "+nextClient.getName()+" you got $"+nextClient.getEarnings()+" for this trip");
          moneyEarned += nextClient.getEarnings();
        }
        transit = false;
      }
      
      //timer
      Thread.sleep(1000);
      System.out.println("Minute: "+TIME);
      TIME++;
      
      //If time is up, then finish simulation
      if(TIME==STOP){
        simulator = false;
        if(transit){
          System.out.println("Dropping "+nextClient.getName()+" you got $"+nextClient.getEarnings()+" for this trip");
        }
        transit = false;
        System.out.println("************************");
        System.out.println("You drove "+requestsProcessed+" people for a total of "+milesTotal+" miles.");
        System.out.println("There are "+pq.size()+" people left in queue");
        System.out.println("You earned a total of $"+moneyEarned);
        System.out.println("Thanks for driving for UBER!");
      }
    }
  }
}
