import java.util.*;
     public class Elevator{
        private ArrayPriorityQueue<Integer> riders;
        private int timeToEnd=0;
        //private boolean available=true
        //private String range;
        private int currFloor=1;
        private int maxFloor;
        private boolean returning=false;
        private ArrayList<Integer> floorsRemaining; //weird stuff here and below
        private ArrayList<Integer> allFloors;
         
    public Elevator(ArrayList<Integer> passengers){
        for (Integer i: passengers)
            riders.add(i); //built by ontrol tower
    }
         
    public void setRange(String newRange){
        range = newRange; //to be done by control tower
    }
         
    public ArrayList<Integer> findFloors
    
    public void calcTime(){
        if (returning)
            timeToEnd = maxFloor * 4; //4 sec for every floor
        else
            timeToEnd = floorsRemaining.getSize() * 4 + maxFloor * 4;
        return timetoEnd;
    }
            
    public String toString(){
        return "Time it will take: " + timeToEnd + "Floors that will be visited this trip: " + allFloors;
    }
    public void add(Integer a){
        riders.add(a)
    }
    public void remove(){
        currFloor = riders.peekMin();
        while (riders.peekMin() == currFloor)
            riders.removeMin();
    }
        
         
         
         //move method??
            
        
    
}