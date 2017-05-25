import java.util.*;

public class ControlTower {
    
    ArrayList<Integer> people;
    ArrayList<Elevator> ellies;
    int min;
    int max;

    public ControlTower() {
	people = new ArrayList<Integer>();
	for (int i = 0; i < 40; i++) {
	    people.add((int)(Math.random() * 11)+1);
	}
	ellies = new ArrayList<Elevator>();
	for (int i = 0; i < 5; i++){
	    ellies.add(new Elevator());
	}
    min = Collections.min(people);
	max = Collections.max(people);
    }

    public void assignRanges() {
    	double floorsPerEl = (max-min)/ellies.size(); 
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
	    for (int i = 0; i < ellies.size(); i++){
	    if (i==0)
		ellies.get(i).setRange(min,min+more-1);
	    else{
		int prevEl = ellies.get(i-1).getMaxFloor();
		ellies.get(i).setRange(prevEl+1, prevEl+more);
		if ((int)(max-(prevEl+more)/(ellies.size()-i-1)) == max-(prevEl+more)/(ellies.size()-i-1) && more!=(int)floorsPerEl){
			more-=1;
		}	
	    }
	}
	}
    }
    public static void main(String[] args){
        ControlTower please = new ControlTower();
        please.assignRanges();
        for (int i=0; i<please.ellies.size(); i++)
            System.out.println(please.ellies.get(i));
        
    }
}

				       

    

	
