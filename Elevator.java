import java.util.*;
import java.lang.*;
public class Elevator{
    
    ArrayPriorityQueue<Passenger> riders;
    private int timeToEnd;
    public boolean available;
    //ints for min and max floors Elevator will cover
    private int minZone;
    private int maxZone;
    
    private int currFloor;
    
    //max floor Elevator will need to go to (based on its Passengers)
    private int maxFloor;
    
    //true if Elevator is going down
    private boolean returning;
    
    //number of unique floors Elevator will need to go to
    private int numFloors;

    //true if Elevator is moving
    private boolean moving;

    public Elevator() {
	riders = new ArrayPriorityQueue<Passenger>();
	timeToEnd = 0;
	available = true;
	currFloor = 0;
	returning = false;
	numFloors = 0;
	moving = false;
    }
    /* Test to make sure assignRanges() works for unavailable Elevators too
    public Elevator(boolean test) {
	this();
	available = false;
    }
    */

    //overloaded constructor for when passengers are already known
    public Elevator(ArrayList<Passenger> passengers){
	this();
        for (Passenger i: passengers)
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
            timeToEnd = currFloor * 4; //4 sec for every floor
        else {
            timeToEnd = ((maxFloor - currFloor) * 3) + //3 sec for every floor until it gets to maxFloor
		(maxFloor * 3) + //3 sec for every floor it has to gone down from maxFloor to ground
		(riders.size()) + //1 sec for every Passenger getting off their floor
		(numFloors * 4); //4 sec for every floor Elevator has to stop at
	}
	return timeToEnd;
    }
            

    //add Passenger to riders, and mantain maxFloor, and numFloors
    public Passenger add (Passenger a, int time) {
	//if Elevator is not full, add Passenger
        if (a.getDestination() > maxFloor) {
	    maxFloor = a.getDestination();
	}
	if (!(riders.contains(a))) {
	    numFloors++;
	}
	riders.add(a);
	//if Elevator is now full, mark it unavailable, and record waitTime of Passengers
	if (isFull()) {
	    available = false;
	    for (Passenger i : riders.getData()) {
		i.setWaitTime(time);
	    }
	    moving = true;
	    //return null to show that a new assignRanges() is needed
	    return null;
	}
	return a;
    }
		
    public void remove(){
        currFloor = riders.peekMin().getDestination();
        while (riders.peekMin().getDestination() == currFloor){
            riders.removeMin();
	}
    }

    public boolean isEmpty() {
	return riders.size() == 0;
    }

    public boolean isFull() {
	return riders.size() == 10;
    }
        
    public String toString(){
        String rtn = "";
	rtn += "Number of people: " + riders.size() + "\n";
	rtn += "First floor: " + minZone + "\n";
	rtn += "Last floor: " + maxZone + "\n";
	rtn += "Time until end: " + timeToEnd;
	
	return rtn;
    }
 
    //move method??
           
}//end class Elevator
