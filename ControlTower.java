import java.util.*;
import java.io.*;
import java.lang.*;

public class ControlTower {
    //contains Passengers waiting to be placed into Elevators
    ArrayList<Passenger> people;
    //contains the Passengers that were not placed into an Elevator during the previous wave
    ArrayList<Passenger> leftover;
    //contains all Elevators
    ArrayList<Elevator> ellies;
    //contains every single Passenger after they have completed their journey
    //used for storing data to write in csv
    ArrayList<Passenger> data;
    //min and max of the destination of Passengers in people
    int min;
    int max;
    // ArrayList<Integer> floors;
    
    //how many floors are in the building
    int maxFloor;
    
    //how many people are in each wave
    int numPeople;

    //tracks time of system, will constantly be incrememented
    int time;

    int waitEl;

    int meanTime;
    int medianTime;
    

    public ControlTower(int setMaxFloor, int numElevators, int setNumPeople, int setWaitEl) {
	people = new ArrayList<Passenger>();
	leftover = new ArrayList<Passenger>();
	data = new ArrayList<Passenger>();
	//	floors = new ArrayList<Integer>();
	
	//populate ellies with numElevators Elevators
	ellies = new ArrayList<Elevator>();
	for (int i = 0; i < numElevators; i++){
	    ellies.add(new Elevator());
	}
	//set preliminary valued for min, and max (to be changed)
	min = setMaxFloor;
	max = 0;
	
	maxFloor = setMaxFloor;
	numPeople = setNumPeople;
	time = 0;
	meanTime = 0;
	medianTime = 0;

	waitEl = setWaitEl;
    }//end ControlTower()

    
    public int getTime() {
	return time;
    }

    
    //helper method
    //returns ArrayList containing the indices of the Elevators in ellies that are available
    public ArrayList<Integer> indexOfAvailElevators() {
	ArrayList<Integer>indexOfAvailElevators = new ArrayList<Integer>();
	for (int i = 0; i < ellies.size(); i++) {
	    if (ellies.get(i).getAvailable()) {
		indexOfAvailElevators.add(i);
	    }
	}
	return indexOfAvailElevators;
    }//end indexOfAvailElevators()

    
    //assigns ranges to Elevator based on range of floors, and number of Elevators that are available
    public void assignRanges() {
	//ArrayList contains the indices of the Elevators in ellies that are available
	ArrayList<Integer>indexOfAvailElevators = indexOfAvailElevators();
	
    	double floorsPerEl = (max-min+1.0)/indexOfAvailElevators.size(); //need 1.0 to make it a double
	if ((int)floorsPerEl == floorsPerEl){    //if floors are divisible by num of elevators   
	    for (int i = 0; i < indexOfAvailElevators.size(); i++){
		if (i==0)
		    ellies.get(indexOfAvailElevators.get(i)).setRange(min,min+(int)floorsPerEl-1);
		else{
		    int prevEl = ellies.get(indexOfAvailElevators.get(i-1)).getMaxFloor();
		    ellies.get(indexOfAvailElevators.get(i)).setRange(prevEl+1, prevEl+(int)floorsPerEl);
		}
	    }
	}
	else{
	    int more = (int)(floorsPerEl+1);
	    int prevEl = min - 1;
	    for (int i = 0; i < indexOfAvailElevators.size(); i++){
		if (i==0)
		    ellies.get(indexOfAvailElevators.get(i)).setRange(prevEl+1,prevEl+more);
		else{
		    prevEl = ellies.get(indexOfAvailElevators.get(i-1)).getMaxFloor();
		    ellies.get(indexOfAvailElevators.get(i)).setRange(prevEl+1, prevEl+more);
		}
		if (more!=(int)floorsPerEl && ( (max-(prevEl+more)) % (indexOfAvailElevators.size()-i-1.0) ) == 0){
		    //if the number of elevators not assigned an elevator divides ellies remaining evenly, return to normal ellie range assignment
		    more= (int)( (max-(prevEl+more)) / (ellies.size()-i-1.0) );
		}
	    }
	}
    }//end assignRanges()

    
    //adds Passenger to Elevator with corresponding range
    //if Elevator with correct floor range is already full, add Passenger back to people
    public void addPassenger() {
	Passenger toAdd = people.remove(0);
	//ArrayList containing the indices of the Elevators in ellies that are available
	ArrayList<Integer>indexOfAvailElevators = indexOfAvailElevators();
	for (Integer i : indexOfAvailElevators) {
	    //if Passenger's destination is within range of Elevator, add it
	    if ((toAdd.getDestination() >= ellies.get(i).getMinZone()) &&
		(toAdd.getDestination() <= ellies.get(i).getMaxZone())) {
		//if Passenger cannot be added to Elevator because it is full, add it to leftover
		if (ellies.get(i).add(toAdd, getTime()) == null) {
		    leftover.add(toAdd);
		}
		/*else {
		  if (!(floors.contains(toAdd.getDestination()))) {
		  floors.add(toAdd.getDestination());
		  }
		  }*/
		return;
	    }
	}
    }//end addPassenger()

    
    //add all Passengers in people to Elevators
    public void addAllPassengers() {
	int a = people.size();
	for (int i = 0; i < a; i++) {
	    addPassenger();
	}
	for (Integer i : indexOfAvailElevators()) {
	    ellies.get(i).setMoveTime(getTime());
	}
    }//end addAllPassenger()

    
    //calculate timeToEnd for all available Elevators
    public void calculateAllElliesTimes() {
	ArrayList<Integer> a = indexOfAvailElevators();
	for (Integer i : a) {
	    ellies.get(i).calcTime();
	}
    }//end calculateAllTime()

    
    //calculate travelTime for all riders in available Elevators
    public void calculateAllRidersTimes() {
	ArrayList<Integer> a = indexOfAvailElevators();
	for (Integer i : a) {
	    for (int p = 0; p < ellies.get(i).getRiders().size(); p++ ) {
		ellies.get(i).timeForPassenger(ellies.get(i).getRiders().get(p));
	    }
	}
    }//end calculateAllRidersTime

