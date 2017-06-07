public class Driver {

    int floors;
    int ellies;
    int pass;


    public void setup() {

	System.out.print("How many floors would you like your building to have? ");
	floors = Keyboard.readInt();;

	System.out.print("How many Elevators would you like in your smart Elevator system (>= 6) ? " );
	ellies = Keyboard.readInt();

	System.out.print("Finally, how many Passengers would you like to send every wave? ");
	pass = Keyboard.readInt();

	simulation();
    }

    public void simulation() {
	ControlTower a = new ControlTower(floors, ellies, pass, (ellies / 3));
	ControlTower dumb = new ControlTower(floors, ellies, pass, 1);
	try {
	    System.out.println("\n\n\nYour simulation is running...");
	    a.loopy(10000);
	    a.writeData();
	    dumb.loopy(10000);
	    dumb.writeData();
	}
	catch (Exception e) {
	    System.out.println("Hmmm... Those numbers don't work too well...");
	    System.out.println("You will have to choose other numbers: May we suggest (20, 10, and 50)?");
	    setup();
	}

	System.out.println("Your simulation was a success! Please navigate to the data folder to view your results!");

	

	//store values from simulation
	int smartMean = a.meanTime;
	int smartMedian = a.medianTime;

	int dumbMean = dumb.meanTime;
	int dumbMedian = dumb.medianTime;

	//System.out.println("Smart:" + smartMean + smartMedian);
	//System.out.println("Dumb: " + dumbMean + dumbMedian);


	//calculate percent improvement of median and mean
	int percentMean = (int) (((dumbMean - smartMean) / (dumbMean + 0.0)) * 100) + 1;
	int percentMedian = (int) (((dumbMedian - smartMedian) / (dumbMedian + 0.0)) * 100) + 1;


	//take whichever improvement is greater
	int improvement = percentMean;
	if (percentMedian > percentMean) {
	    improvement = percentMedian;
	}

	//System.out.println(percentMean);
	//System.out.println(percentMedian);

	System.out.println("A Smart Elevator working in your building would be " + improvement + "% faster!");
    }
		

    public static void main(String[] args) {
	System.out.println("Hello! Welcome to our Elevator, where we can display to you our smart Elevator system");
	Driver bob = new Driver();
	bob.setup();
	
    }
	
}//end class Driver
