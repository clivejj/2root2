import java.util.*;

public class ControlTower {
    
    ArrayList<Passenger> people;
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
    }
    
    public int getTime() {
	return time;
    }

    //assigns ranges to Elevator based on range of floors, and number of Elevators
    public void assignRanges() {
    	double floorsPerEl = (max-min+1.0)/ellies.size(); //need 1.0 to make it a double
	if ((int)floorsPerEl == floorsPerEl){    //if floors are divisible by num of elevators   
	    for (int i = 0; i < ellies.size(); i++){
		if (i==0)
		    ellies.get(i).setRange(min,min+(int)floorsPerEl-1);
		else{
		    int prevEl = ellies.get(i-1).getMaxFloor();
		    ellies.get(i).setRange(prevEl+1, prevEl+(int)floorsPerEl);
		}
	    }
	}
	else{
	    int more = (int)(floorsPerEl+1);
	    int prevEl=0;
	    for (int i = 0; i < ellies.size(); i++){
		if (i==0)
		    ellies.get(i).setRange(prevEl+1,prevEl+more);
		else{
		    prevEl = ellies.get(i-1).getMaxFloor();
		    ellies.get(i).setRange(prevEl+1, prevEl+more);
		}
		if (more!=(int)floorsPerEl && ( (max-(prevEl+more)) % (ellies.size()-i-1.0) ) == 0){
		    //if the number of elevators not assigned an elevator divides ellies remaining evenly, return to normal ellie range assignment
		    more= (int)( (max-(prevEl+more)) / (ellies.size()-i-1.0) );
		}
	    }
	}
    }//end assignRanges()

    //adds Passenger to Elevator with corresponding range
    //also eventually add option for overflow of Elevator
    public void addPassenger() {
	Passenger toAdd = people.remove(0);
	for (Elevator i : ellies) {
	    if ((toAdd.getDestination() >= i.getMinFloor()) && (toAdd.getDestination() <= i.getMaxFloor())) {
		i.add(toAdd);
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
	
    //overridden 
    public String toString() {
	String rtn = "";
	for (int i=0; i<ellies.size(); i++){
	    rtn += "-------------ELEVATOR" + i + "------------\n";
	    rtn += ellies.get(i) + "\n";
	}
	return rtn;
    }

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
	assignRanges();
	addAllPassengers();
	calculateAllTimes();
    }//end newWave()
	
		    
	
    public static void main(String[] args){
        ControlTower please = new ControlTower(34, 7, 40);
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

	for (Elevator i : please.ellies) {
	    System.out.println(i);
	}

    }
    
}//end class ControlTower

				       

    

	
