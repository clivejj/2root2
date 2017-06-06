public class Driver {

    int floors;
    int ellies;
    int pass;


    public void setup() {

	System.out.print("How many floors would you like your building to have? ");
	floors = Keyboard.readInt();;

	System.out.print("How many Elevators would you like in your smart Elevator system? " );
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
	    a.loopy(3600);
	    a.writeData();
	    dumb.loopy(3600);
	    dumb.writeData();
	}
	catch (Exception e) {
	    System.out.println("Ooops these numbers are too extreme, and will not work properly.");
	    System.out.println("You will have to choose other numbers: May we suggest (20, 10, and 50)?");
	    setup();
	}

	System.out.println("Your simulation was a success! Please navigate to the data folder to view these results!");
	System.out.println("A Smart Elevator working in your building would save you...");
    }
		

    public static void main(String[] args) {
	System.out.println("Hello! Welcome to our Elevator, where we can display to you our smart Elevator system");
	Driver bob = new Driver();
	bob.setup();
	
    }
	
}//end class Driver
