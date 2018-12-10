package epam.com.transport;

public class Train {

	private String direction;
	private int trainNumber;
	private Stop [] stops;
	public static final int totalSeats = 360; // total number of seats in the train
	public static int numberOfFreeSeats = 360;
	
	public Train(String direction, int trainNumber) {
		
		this.direction = direction;
		this.trainNumber = trainNumber;
		
	}
	
	public int getNumberOfFreeSeats() {
		return numberOfFreeSeats;
	}

}
