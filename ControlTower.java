import java.util.*;

public class ControlTower {
    
    ArrayList<Integer> people;
    ArrayList<Elevator> ellies;
    int min;
    int max;

    public ControlTower() {
	people = new ArrayList<Integer>();
	for (int i = 0; i < 40; i++) {
	    people.add((int)(Math.random() * 34)+1);
	}
	ellies = new ArrayList<Elevator>();
	for (int i = 0; i < 7; i++){
	    ellies.add(new Elevator());
	}
	min = Collections.min(people);
	max = Collections.max(people);   
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
    //will eventually be relocated to Passenger class
    //also add option for overflow of Elevator
    public void addPassenger() {
	Integer toAdd = people.remove(0);
	for (Elevator i : ellies) {
	    if ((toAdd >= i.getMinFloor()) && (toAdd <= i.getMaxFloor())) {
		i.add(toAdd);
		return;
	    }
	}
    }
		    
	
	
	
    
    
    public static void main(String[] args){
        ControlTower please = new ControlTower();
        please.assignRanges();
	System.out.println("After assigning ranges...");
        for (int i=0; i<please.ellies.size(); i++) {
            System.out.println(please.ellies.get(i));
	}

	System.out.println("\n\nAfter adding Passengers");
	//keep a constant, otherwise this will not work
	int a = please.people.size();
	for (int i = 0; i < a; i++) {
	    please.addPassenger();
	}
	    
	for (int i=0; i<please.ellies.size(); i++) {
	    System.out.println(please.ellies.get(i));
	}
    }
    
}//end class ControlTower

				       

    

	
