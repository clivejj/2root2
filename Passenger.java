public class Passenger implements Comparable<Passenger> {

    public int destination;
    public int birthTime;
    public int waitTime;
    public int dropOffTime;
    public int travelTime;
    public int totalTime;

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

    public void setWaitTime(int setWaitTime) {
	waitTime = setWaitTime;
    }
    
    //public void setEndWaitTime(int setEndWaitTime) {
    //	endWaitTime = setEndWaitTime;
    //}

    public void setTravelTime(int setTravelTime){
    travelTime = setTravelTime;
    }
    
    public int compareTo(Passenger a) {
	return this.getDestination() - a.getDestination();
    }

    public String toString() {
	return "" +  destination;
    }
    
    public int setTotalTime(){
        totalTime=waitTime-birthTime+travelTime;
	return totalTime;
    }

}
    
    
	
	    
