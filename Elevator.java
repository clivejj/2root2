import java.util.*;
public class Elevator{
    
    private ArrayPriorityQueue<Integer> riders;
    private int timeToEnd;
    private boolean available;
    //ints for min and max floors Elevator will cover
    private int minZone;
    private int maxZone;
    
    private int currFloor;
    
    //max floor Elevator will need to go to (based on its Passengers)
    private int maxFloor;
    
    //true if Elevator is going down
    private boolean returning;
    
    //private ArrayList<Integer> floorsRemaining; //weird stuff here and below
    //private ArrayList<Integer> allFloors;

    //number of unique floors Elevator will need to go to (based on its Passengers)
    private int numFloors;
    //number of Passengers in riders
    private int numPassengers;

    public Elevator() {
	riders = new ArrayPrioirtyQueue<Integer>();
	timeToEnd = 0;
	available = true;
	currFloor = 0;
	returning = false;
	numFloors = 0;
	numPassengers = 0;
    }

    //overloaded constructor for when passengers are already known
    public Elevator(ArrayList<Integer> passengers){
	this();
        for (Integer i: passengers)
            riders.add(i); //built by control tower
    }
         
    public void setRange(int setMinZone, int setMaxZone) {
	minZone = setMinZone;
	maxZone = setMaxZone;
    }
    public void setRange(int setMaxZone) {
	maxZone = setMaxZone;
    }	
    public int getMinFloor(){
	return minZone;
    }
    public int getMaxFloor(){
	return maxZone;
    }
         
    //public ArrayList<Integer> findFloors

    //calulates how much time it will take Elevator to reach ground floor
    public int calcTime(){
        if (returning)
            timeToEnd =  currFloor * 4; //4 sec for every floor
        else
            timeToEnd = ((maxFloor - currFloor) * 4) + //4 sec for every floor until it gets to maxFloor
		(maxFloor * 4) //4 sec for every floor it has to gone down from maxFloor to ground
		+ numPassengers //1 sec for every Passenger getting off their floor	
		return timetoEnd;
    }
            
    public String toString(){
        return "Time it will take: " + timeToEnd + "Floors that will be visited this trip: " + allFloors;
    }
    
    public Integer add(Integer a){
        riders.add(a);
	return a;
	
    }
    
    public void remove(){
        currFloor = riders.peekMin();
        while (riders.peekMin() == currFloor)
            riders.removeMin();
    }

    public boolean isEmpty() {
	return numPassengers == 0;
    }
        
 
    //move method??
           
}//end class Elevator
