package epam.com.transport;

import java.util.*;

public class Passenger {

	private String name;
	private String passportId;

	public Passenger(String name, String passportId) {
		this.name = name;
		this.passportId = passportId;
	}

	public String getName() {
		return this.name;
	}

	public String getPassportId() {
		return this.passportId;
	}

	// book a ticket
	public void bookTicket() {
		// create the same number of tickets as the number of seats booked
		for (int i = 1; i <= chooseSeats(); i++) {
			new Ticket();
		}
	}

	// choose seats
	public int chooseSeats() {
		System.out.println("How many seats do you want to book?");
		Scanner input = new Scanner(System.in);
		int numberOfSeats = input.nextInt();
		return numberOfSeats;
	}

}
