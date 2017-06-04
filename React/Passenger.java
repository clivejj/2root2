public class Passenger implements Comparable<Passenger> {

    public int destination;
    public int birthTime;
    public int waitTime;
    public int travelTime;
    public int totalTime;

    //constructor when destination is already known
    public Passenger(int setBirthTime, int setDestination) {
	birthTime = setBirthTime;
	destination = setDestination;
    }
    
 /**********************ACCESSORS**********************/
    public int getDestination() {
	return destination;
    }
    
    public int getBirthTime() {
	return birthTime;
    }

    public int getWaitTime() {
	return waitTime;
    }
    
    public int getTravelTime() {
	return travelTime;
    }

    public int getTotalTime() {
	return totalTime;
    }
    /**********************END ACCESSORS**********************/

    
    /**********************MUTATORS**********************/
    public void setWaitTime(int setWaitTime) {
	waitTime = setWaitTime;
    }
    

    public void setTravelTime(int setTravelTime){
	travelTime = setTravelTime;
    }

    public int setTotalTime(){
        totalTime=waitTime+travelTime;
	return totalTime;
    }
    /**********************MUTATORS**********************/
    
    public int compareTo(Passenger a) {
	return this.getDestination() - a.getDestination();
    }

    public String toString() {
	return destination + "," + waitTime + "," + travelTime + "," + totalTime;
    }
    

}
    
    
	
	    
