# 2root2
# Caleb Smith-Salzberg, Clive Johnson

## Smart Elevator
Our project aims to model a working version of a smart elevator. The Elevator we have coded is essentially an optimized version of elevators where ranges are assigned (this method is found in many large buildings). Our Elevators have ranges that constantly change depending on the destinations of incoming Passengers, and also on the number of available Elevators. We believe this approach will reduce the travel time of Passengers trying to get to their floors.

## How It Works
We implemented an ArrayPriorityQueue to track Passengers and to assign ranges to Elevators. Using numbers that can easily be changed, times for each Elevator trip and times for each Passenger to reach their destination floor are produced. The Passengers come in waves, and the waves contain a certain number of Passengers travelling to random floors within a given range for the building. We feel this accurately represents a building's traffic.

The ControlTower class controls the incoming Passengers, and asks the Elevators to kindly accept new Passengers that fit into their range. If an Elevator is full, the Passenger is kindly asked to join the next wave of Passengers. We implemented an internal timer/clock inside the ControlTower in order to better track wait times, and to easily determine whether an Elevator is available by subtracting the current time from the Elevator's trip time.

The ranges are reassigned once Elevators become available. This means that, for example, if all Elevators are available (meaning they are less than x seconds from finishing) except one, all the Elevators will take up some extra floors so all floors are represented, until the last Elevator returns. This cycle continues until some time is reached in ControlTower. 

## To Launch:
1. Clone the repository: ```git clone git@github.com:clivejj/2root2.git```
2. Navigate to the correct directory: ```cd 2root2```
3. View a sample csv containing data from a simulation.
4. Run graphic that accompanies data.
5. Run a new simulation (may take some time to run)
NOTE: 3-5 does not exist with the current code
