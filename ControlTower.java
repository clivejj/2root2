import java.util.*;

public class ControlTower {
    
    ArrayList<Passenger> people;
    //contains the Passengers that were not placed into an Elevator during the previous wave
    ArrayList<Passenger> leftover;
    ArrayList<Elevator> ellies;
    //min and max of the destination of Passengers in people
    int min;
    int max;
    
    //how many floors are in the building
    int maxFloor;
    
    //how many people are in each wave
    int numPeople;

    //tracks time of system, will constantly be incrememented
    int time;
    

    public ControlTower(int setMaxFloor, int numElevators, int setNumPeople) {
	people = new ArrayList<Passenger>();
	leftover = new ArrayList<Passenger>();
	//populate ellies with numElevators Elevators
	ellies = new ArrayList<Elevator>();
	for (int i = 0; i < numElevators; i++){
	    //Test to make sure assignRanges() works for unavailable Elevators too
	    //if (i == 3) {
	    //	ellies.add(new Elevator(false));
	    //}
	    // else {
	    ellies.add(new Elevator());
	    // }
	}
	//set preliminary valued for min, and max (to be changed)
	min = setMaxFloor;
	max = 0;
	maxFloor = setMaxFloor;
	numPeople = setNumPeople;
	time = 0;
    }
    
    public int getTime() {
	return time;
    }

    //returns ArrayList containing the indices of the Elevators in ellies that are available
    public ArrayList<Integer> indexOfAvailElevators() {
	ArrayList<Integer>indexOfAvailElevators = new ArrayList<Integer>();
	for (int i = 0; i < ellies.size(); i++) {
	    if (ellies.get(i).available) {
		indexOfAvailElevators.add(i);
	    }
	}
	return indexOfAvailElevators;
    }

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
	    int prevEl=0;
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
	    if ((toAdd.getDestination() >= ellies.get(i).getMinFloor()) &&
		(toAdd.getDestination() <= ellies.get(i).getMaxFloor())) {
		//if Passenger cannot be added to Elevator because it is full, add it to leftover
		if (ellies.get(i).add(toAdd, getTime()) == null) {
		    leftover.add(toAdd);
		}   
		return;
	    }
	}
    }//end addPassenger()
    
    //add all Passengers in people
    public void addAllPassengers() {
	int a = people.size();
	for (int i = 0; i < a; i++) {
	    addPassenger();
	}
    }//end addAllPassenger()

    //calculate timeToEnd for all Elevators in ellie
    public void calculateAllTimes() {
	for (Elevator i : ellies) {
	    i.calcTime();
	}
    }//end calculateAllTime()

    //creates new wave of Passengers
    //places Passengers into assigned Elevators, calculates times
    public void newWave() {
	for (int i = 0; i < numPeople; i++) {
	    int dest = (int)(Math.random() * maxFloor) + 1;
	    if (dest > max) {
		max = dest;
	    }
	    if (dest < min) {
		min = dest;
	    }
	    people.add(new Passenger(getTime(), dest));
	}
	//System.out.println(people);
	assignRanges();
	addAllPassengers();
	calculateAllTimes();
	//add Passengers from leftover back to people
	int a = leftover.size();
	for (int i = 0; i < a; i++) {
	    people.add(leftover.remove(0));
	}
    }//end newWave()
    
    //overridden 
    public String toString() {
	String rtn = "";
	for (int i=0; i<ellies.size(); i++){
	    rtn += "-------------ELEVATOR" + i + "------------\n";
	    rtn += ellies.get(i) + "\n";
	}
	return rtn;
    }
    
	
    public static void main(String[] args){
        ControlTower please = new ControlTower(40, 7, 60);
        //please.assignRanges();
	//System.out.println("After assigning ranges...");
	// System.out.println(please);

	//	please.addAllPassenger();
	//System.out.println("After adding Passengers...");
	//System.out.println(please);

	//please.calculateAllTime();
	//System.out.println("After calculating time...");
	//System.out.println(please);
		
	/*please.assignRanges();
	System.out.println("After assigning ranges...");
	System.out.println(please);

	please.addAllPassenger();
	System.out.println("After adding Passengers...");
	System.out.println(please);

	please.calculateAllTime();
	System.out.println("After calculating time...");
	System.out.println(please);*/

	please.newWave();

	System.out.println(please);
	for (Elevator gjoa : please.ellies){    
        /*for (int i=0; i<gjoa.riders.size(); i++){
            System.out.print("Target floor:  ");
            System.out.print(gjoa.riders.get(i).getDestination());
            System.out.print("   Time: "); 
            System.out.println(gjoa.timeForPassenger(gjoa.riders.get(i)));*/
	    System.out.println(gjoa.riders);
	}
	//System.out.println(please.people);
    }
    
}//end class ControlTower

				       

    

	
