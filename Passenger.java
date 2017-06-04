public class Passenger implements Comparable<Passenger> {

    public int destination;
    public int birthTime;
    public int waitTime;
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

    public int getTotalTime() {
	return totalTime;
    }

    public int getTravelTime() {
	return travelTime;
    }

    public void setWaitTime(int setWaitTime) {
	waitTime = setWaitTime;
    }
    
    public int getWaitTime() {
	return waitTime;
    }

    public int getBirthTime() {
	return birthTime;
    }

    public void setTravelTime(int setTravelTime){
	travelTime = setTravelTime;
    }
    
    public int compareTo(Passenger a) {
	return this.getDestination() - a.getDestination();
    }

    public String toString() {
	return destination + "," + waitTime + "," + travelTime + "," + totalTime;
    }
    
    public int setTotalTime(){
        totalTime=waitTime+travelTime;
	return totalTime;
    }

}
    
    
	
	    
