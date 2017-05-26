import java.util.*;

public class ControlTower {
    
    ArrayList<Passenger> people;
    ArrayList<Elevator> ellies;
    int min;
    int max;
    int maxFloor;
    int time;
    int numPeople;
    

    public ControlTower(int maxFloor, int numElevators, int numPeople) {
	maxFloor=maxFloor;
	time = 0;
	ellies = new ArrayList<Elevator>();
	for (int i = 0; i < numElevators; i++){
	    ellies.add(new Elevator());
	}
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
    public void addAllPassenger() {
	int a = people.size();
	for (int i = 0; i < a; i++) {
	    addPassenger();
	}
    }//end addAllPassenger()

    //calculate timeToEnd for all Elevators in ellie
    public void calculateAllTime() {
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

	while (true) {
	    please.people = new ArrayList<Passenger>();
	    for (int i = 0; i < please.numPeople; i++) {
		int dest = (int)(Math.random() * please.maxFloor) + 1;
		please.people.add(new Passenger(dest, please));
	    }
	    //please.min = Collections.min(please.people);
	    //please.max = Collections.max(please.people);
	}
    }
    
}//end class ControlTower

				       

    

	
