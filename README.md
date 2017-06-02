# 2root2
## Caleb Smith-Salzberg, Clive Johnston

## Smart Elevator
NOTE: MAKE SURE MIN AND MAX OF CONTROL TOWER ARE BEING UPDATED WEHN PASSENGERS FROM LEFTOVER ARE BEING ADDED TO PASSENGERS
Our project aims to model a working version of a smart elevator, whereby Passengers are assigned to an Elevator based on their destination. Passengers with the same or similar destinations should be grouped in the same Elevator to eliminate the need to stop on many different floors each time. The Elevator we have coded is essentially an optimized version of elevators where floor ranges are assigned for each elevator (this method is found in many large buildings). Our Elevators have floor ranges that constantly change depending on the destinations of incoming Passengers, and also on the number of available Elevators. We believe this approach will reduce the travel time of Passengers trying to get to their floors, and it will also reduce the distance that Elevators have to travel which would hopefully reduce the amount of maintenence that has to be done.

## How It Works
The ControlTower class creates waves of incoming Passengers; each wave has a constant number of Passengers and each Passenger has a random destination. Then, the control tower assigns floor ranges to each available Elevator based on the highest destination of the Passengers in the wave. This means that, for example, if all Elevators are available (meaning they are less than x seconds from finishing) except one, all the Elevators will take up some extra floors so all floors are represented, until the last Elevator returns. This cycle continues until some time is reached in ControlTower. 

The Control Tower asks the Elevators to kindly accept new Passengers that fit into their range. If an Elevator is full, the Passenger is kindly asked to join the next wave of Passengers. We implemented an internal timer/clock inside the ControlTower in order to better track wait times, and to easily determine whether an Elevator is available by subtracting the current time from the Elevator's trip time.

We implemented an ArrayPriorityQueue to track Passengers in each Elevator. Times for each Elevator trip and times for each Passenger to reach their destination floor are produced, using an algorithm that takes into account how many floors an Elevator has to travel, and how many Passengers it has to drop off. 

We feel the waves of Passengers closely represent a building's traffic, but it will not be a perfect simulation


## To Launch:
1. Clone the repository: ```git clone git@github.com:clivejj/2root2.git```
2. Navigate to the correct directory: ```cd 2root2```
3. View a sample csv containing data from a simulation.
4. Run graphic that accompanies data.
5. Run a new simulation (may take some time to run)
NOTE: 3-5 does not exist with the current code
