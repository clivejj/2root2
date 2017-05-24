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
    }

    public assignMinMax() {
	min = people.min();
    

    

	
