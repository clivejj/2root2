public class Passenger implements Comparable<Passenger> {

    public int destination;
    public int birthTime;
    public int waitTime;
    public int dropOffTime;

    public Passenger(int setBirthTime) {
	destination =(int)(Math.random() * 34 + 1);
	birthTime = setBirthTime;
    }

    //overloaded constructor when destination is already known
    public Passenger(int setBirthTime, int setDestination) {
	this(setBirthTime);
	destination = setDestination;
    }

    public int getDestination() {
	return destination;
    }

    public int compareTo(Passenger a) {
	return a.getDestination() - this.getDestination();
    }

    public String toString() {
	return "" +  destination;
    }

    public void setWaitTime(int setWaitTime) {
	waitTime = setWaitTime;
    }
}
    
    
	
	    
