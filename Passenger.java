public class Passenger {

    public int destination;
    public int birthTime;
    public int waitTime;
    public int dropOffTime;

    public Passenger(ControlTower a) {
	destination =(int)Math.random() * 34 + 1);
	birthTime = a.getTime();
    }
	
    public Passenger(int setDestination, ControlTower a) {
	this(a);
	destination = setDestination;
    }
}
    
    
	
	    
