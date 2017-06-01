import java.util.*;
import java.lang.*;
public class Elevator{
    
    private ArrayPriorityQueue<Passenger> riders;
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

    //time that Elevator starts moving
    private int moveTime;

    public Elevator() {
	riders = new ArrayPriorityQueue<Passenger>();
	timeToEnd = 0;
	available = true;
	currFloor = 0;
	returning = false;
	numFloors = 0;
	moving = false;
    }

    //overloaded constructor for when passengers are already known
    public Elevator(ArrayList<Passenger> passengers){
	this();
        for (Passenger i: passengers)
            riders.add(i); //built by control tower
    }
    public void setMoveTime(int setMoveTime){
	moveTime=setMoveTime;
    }
    public int getMoveTime(){
	return moveTime;
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

    public ArrayPriorityQueue<Passenger> getRiders() {
	return riders;
    }

    //calulates how much time it will take Elevator to reach ground floor
    public int calcTime(){
        if (returning)
            timeToEnd = currFloor * 3; //3 sec for every floor
        else {
            timeToEnd = ((maxFloor - currFloor) * 3) + //3 sec for every floor until it gets to maxFloor
		((maxFloor) * 3) + //3 sec for every floor it has to gone down from maxFloor to ground
		(riders.size()) + //1 sec for every Passenger getting off their floor
		(numFloors * 4); //4 sec for every floor Elevator has to stop at
	}
	return timeToEnd;
    }
            

    //add Passenger to riders, and mantain maxFloor, and numFloors
    public Passenger add (Passenger a, int time) {

    	//if Elevator is full, return null
	//returning null will tell addPassenger() to add Passenger a to leftover
	if (isFull()) {
	    return null;
	}
	
        //if Elevator is not full, add Passenger with its corresponding waitTime
	//update Elevator's maxFloor, and numFloors if needed
	else{
	    if (a.getDestination() > maxFloor) {
		maxFloor = a.getDestination();
	    }
	    if (!(riders.contains(a))) {
		numFloors++;
	    }
	    a.setWaitTime(time);
	    riders.add(a);
	}
	return a;
    }
    
    public void empty(){ //empty an elevator. Do this when elevator return (all passengers have already exited in 'real' life
        while (riders.size()!=0){
            riders.removeMin();
        }
    }
    
    public int timeForPassenger(Passenger a){
        int passengersBefore=0;
        ArrayPriorityQueue<Integer> floorsBefore = new ArrayPriorityQueue<Integer>();
        int passengersAtFloor=0;
        for (int i=0; i<riders.size(); i++){
            if (a.compareTo(riders.get(i)) > 0){
                passengersBefore += 1;
                if (!floorsBefore.contains(riders.get(i).getDestination())){
                    floorsBefore.add(riders.get(i).getDestination());
                }
            }
            else if (a.compareTo(riders.get(i)) == 0){
                passengersAtFloor += 1;
            }
        }
        int total = ((a.getDestination()-1) * 3) + //3 sec for every floor until it gets to maxFloor
		(passengersAtFloor/2) + //1 sec for every Passenger getting off their floor. Take the average time as approx
		(floorsBefore.size() * 4) + //4 sec for every floor Elevator has to stop a
        passengersBefore; //1 sec for passengers to get off
        a.setTravelTime(total);
        return total;
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