    //creates new wave of Passengers, adds them to people
    //also adds Passengers from leftover to people
    public void newWave() {
	//adds Passengers from leftover, updates min and max if needed
	while (!(leftover.isEmpty())) {
	    int dest = leftover.get(0).getDestination();
	    if (dest > max) {
		max = dest;
	    }
	    if (dest < min) {
		min = dest;
	    }
	    people.add(leftover.remove(0));
	}
	
	//adds new wave of Pasengers, updates min and max if needed    
	for (int i = 0; i < numPeople; i++) {
	    int dest = (int)(Math.random() * maxFloor) + 2;
	    if (dest > max) {
		max = dest;
	    }
	    if (dest < min) {
		min = dest;
	    }
	    people.add(new Passenger(getTime(), dest));
	}
    }//end newWave()

    
    //empty an elevator an adds its Passengers to data
    public void empty(Elevator a){ 
	while (!(a.isEmpty())) {
	    data.add(a.getRiders().removeMin());
        }
    }

    
    //runs the Elevator by sending waves of Passengers at the Elevators
    //tells Elevators to assignRanges()
    //keeps track of Elevator's movements thru time variable
    public void loopy(int timeToEnd) {
	//sets the time for the next wave
	int nextWaveTime = 0;
	
	//keep running until time has reached timeToEnd
	while (time < timeToEnd) {
	    
	    //declare appropriate Elevators available and empty() out their Passengers
	    for (int i = 0; i < ellies.size(); i++) {
		if (getTime()-ellies.get(i).getMoveTime() == ellies.get(i).calcTime()){
		    ellies.get(i).setAvailable(true);
		    empty(ellies.get(i));
		    ellies.set(i, new Elevator());
		}
	    }

	    //it is time for a new wave
	    if (time == nextWaveTime) {
		//create a new wave and assign a nextWaveTime in range [200, 299)
		newWave();
		nextWaveTime += (int) (100 + Math.random() * 100);

		//for looping thru available Elevators
		ArrayList<Integer> a = indexOfAvailElevators();
		//if there are more than 4 available Elevators...
		if (a.size() >= waitEl) {
		    //assignRanges to available Elevators
		    assignRanges();
		    //add all Passengers to available Elevators
		    addAllPassengers();
		    //calculate timeToEnd for available Elevators, travelTime for Passengers
		    calculateAllElliesTimes();
		    calculateAllRidersTimes();
		    //mark Elevators as unavailable
		    for (Integer i : a) {
			if (!(ellies.get(i).isEmpty())) {
			    ellies.get(i).setAvailable(false);
			}
		    }
		    //System.out.println("--------------NEW WAVE @ TIME: " + getTime() + "--------------");
		    //System.out.println("min: " + min + " max: " + max);
		    //System.out.println(this);
		}
	    }
	    //increment time
	    time++;
	}
    }//end loopy()

    
    //writes Passenger info from data into csv file
    //code from stack overflow
    //writes Passenger info from data into csv file
    //code from stack overflow
    public int writeData() {
	try {
	    FileWriter a  = new FileWriter("./data/log" + waitEl + ".csv", false);
	    BufferedWriter writer = new BufferedWriter(a);
	    writer.write("Destination,Wait Time,Travel Time,Total Time\n");
	    for (Passenger i : data) {
		writer.write(i.toString() + "\n");
	    }
	    writer.flush();
	    writer.close();
	}
	catch (Exception e) {
	    return -1;
	}
	
	//store summary statistcs in a new file, stat.csv
	try {
	    FileWriter b = new FileWriter("./data/stat" + waitEl + ".csv", false);
	    BufferedWriter writers = new BufferedWriter(b);
	    writers.write("Destination,Frequency," + 
			  "Mean Wait Time,Median Wait Time," +
			  "Mean Travel Time,Median Travel Time," +
			  "Mean Total Time,Median TotalTime\n");
	    
	    //summary stats for every Passenger sorted by destination floor
	    for (int floor = 2; floor <= maxFloor; floor++) {
		int counter = 0;
		
		//find mean wait and meanTravel
		int meanWait = 0;
		int meanTravel = 0;
		int meanTotal = 0;
		//for help finding Median
		ArrayPriorityQueue<Integer> waitData = new ArrayPriorityQueue<Integer>();
		ArrayPriorityQueue<Integer> travelData = new ArrayPriorityQueue<Integer>();
		ArrayPriorityQueue<Integer> totalData = new ArrayPriorityQueue<Integer>();
		int medWait = 0;
		int medTravel = 0;
		int medTotal = 0;
		for (Passenger p : data) {
		    if (p.getDestination() == floor) {
			meanWait += p.getWaitTime();
			meanTravel += p.getTravelTime();
			waitData.add(p.getWaitTime());
			travelData.add(p.getTravelTime());
			totalData.add(p.getTotalTime());
			counter++;
		    }
		}
		if (counter != 0) {
		    meanWait = (int) (meanWait / counter);
		    meanTravel = (int) (meanTravel / counter);
		    meanTotal = meanWait + meanTravel;
		    medWait = waitData.get(counter / 2);
		    medTravel = travelData.get(counter / 2);
		    medTotal = totalData.get(counter / 2);
		    writers.write(floor + "," + counter + "," +  meanWait + "," + medWait + "," +
				  meanTravel + "," + medTravel + "," +
				  meanTotal + "," + medTotal + "\n");
		    writers.flush();
		}
	    }	    
	    //summary statistics for every Passenger
	    int meanWait = 0;
	    int meanTravel = 0;
	    int meanTotal = 0;
	    //for help finding Median
	    ArrayPriorityQueue<Integer> waitData = new ArrayPriorityQueue<Integer>();
	    ArrayPriorityQueue<Integer> travelData = new ArrayPriorityQueue<Integer>();
	    ArrayPriorityQueue<Integer> totalData = new ArrayPriorityQueue<Integer>();
	    int medWait = 0;
	    int medTravel = 0;
	    int medTotal = 0;
	    
	    for (Passenger p : data) {
		meanWait += p.getWaitTime();
		meanTravel += p.getTravelTime();
		waitData.add(p.getWaitTime());
		travelData.add(p.getTravelTime());
		totalData.add(p.getTotalTime());
	    }
	    meanWait = (int) (meanWait / data.size());
	    meanTravel = (int) (meanTravel / data.size());
	    meanTotal = meanWait + meanTravel;
	    medWait = waitData.get(data.size() / 2);
	    medTravel =  travelData.get(data.size() / 2);
	    medTotal =  totalData.get(data.size() / 2);

	    //store mean and median times
	    meanTime = meanTotal;
	    medianTime = medTotal;
	    
	    writers.write("ALL FLOORS" + "," + data.size() + "," +
			  meanWait + "," + medWait + "," +
			  meanTravel + "," + medTravel + "," + 
			  meanTotal + "," + medTotal + "\n");
   
	    //flush and close
	    writers.flush();
	    writers.close();
	}
	catch (Exception e) {
	    return -1;
	}

	return 0;
    }//end writeData()

    
    //overridden toString()
    public String toString() {
	String rtn = "";
	for (int i=0; i<ellies.size(); i++){
	    rtn += "-------------ELEVATOR" + i + "------------\n";
	    rtn += ellies.get(i) + "\n";
	}
	return rtn;
    }//end toString()

    
    public static void main(String[] args){
	ControlTower please = new ControlTower(50, 10, 50, 1);
	please.loopy(10000);
	please.writeData();

	
	//maxfloor, numellies, ppl, waittillthismanyellies
	ControlTower dummy = new ControlTower(50, 20, 3, 4);
	dummy.loopy(10000);
	//System.out.println(dummy.data.size());
	//System.out.println(dummy.data);
	dummy.writeData();
	

	
    }//end main()

}//end class ControlTower

				       

    

	
