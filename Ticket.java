package epam.com.transport;

public class Ticket {
	
	private static int idNum = 1000; //just a random id number, which's going to be updated
	//each time the Ticket constructor is called
	private int numberOfSeats;
	
	public Ticket() {
		this.idNum++;
		
		//let's check if there are seats available
		if (Train.numberOfFreeSeats > 0) {
		//if there are, update the number of free seats
		Train.numberOfFreeSeats -= numberOfSeats;
		} else {
			System.out.println("Sorry, there are currently no seats available");
		}
	}
		
		
	
}
