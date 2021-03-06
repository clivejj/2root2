# 2root2
## Caleb Smith-Salzberg, Clive Johnston

## Smart Elevator
Our project aims to model a working version of a smart elevator, whereby Passengers are assigned to an Elevator based on their destination. Passengers with the same or similar destinations should be grouped in the same Elevator to eliminate the need to stop on many different floors each time. The Elevator we have coded is essentially an optimized version of elevators where floor ranges are assigned for each elevator (this method is found in many large buildings). Our Elevators have floor ranges that constantly change depending on the destinations of incoming Passengers, and also on the number of available Elevators. We believe this approach will reduce the travel time of Passengers trying to get to their floors, and it will also reduce the distance that Elevators have to travel which would hopefully reduce the amount of maintenence that has to be done.

## How It Works
The ControlTower class creates waves of incoming Passengers; each wave has a constant number of Passengers and each Passenger has a random destination. Then, the ControlTower assigns floor ranges to each available Elevator (available meaning the Elevator is sitting on the ground floor and not yet full) based on the highest and lowest destination of the Passengers in the wave. Each Elevator will be assigned to cover an equal amount of floors as the other Elevators. 

The Control Tower asks the Elevators to kindly accept new Passengers whose destinations fit into their range. If an Elevator is full, the Passenger is kindly asked to join the next wave of Passengers. We implemented an internal timer/clock inside the ControlTower in order to better track wait times, and to easily determine whether an Elevator is available by subtracting the current time from the Elevator's trip time.

We implemented an Priority Queue to track Passengers in each Elevator. This is because Passengers are added to an Elevator in a random order, but we want the Passenger's with the lowest destination to be dropped off first. A Priority Queue is the perfect tool for this task. 

Times for each Elevator trip and times for each Passenger to reach their destination floor are produced, using an algorithm that takes into account how many floors an Elevator has to travel, and how many Passengers it has to drop off. 

We feel the waves of Passengers closely represent a building's traffic, but it will not be a perfect simulation.

The wait time, and trip times of each Passenger are logged in a csv file. A "dummy" Elevator system is also being run at the same time and its data also being stored, so that it can be compared with the results from the Smart Elevator.


## To Launch:
1. Open a terminal session
2. Clone the repository: ```$ git clone git@github.com:clivejj/2root2.git```
3. Navigate to the correct directory: ```$ cd 2root2```
4. Compile and run the program: ```$ javac Driver.java``` ```$ java Driver```
5. Follow prompts on screen, and enter appropriate integers
6. When the program terminates, navigate to the data directory: ```$ cd data```
7. Open the data you would like to view: 
  * ```log1.csv``` contains useful data from every single Passenger in the dummy Elevator
  * ```stat1.csv``` contains summary statistics for Passengers in the dummy Elevator
  * ```log[INT].csv``` contains useful data from every single Passenger in your Smart Elevator
  * ```stat[INT].csv``` contains summary statistics for Passengers in your Smart Elevator
