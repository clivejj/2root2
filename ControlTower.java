import java.util.*;

public class ControlTower {
    
    ArrayList<Integer> people;
    ArrayList<Elevator> ellies;
    int min;
    int max;

    public ControlTower() {
	people = new ArrayList<Integer>();
	for (int i = 0; i < 40; i++) {
	    people.add((int)(Math.random() * 11));
	}
	ellies = new ArrayList<Elevator>();
	for (int i = 0; i < 5; i++){
            Elevator i = new Elevator();
	    ellies.add(i);
	}
    }

    public assignMinMax() {
	min = Collections.min(people);
	max = Collections.max(people);
    }
	
    public void assignRanges() {
    	double floorsPerEl = (max-min)/ellies.getSize(); 
	if ((int)floorsPerEl == floorsPerEl){    //if floors are divisible by num of elevators
	for (int i = 0; i < ellies.getSize(); i++){
	    if (i==0)
		ellies.get(i).setRange(min,min+floorsPerEl-1);
	    else{
		int prevEl = ellies.get(i-1).getMaxFloor();
		ellies.get(i).setRange(prevEl+1, prevEl+floorsPerEl);
	    }
	}
	}
	else{
	    int more = (int)(floorsPerEl+1);
	    for (int i = 0; i < ellies.getSize(); i++){
	    if (i==0)
		ellies.get(i).setRange(min,min+more-1);
	    else{
		int prevEl = ellies.get(i-1).getMaxFloor();
		ellies.get(i).setRange(prevEl+1, prevEl+more);
		if ((int)(max-(prevEl+more)/(ellies.getSize()-i-1) == max-(prevEl+more)/(ellies.getSize()-i-1) && more!=(int)floorsPerEl){
			more-=1;
		}	
	    }
	}
	}
    }

				       

    

	
